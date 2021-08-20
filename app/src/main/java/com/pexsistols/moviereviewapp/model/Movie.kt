package com.pexsistols.moviereviewapp.model

data class Movie(
    val name : String?,
    val director : String?,
    val genre : ArrayList<String?>,
    val year: String?,
    val length : String?,
    val posterUrl : String?,
    val review : String?)