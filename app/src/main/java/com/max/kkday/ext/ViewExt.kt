package com.max.kkday.ext

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 * 加入RecyclerView中LinearLayoutManager的ItemDecoration
 *
 * @param drawableRes 分隔線圖檔
 */
fun RecyclerView.addDividerItemDecoration(@DrawableRes drawableRes: Int) {
    (layoutManager as? LinearLayoutManager)?.let { linearLayoutManager ->
        ContextCompat.getDrawable(context, drawableRes)?.let { dividerDrawable ->
            val itemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
            itemDecoration.setDrawable(dividerDrawable)
            addItemDecoration(itemDecoration)
        }
    }
}

/**
 * Reduces drag sensitivity of [ViewPager2] widget
 */
fun ViewPager2.reduceDragSensitivityBy(multiple: Int) {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop * multiple)
}