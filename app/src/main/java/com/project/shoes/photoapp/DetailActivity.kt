package com.project.shoes.photoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.support.v7.widget.Toolbar
import com.bumptech.glide.Glide
import com.project.shoes.photoapp.models.Photo
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val ImageView = findViewById<ImageView>(R.id.imageView) as ImageView
        val photo = intent.getSerializableExtra(PHOTO) as Photo?

        photo?.webformatURL.let {
            Glide.with(this).load(photo?.webformatURL).into(imageView)
        }

        imageView.setOnClickListener{
            finish()
        }
    }

    companion object {
        val PHOTO = "PHOTO"
    }
}
