package com.max.kkday.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.max.kkday.R
import com.max.kkday.databinding.ActivityMainBinding
import com.max.kkday.ext.hideKeyboard
import com.max.kkday.ext.reduceDragSensitivityBy
import com.max.kkday.ext.showKeyboard
import com.max.kkday.ext.showToast
import com.max.kkday.model.service.util.LoadApiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private val CHECK_LINK_REGEX = "[a-zA-Z0-9_`~!@#\$%^&*()+=/-{}\\[\\]\"'-.?,<>|:]*".toRegex()
    }

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    private val mainPagers by lazy {
        MainPager.values().toList()
    }
    private val mainPagerAdapter by lazy {
        MainPagerAdapter(this, mainPagers)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTabLayout()
        initViewPager2()
        initErrorText()
        initEditText()
        initGetShortLinkButton()
        observeViewModel()
    }

    private fun initTabLayout() {
        mainPagers.forEach { _ ->
            binding.tabLayoutMainPager.addTab(binding.tabLayoutMainPager.newTab())
        }
    }

    private fun initViewPager2() {
        binding.viewPagerMain.apply {
            reduceDragSensitivityBy(3)
            adapter = mainPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val tab = binding.tabLayoutMainPager.getTabAt(position)
                    tab?.select()
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageScrollStateChanged(state: Int) {
                }
            })
        }
    }

    private fun initGetShortLinkButton() {
        binding.buttonGetShortLink.setOnClickListener {
            conventLink()
        }
    }

    private fun initErrorText() {
        binding.TextViewErrorText.setOnClickListener {
            binding.TextViewErrorText.isVisible = false
            showKeyboard(binding.editTextLink)
        }
    }

    private fun conventLink() {
        if (binding.editTextLink.text.isNotBlank()) {

            if (isEditTextLinkMatches()) {
                viewModel.conventLink(binding.editTextLink.text.toString())
            } else {
                showToast(getString(R.string.input_error))
            }

        } else {
            binding.TextViewErrorText.isVisible = true
            hideKeyboard()
        }
    }

    private fun initEditText() {
        binding.editTextLink.apply {
            setOnEditorActionListener { _, actionId, _ ->

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    conventLink()
                }

                false
            }
        }
    }

    private fun observeViewModel() {
        viewModel.loadApiStatus.observe(this, {
            it?.let { status ->
                when (status) {
                    LoadApiStatus.LOADING -> {
                        binding.mainProgressBar.isVisible = true
                    }
                    LoadApiStatus.DONE -> {
                        showToast(getString(R.string.success))
                        hideKeyboard()
                        binding.editTextLink.setText("")
                        binding.mainProgressBar.isVisible = false

                        if (binding.viewPagerMain.currentItem != MainPager.HISTORY.ordinal) {
                            binding.viewPagerMain.currentItem = MainPager.HISTORY.ordinal
                        }
                    }
                    is LoadApiStatus.ERROR -> {
                        showToast(status.errorMessage)
                        binding.mainProgressBar.isVisible = false
                    }
                }
            }
        })
    }

    private fun isEditTextLinkMatches(): Boolean {
        return CHECK_LINK_REGEX.matches(binding.editTextLink.text)
    }
}