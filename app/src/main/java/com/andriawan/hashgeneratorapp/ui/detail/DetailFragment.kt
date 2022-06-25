package com.andriawan.hashgeneratorapp.ui.detail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.andriawan.hashgeneratorapp.base.BaseFragment
import com.andriawan.hashgeneratorapp.databinding.FragmentDetailBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModels()
    override val binding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }

    private val args: DetailFragmentArgs by navArgs()

    override fun onInitViews() {
        val algorithm = args.hash.name
        val plainText = args.plainText

        viewModel.processPlainText(plainText, algorithm)

        binding.copyButton.setOnClickListener {
            copyText(binding.resultTextView.text.toString())
        }
    }

    override fun onInitObserver() {
        viewModel.showResult.observe(this) {
            it.hasBeenHandled()?.let { resultText ->
                binding.resultTextView.text = resultText
            }
        }
    }

    private fun copyText(text: String) {
        val clipboard =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(LABEL_COPIED, text)
        clipboard.setPrimaryClip(clip)

        lifecycleScope.launch {
            showCopyTextAnimation()
        }
    }

    private suspend fun showCopyTextAnimation() {
        binding.successCopiedLinearLayout.animate()
            .translationY(0F)
            .alpha(1.0f).duration = 400L

        delay(3000)

        binding.successCopiedLinearLayout.animate()
            .translationY(-binding.successCopiedLinearLayout.height.toFloat())
            .alpha(0.0f).duration = 400L
    }

    companion object {
        private const val LABEL_COPIED = "Copied secured text"
    }
}