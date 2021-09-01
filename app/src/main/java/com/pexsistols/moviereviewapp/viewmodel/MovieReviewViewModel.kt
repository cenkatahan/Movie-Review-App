package com.pexsistols.moviereviewapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pexsistols.moviereviewapp.model.Movie
import com.pexsistols.moviereviewapp.service.FirebaseData

class MovieReviewViewModel : ViewModel() {

    private val movie = MutableLiveData<Movie>()
    private val firebaseData = FirebaseData()

    fun fetchMovieFromFB(id : Int){
        firebaseData.getDataFromFb(id, movie)
    }

    fun getMovie() : MutableLiveData<Movie> {
        return movie
    }
}