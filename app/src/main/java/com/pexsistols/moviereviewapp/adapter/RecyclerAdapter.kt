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
import com.pexsistols.moviereviewapp.view.MovieFeedFragmentDirections
import java.util.ArrayList

class RecyclerAdapter(private val movieList : ArrayList<Movie>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val posterHolder : ImageView
        val nameHolder : TextView
        val yearHolder : TextView
        val lengthHolder : TextView

        init {
            posterHolder = view.findViewById(R.id.row_poster)
            nameHolder = view.findViewById(R.id.row_name)
            yearHolder = view.findViewById(R.id.row_year)
            lengthHolder = view.findViewById(R.id.row_length)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movieList[position]

        holder.nameHolder.text = currentMovie.title
        holder.yearHolder.text = currentMovie.year
        holder.lengthHolder.text = currentMovie.length
        //holder.posterHolder = movieList[position].posterUrl

        holder.itemView.setOnClickListener {
            val action = MovieFeedFragmentDirections.actionMovieFeedFragmentToMovieReviewFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateMovieList(list : ArrayList<Movie>){
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }
}