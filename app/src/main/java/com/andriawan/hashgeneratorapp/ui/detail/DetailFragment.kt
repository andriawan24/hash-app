package com.andriawan.hashgeneratorapp.ui.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.andriawan.hashgeneratorapp.base.BaseFragment
import com.andriawan.hashgeneratorapp.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModels()
    override val binding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }

    private val args: DetailFragmentArgs by navArgs()

    override fun onInitViews() {
        val name = args.hash.name
    }

    override fun onInitObserver() {

    }
}