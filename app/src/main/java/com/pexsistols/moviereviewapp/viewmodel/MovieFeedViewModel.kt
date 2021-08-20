package com.pexsistols.moviereviewapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pexsistols.moviereviewapp.model.Movie

class MovieFeedViewModel : ViewModel() {

    val movies = MutableLiveData<List<Movie>>()



    //change method name
    fun refreshData(){
        val bladeRunner = Movie("Blade Runner","Ridley Scott", arrayListOf("Sci-fi"), "1982", "1saat 57dk", "", "VERY GOOD")
        val bladeRunner2049 = Movie("Blade Runner 2049",
            "Denis Villeneuve", arrayListOf("Sci-fi"), "2017", "2 saat 44 dk", "", "VERY GOOD!!!")

        val movieListTest = ArrayList<Movie>()
        movieListTest.add(bladeRunner)
        movieListTest.add(bladeRunner2049)


        movies.value = movieListTest

    }
}