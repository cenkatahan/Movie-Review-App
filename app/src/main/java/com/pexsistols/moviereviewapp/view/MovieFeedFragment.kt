package com.pexsistols.moviereviewapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pexsistols.moviereviewapp.R
import com.pexsistols.moviereviewapp.adapter.RecyclerAdapter
import com.pexsistols.moviereviewapp.model.Movie
import com.pexsistols.moviereviewapp.viewmodel.MovieFeedViewModel

class MovieFeedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter : RecyclerAdapter
    private lateinit var movieList : ArrayList<Movie>
    private var db = Firebase.firestore
    private lateinit var movieFeedViewModel : MovieFeedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieList = ArrayList()

        movieFeedViewModel = ViewModelProviders.of(this).get(MovieFeedViewModel::class.java)
        movieFeedViewModel.refreshData()

        recyclerView = view.findViewById(R.id.recyclerview_feed)
        initRecyclerview()

        observeMovies()

        //fetchMovies()
    }

    private fun initRecyclerview(){
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        movieAdapter = RecyclerAdapter(movieList)
        recyclerView.adapter = movieAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun observeMovies(){

        movieFeedViewModel.getMovies().observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                recyclerView.visibility = View.VISIBLE
                movieAdapter.updateMovieList(movies)
            }
        })
    }

    private fun fetchMovies(){
        db.collection("reviews")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //println("${document["name"]}")
                }
            }
            .addOnFailureListener {
                //Log.w(TAG, "Error getting documents.", exception)
            }
    }



}