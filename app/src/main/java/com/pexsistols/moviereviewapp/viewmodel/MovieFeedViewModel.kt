package com.pexsistols.moviereviewapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pexsistols.moviereviewapp.model.Movie
import com.pexsistols.moviereviewapp.service.FirebaseData
import kotlin.collections.ArrayList

class MovieFeedViewModel : ViewModel() {

    private val movies = MutableLiveData<ArrayList<Movie>>()
    private val firebaseData = FirebaseData()

    fun fetchMovies(){
        firebaseData.getAllDataFromFB(movies)
    }

    fun getMovies() : MutableLiveData<ArrayList<Movie>> {
        return movies
    }

}