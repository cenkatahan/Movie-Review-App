package com.pexsistols.moviereviewapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import com.pexsistols.moviereviewapp.model.Movie
import kotlin.collections.ArrayList

class MovieFeedViewModel : ViewModel() {

    private var db : FirebaseFirestore = Firebase.firestore
    private val movies = MutableLiveData<ArrayList<Movie>>()
    private lateinit var movieList : ArrayList<Movie>

    fun fetchMoviesFromFB(){
        movieList = ArrayList()

        //DO wrap with a method
        db.collection("reviews").addSnapshotListener { value, error ->
            if (error != null){
                println(error.localizedMessage)
            }else{
                if (value != null) {
                    if (!value.isEmpty){
                        val documents = value.documents

                        for (document in documents){
                            val title = document.get("name") as String

                            val movie = Movie(title,"",arrayListOf(),"","","","")
                            movieList.add(movie)
                        }
                        movies.value = movieList
                    }
                }
            }
        }
    }

    fun getMovies() : MutableLiveData<ArrayList<Movie>> {
        return movies
    }

}