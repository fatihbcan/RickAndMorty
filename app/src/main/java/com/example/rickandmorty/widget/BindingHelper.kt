package com.example.rickandmorty.widget

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmorty.R

// binds image, if it fails (image url is empty for example) returns a red "X" as default
@BindingAdapter("imageUrl")
fun setImageView(view: ImageView, uri: String) {
    val options = RequestOptions()
        .error(R.drawable.image_load_failed_default)
    Glide.with(view.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(view)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setLocation")
fun setLocation(view: TextView, location: String) {
    view.text = "Last Location : $location"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setStatus")
fun setStatus(view: TextView, location: String) {
    view.text = "Status : $location"
}