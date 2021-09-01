package com.pexsistols.moviereviewapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pexsistols.moviereviewapp.model.Movie
import com.pexsistols.moviereviewapp.service.FirebaseService

class MovieReviewViewModel : ViewModel() {

    private val movie = MutableLiveData<Movie>()
    private val firebaseService = FirebaseService()

    fun fetchMovieFromFB(id : Int){
        firebaseService.getDataFromFb(id, movie)
    }

    fun getMovie() : MutableLiveData<Movie> {
        return movie
    }
}