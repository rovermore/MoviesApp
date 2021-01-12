package com.example.moviesapp.screen.main

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.MoviesApp
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMainBinding
import com.example.moviesapp.model.canon.Movie
import com.example.moviesapp.utils.ViewState
import com.example.moviesapp.utils.gone
import com.example.moviesapp.utils.visible
import javax.inject.Inject


class MainFragment : Fragment(), MainAdapter.OnItemClicked{

    @Inject
    lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root
    }

    init { MoviesApp.daggerAppComponent().inject(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupReloadButton() {
        binding.errorView.reloadButton.setOnClickListener {
            mainViewModel.loadData()
        }
    }

    private fun setupObservers() {
        observeState()
    }

    private fun observeState() {
        mainViewModel.viewState.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
    }

    private fun setupUI() {
        setupReloadButton()
    }

    private fun setSearchView(movieList: MutableList<Movie>) {
        binding.searchBar.apply {
            queryHint = resources.getString(R.string.search_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        val filteredMovieList = movieList.filter { it.title.contains(query, true) }
                        mainAdapter.updateMovieList(filteredMovieList.toMutableList())
                    }
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }
            })

            setOnCloseListener(object : SearchView.OnCloseListener{
                override fun onClose(): Boolean {
                    mainAdapter.updateMovieList(movieList)
                    return false
                }

            })

        }
    }

    private fun setUpRecyclerView(movieList: List<Movie>) {
        mainAdapter = MainAdapter(movieList.toMutableList(), this)
        binding.mainRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
        setSearchView(movieList.toMutableList())
    }

    private fun updateUI(viewState: ViewState<List<Movie>>) {
        when (viewState) {
            ViewState.Error -> setErrorView()
            ViewState.Loading -> setLoadingView()
            is ViewState.Content -> {
                setUpRecyclerView(viewState.data)
                setSuccessView()
            }
        }
    }

    private fun setErrorView() {
        binding.apply {
            contentView.gone()
            progressBar.gone()
            errorView.root.visible()
        }
    }

    private fun setLoadingView() {
        binding.apply {
            errorView.root.gone()
            contentView.gone()
            progressBar.visible()
        }
    }

    private fun setSuccessView() {
        binding.apply {
            progressBar.gone()
            errorView.root.gone()
            contentView.visible()
        }
    }

    override fun itemClicked(movie: Movie) {
        navigateToDetailFragment(movie.id)
    }

    private fun navigateToDetailFragment(id: Int) {
        val bundle = bundleOf("movieID" to id)
        NavHostFragment.findNavController(this).navigate(R.id.action_MainFragment_to_DetailFragment, bundle)
    }

}