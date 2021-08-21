package com.pexsistols.moviereviewapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import com.pexsistols.moviereviewapp.model.Movie
import java.util.*
import kotlin.collections.ArrayList

class MovieFeedViewModel : ViewModel() {

    private val TAG : String = "MOVIEWFEEDVIEWMODEL"
    private var db = Firebase.firestore
    val movies = MutableLiveData<ArrayList<Movie>>()


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

    fun readData(){
        db.collection("reviews").addSnapshotListener { value, error ->
            if( error != null){
                Log.w(TAG, error.localizedMessage, error)
                return@addSnapshotListener
            }
            if(value != null){
                val documents = value.documents
                val movies = ArrayList<Movie>()
                documents.forEach {
                    it.toString()
                    
                }
            }
        }
    }


}