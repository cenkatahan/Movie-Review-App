package com.pexsistols.moviereviewapp.service

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pexsistols.moviereviewapp.model.Movie

class FirebaseService {

    private var db : FirebaseFirestore = Firebase.firestore

    fun getAllDataFromFB(movies : MutableLiveData<ArrayList<Movie>>){
        val movieList = ArrayList<Movie>()

        db.collection("reviews").orderBy("id", Query.Direction.ASCENDING).addSnapshotListener { value, error ->
            if (error != null){
                println(error.localizedMessage)
            }else{
                if (value != null) {
                    if (!value.isEmpty){
                        val documents = value.documents

                        for (document in documents){
                            val id = document.get("id") as Number
                            val title = document.get("name") as String
                            val ogTitle = document.get("originalTitle") as String
                            val director = document.get("director") as String
                            val genres = document.get("genre") as String
                            val year = document.get("year") as String
                            val length = document.get("length") as String
                            val posterUrl = document.get("posterUrl") as String
                            val review = document.get("review") as String

                            val movie = Movie(id.toInt(), title, ogTitle, director, genres, year, length, posterUrl, review)

                            movieList.add(movie)
                        }
                        movies.value = movieList
                    }
                }
            }
        }
    }

    fun getDataFromFb(id : Int, movie : MutableLiveData<Movie>){
        db.collection("reviews").whereEqualTo("id", id).orderBy("id", Query.Direction.ASCENDING).addSnapshotListener { value, error ->
            if (error != null){
                println(error.localizedMessage)
            }else{
                if(value != null){
                    val documentValues = value.documents

                    for (data in documentValues){
                        val id = data.get("id") as Number
                        val title = data.get("name") as String
                        val ogTitle = data.get("originalTitle") as String
                        val director = data.get("director") as String
                        val genres = data.get("genre") as String
                        val year = data.get("year") as String
                        val length = data.get("length") as String
                        val posterUrl = data.get("posterUrl") as String
                        val review = data.get("review") as String

                        val x = Movie(id.toInt(), title, ogTitle, director, genres, year, length, posterUrl, review)

                        movie.value = x
                    }
                }
            }
        }
    }

}