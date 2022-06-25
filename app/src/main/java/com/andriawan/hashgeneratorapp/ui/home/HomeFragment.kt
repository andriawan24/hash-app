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
        binding.secureButton.setOnClickListener {
            if (binding.algorithmChooserAutoCompleteView.text.isNotEmpty() &&
                binding.plainTextTextView.text?.isNotEmpty() == true
            ) {
                val plainText = binding.plainTextTextView.text.toString()
                val algorithms = viewModel.hashTypes.value ?: emptyList()
                val algorithm = algorithms.find {
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
                showToast(requireContext().getString(R.string.error_secure_form))
            }
        }

        binding.clearButton.setOnClickListener {
            binding.algorithmChooserAutoCompleteView.setText("")
            binding.plainTextTextView.setText("")
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

    override fun onResume() {
        super.onResume()
        viewModel.getHashTypes()
    }
}
