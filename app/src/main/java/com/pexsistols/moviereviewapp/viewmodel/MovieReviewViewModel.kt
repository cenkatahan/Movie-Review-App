package com.pexsistols.moviereviewapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pexsistols.moviereviewapp.model.Movie

class MovieReviewViewModel : ViewModel() {

    private var db : FirebaseFirestore = Firebase.firestore
    private val movie = MutableLiveData<Movie>()
    private lateinit var selectedMovie: Movie

    fun fetchMovieFromFB(){

    }


    fun getMovie() : MutableLiveData<Movie> {
        return movie
    }
}