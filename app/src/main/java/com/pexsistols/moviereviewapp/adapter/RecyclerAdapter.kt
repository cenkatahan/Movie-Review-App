package com.pexsistols.moviereviewapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pexsistols.moviereviewapp.R
import com.pexsistols.moviereviewapp.model.Movie
import com.pexsistols.moviereviewapp.service.GlideService
import com.pexsistols.moviereviewapp.view.MovieFeedFragmentDirections
import java.util.ArrayList

class RecyclerAdapter(private val movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val posterHolder: ImageView = view.findViewById(R.id.row_poster)
        val titleHolder: TextView = view.findViewById(R.id.row_title)
        val originalTitleHolder: TextView = view.findViewById(R.id.row_original_title)
        val yearHolder: TextView = view.findViewById(R.id.row_year)
        val lengthHolder: TextView = view.findViewById(R.id.row_length)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movieList[position]

        with(holder) {
            titleHolder.text = currentMovie.title
            originalTitleHolder.text = currentMovie.originalTitle
            yearHolder.text = currentMovie.year
            lengthHolder.text = currentMovie.length

            GlideService.downloadPoster(
                itemView,
                currentMovie.posterUrl,
                posterHolder
            )

            itemView.setOnClickListener {
                val action = currentMovie.id?.let { it1 ->
                    MovieFeedFragmentDirections.actionMovieFeedFragmentToMovieReviewFragment(
                        it1
                    )
                }
                Navigation.findNavController(it).navigate(action!!)
            }
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateMovieList(list: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

}