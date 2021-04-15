package com.tcs.testapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * This method is used to load image to imageview using Picasso library
 */
@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Picasso.get()
        .load(url)
        .into(view)
}