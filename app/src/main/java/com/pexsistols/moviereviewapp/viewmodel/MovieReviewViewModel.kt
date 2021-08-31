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

        println("ID: $id")
//        db.collection("reviews").whereEqualTo("id", id).addSnapshotListener { value, error ->
//            if (error != null){
//                println(error.localizedMessage)
//            }else{
//                if(value != null){
//                    val documentValues = value.documents
//
//                    for (data in documentValues){
//                        println(data.get("name"))
//                        val title = data.get("name") as String
//                        selectedMovie = Movie(title,"",arrayListOf(),"","","","")
//                        movie.value = selectedMovie
//                    }
//                }
//            }
//        }
    }


    fun getMovie() : MutableLiveData<Movie> {
        return movie
    }
}