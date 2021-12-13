package com.max.kkday.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val pagers: List<MainPager>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return pagers.size
    }

    override fun createFragment(position: Int): Fragment {
        return pagers[position].createFragment()
    }
}
