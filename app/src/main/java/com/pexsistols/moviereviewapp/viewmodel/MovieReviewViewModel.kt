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

    fun fetchMovieFromFB(id : Int){

        db.collection("reviews").whereEqualTo("id", id.toString()).addSnapshotListener { value, error ->
            if (error != null){
                println(error.localizedMessage)
            }else{
                if(value != null){
                    val documentValues = value.documents

                    for (data in documentValues){
                        val title = data.get("name") as String
                        val ogTitle = data.get("originalTitle") as String
                        val director = data.get("director") as String
                        val genres = data.get("genre") as String
                        val year = data.get("year") as String
                        val length = data.get("length") as String
                        //val posterUrl = data.get("posterUrl") as String
                        val review = data.get("review") as String

                        selectedMovie = Movie(title, ogTitle, director, genres, year, length, "", review)

                        movie.value = selectedMovie
                    }
                }
            }
        }

    }

    fun getMovie() : MutableLiveData<Movie> {
        return movie
    }
}