package com.pexsistols.moviereviewapp.service

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pexsistols.moviereviewapp.R

class GlideService {

    fun downloadPoster(view: View, posterUrl : Any?, poster: ImageView){
        Glide
            .with(view)
            .load(posterUrl)
            .centerCrop()
            .placeholder(R.drawable.background)
            .into(poster)
    }

}