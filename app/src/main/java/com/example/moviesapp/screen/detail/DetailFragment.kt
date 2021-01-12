package com.example.moviesapp.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.moviesapp.MoviesApp
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentDetailBinding
import com.example.moviesapp.model.canon.Movie
import com.example.moviesapp.utils.ViewState
import com.example.moviesapp.utils.getImageUrl
import com.example.moviesapp.utils.gone
import com.example.moviesapp.utils.visible
import com.squareup.picasso.Picasso
import javax.inject.Inject


class DetailFragment : Fragment() {

    @Inject
    lateinit var detailViewModel: DetailViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var movieId: Int? = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val bundle = arguments
        movieId = bundle?.getInt("movieID")
        movieId?.let { detailViewModel.initialize(it) }
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    init {
        MoviesApp.daggerAppComponent().inject(this)
    }

    private fun setupReloadButton() {
        movieId?.let {
            binding.detailErrorView.reloadButton.setOnClickListener {
                detailViewModel.initialize(movieId!!)
            }
        }
    }

    private fun setupObservers() {
        observeState()
    }

    private fun observeState() {
        detailViewModel.viewState.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
    }

    private fun setupUI() {
        setupReloadButton()
    }

    private fun setupView(movie: Movie) {
        binding.apply {
            titleTextView.text = movie.title
            releaseDate.text = movie.date
            rating.text = movie.vote.toString()
            overviewTextView.text = movie.overview
            Picasso.with(binding.root.context)
                .load(movie.getImageUrl())
                .error(R.drawable.ic_baseline_error_24)
                .into(posterImageView)
        }
    }

    private fun updateUI(it: ViewState<Movie>?) {
        when (it) {
            ViewState.Error -> setErrorView()
            ViewState.Loading -> setLoadingView()
            is ViewState.Content -> {
                setupView(it.data)
                setSuccessView()
            }
        }
    }

    private fun setErrorView() {
        binding.apply {
            detailProgressBar.gone()
            detailContent.gone()
            detailErrorView.root.visible()
        }
    }

    private fun setLoadingView() {
        binding.apply {
            detailErrorView.root.gone()
            detailContent.gone()
            detailProgressBar.visible()
        }
    }

    private fun setSuccessView() {
        binding.apply {
            detailProgressBar.gone()
            detailErrorView.root.gone()
            detailContent.visible()
        }
    }
}