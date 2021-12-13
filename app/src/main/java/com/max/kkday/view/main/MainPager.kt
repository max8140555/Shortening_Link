package com.max.kkday.view.main

import androidx.fragment.app.Fragment
import com.max.kkday.view.history.HistoryFragment
import com.max.kkday.view.home.HomeFragment

enum class MainPager {
    HOME {
        override fun createFragment(): Fragment = HomeFragment.newInstance()
    },
    HISTORY {
        override fun createFragment(): Fragment = HistoryFragment.newInstance()
    };

    abstract fun createFragment(): Fragment
}