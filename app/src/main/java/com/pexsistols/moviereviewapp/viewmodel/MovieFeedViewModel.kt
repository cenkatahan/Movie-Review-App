package com.pexsistols.moviereviewapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import com.pexsistols.moviereviewapp.model.Movie
import kotlin.collections.ArrayList

class MovieFeedViewModel : ViewModel() {

    private val TAG : String = "MOVIEWFEEDVIEWMODEL"
    private var db : FirebaseFirestore = Firebase.firestore
    private val movies = MutableLiveData<ArrayList<Movie>>()
    private lateinit var movieList : ArrayList<Movie>


    //change method name
    fun refreshData(){
        val bladeRunner = Movie("Blade Runner","Ridley Scott", arrayListOf("Sci-fi"), "1982", "1saat 57dk", "", "VERY GOOD")
        val bladeRunner2049 = Movie("Blade Runner 2049",
            "Denis Villeneuve", arrayListOf("Sci-fi"), "2017", "2 saat 44 dk", "", "VERY GOOD!!!")

        movieList = ArrayList<Movie>()
//        movieList.add(bladeRunner)
//        movieList.add(bladeRunner2049)



        db.collection("reviews").addSnapshotListener { value, error ->
            if (value != null) {
                if (!value.isEmpty){
                    val documents = value.documents

                    for (document in documents){
                        val title = document.get("name") as String

                        val movie = Movie(title, "", arrayListOf(), "", "", "", "")
                        movieList.add(movie)
                    }
                    movies.value = movieList
                }
            }
        }

//        db.collection("reviews")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    //println("${document["name"]}")
//                    val title = document.get("name") as String
//
//                    val currentMovie = Movie(title, "", arrayListOf(), "", "", "", "")
//                    movieList.add(currentMovie)
//                }
//            }
//            .addOnFailureListener {
//                //Log.w(TAG, "Error getting documents.", exception)
//            }





    }

    fun getMovies() : MutableLiveData<ArrayList<Movie>> {
        return movies
    }

}