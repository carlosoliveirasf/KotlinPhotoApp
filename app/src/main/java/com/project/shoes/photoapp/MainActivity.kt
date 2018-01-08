package com.project.shoes.photoapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.project.shoes.photoapp.api.PhotoRetriever
import com.project.shoes.photoapp.models.Photo
import com.project.shoes.photoapp.models.PhotoList
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var photos: List<Photo>? = null

    var mainAdapter : MainAdapter? = null
    lateinit var recyclerView : RecyclerView

    override fun onClick(view: View?) {
        val intent = Intent(this, DetailActivity::class.java)
        val holder = view?.tag as MainAdapter.PhotoViewHolder
        intent.putExtra(DetailActivity.PHOTO, mainAdapter?.getPhoto(holder.adapterPosition))
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var retriever = PhotoRetriever()

        val callback = object : Callback<PhotoList>{
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
                Log.e("MainActivity", "Problems calling API", t)
            }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                response?.isSuccessful.let {
                    this@MainActivity.photos = response?.body()?.hits
                    mainAdapter = MainAdapter(this@MainActivity.photos!!, this@MainActivity)
                    recyclerView.adapter = mainAdapter
                }
            }
        }
        retriever.getPhotos(callback)

    }
}
