package com.pexsistols.moviereviewapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pexsistols.moviereviewapp.R
import com.pexsistols.moviereviewapp.viewmodel.MovieFeedViewModel
import com.pexsistols.moviereviewapp.viewmodel.MovieReviewViewModel

class MovieReviewFragment : Fragment() {

    private lateinit var title : TextView
    private lateinit var director : TextView
    private lateinit var year : TextView
    private lateinit var length : TextView
    private lateinit var poster : ImageView
    private lateinit var genres : TextView
    private lateinit var review : TextView
    private lateinit var db : FirebaseFirestore
    private lateinit var movieReviewViewModel: MovieReviewViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.firestore
        movieReviewViewModel = ViewModelProviders.of(this).get(MovieReviewViewModel::class.java)


    }
}