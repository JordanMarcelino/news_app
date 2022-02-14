package com.example.newsapiclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.newsapiclient.databinding.FragmentDetailsBinding
import com.example.newsapiclient.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        val args = DetailsFragmentArgs.fromBundle(requireArguments())
        binding.wvNews.apply {
            webViewClient = WebViewClient()
            args.article.url?.let { loadUrl(it) }
        }
        binding.fabSave.setOnClickListener {
            viewModel.saveNews(args.article)
            Snackbar.make(view, "Saved Successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}