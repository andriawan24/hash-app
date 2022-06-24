package com.andriawan.hashgeneratorapp.ui.home

import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andriawan.hashgeneratorapp.R
import com.andriawan.hashgeneratorapp.base.BaseFragment
import com.andriawan.hashgeneratorapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()
    override val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        viewModel.getHashTypes()

        binding.secureButton.setOnClickListener {
            if (binding.algorithmChooserAutoCompleteView.text.isNotEmpty()
                && binding.plainTextTextView.text?.isNotEmpty() == true
            ) {
                val plainText = binding.plainTextTextView.text.toString()
                val algorithm = viewModel.hashTypes.value?.first {
                    it.name == binding.algorithmChooserAutoCompleteView.text.toString()
                }

                algorithm?.let {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                            hash = algorithm,
                            plainText = plainText
                        )
                    )
                }
            } else {
                showToast("Please choose the algorithm and enter the plain text first")
            }
        }
    }

    override fun onInitObserver() {
        viewModel.updateDropdownList.observe(this) {
            it.hasBeenHandled()?.let { list ->
                val options = list.map { sub -> sub.name }
                val adapter = ArrayAdapter(requireContext(), R.layout.list_item, options)
                binding.algorithmChooserAutoCompleteView.setAdapter(adapter)
            }
        }
    }
}