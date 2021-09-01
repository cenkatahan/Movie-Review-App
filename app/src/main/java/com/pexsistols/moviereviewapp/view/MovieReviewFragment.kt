package com.pexsistols.moviereviewapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.pexsistols.moviereviewapp.R
import com.pexsistols.moviereviewapp.viewmodel.MovieReviewViewModel

class MovieReviewFragment : Fragment() {

    private lateinit var title : TextView
    private lateinit var ogTitle : TextView
    private lateinit var director : TextView
    private lateinit var year : TextView
    private lateinit var length : TextView
    private lateinit var poster : ImageView
    private lateinit var genres : TextView
    private lateinit var review : TextView
    private lateinit var movieReviewViewModel: MovieReviewViewModel
    private var movieId : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        declareComponents(view)

        arguments?.let {
            movieId = MovieReviewFragmentArgs.fromBundle(it).id
        }

        movieReviewViewModel = ViewModelProviders.of(this).get(MovieReviewViewModel::class.java)
        movieReviewViewModel.fetchMovieFromFB(movieId)

        observeMovie()
    }

    private fun declareComponents(view: View){
        title = view.findViewById(R.id.review_title)
        ogTitle = view.findViewById(R.id.review_original_title)
        director = view.findViewById(R.id.review_director)
        year = view.findViewById(R.id.review_year)
        length = view.findViewById(R.id.review_length)
        poster = view.findViewById(R.id.review_poster)
        genres = view.findViewById(R.id.review_genre)
        review = view.findViewById(R.id.review_review)
    }

    private fun observeMovie(){
        movieReviewViewModel.getMovie().observe(viewLifecycleOwner, {
            title.text = it.title
            ogTitle.text = it.originalTitle
            director.text = it.director
            year.text = it.year
            length.text = it.length
            genres.text = it.genre
            //poster.text = it.title
            review.text = it.review

        })
    }

}