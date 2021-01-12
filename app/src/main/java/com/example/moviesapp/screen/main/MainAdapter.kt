package com.example.moviesapp.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.MovieItemBinding
import com.example.moviesapp.model.canon.Movie
import com.example.moviesapp.utils.getImageUrl
import com.squareup.picasso.Picasso

class MainAdapter (var movieList: MutableList<Movie>?,
                   val itemClicked: OnItemClicked
) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    interface OnItemClicked {
        fun itemClicked(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(movieList.isNullOrEmpty()){
            return 0
        }
        return movieList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(!movieList.isNullOrEmpty()) {
            holder.bindView(movieList!![position])
        }
    }

    fun updateMovieList(updatedMovieList: MutableList<Movie>){
        movieList = updatedMovieList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val binding  = MovieItemBinding.bind(view)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            movieList?.get(adapterPosition)?.let { itemClicked.itemClicked(it) }
        }

        fun bindView(movie: Movie) {
            binding.apply {
                movie.image?.run {
                    Picasso.with(binding.root.context)
                        .load(movie.getImageUrl())
                        .error(R.drawable.ic_baseline_error_24)
                        .into(movieImage)
                }
            }
        }
    }
}