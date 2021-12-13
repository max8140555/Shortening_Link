package com.max.kkday.view.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.max.kkday.R
import com.max.kkday.databinding.FragmentHistoryBinding
import com.max.kkday.ext.addDividerItemDecoration
import com.max.kkday.ext.copyText
import com.max.kkday.ext.hideKeyboard
import com.max.kkday.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment(R.layout.fragment_history) {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModel()

    private val historyOnViewHolderListener by lazy {
        object : HistoryOnViewHolderListener {
            override fun deleteHistory(id: Int) {
                viewModel.deleteHistory(id)
            }

            override fun clickItem() {
                requireActivity().hideKeyboard()
            }

            override fun copyText(shortLink: String) {
                requireContext().copyText(shortLink)
                requireActivity().showToast(getString(R.string.copy_success))
            }
        }
    }

    private val historyAdapter by lazy {
        HistoryAdapter(historyOnViewHolderListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHistoryBinding.bind(view)

        initRecyclerView()
        initConstraintLayout()
        observeViewModel()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initConstraintLayout() {
        binding.historyConstraintLayout.setOnClickListener {
            requireActivity().hideKeyboard()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewHistory.apply {
            adapter = historyAdapter
            addDividerItemDecoration(R.drawable.shape_8dp_transparent_divider)
        }
    }

    private fun observeViewModel() {
        viewModel.items.observe(viewLifecycleOwner, {
            it?.let { items ->
                historyAdapter.submitList(items) {
                    _binding?.recyclerViewHistory?.scrollToPosition(0)
                }
            }
        })
    }

}