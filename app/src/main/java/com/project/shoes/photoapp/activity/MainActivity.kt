package com.project.shoes.photoapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.project.shoes.photoapp.R
import com.project.shoes.photoapp.abstracts.MainView
import com.project.shoes.photoapp.models.Photo
import com.project.shoes.photoapp.models.PhotoList
import com.project.shoes.photoapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener, MainView {
    var photos: List<Photo>? = null
    val presenter : MainPresenter? = MainPresenter(this)
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
        callBack()
    }

    override fun callBack(){
        presenter?.call()
    }

    override fun sucess(response : Response<PhotoList>?) {
        this@MainActivity.photos = response?.body()?.hits
        mainAdapter = MainAdapter(this@MainActivity.photos!!, this@MainActivity)
        recyclerView.adapter = mainAdapter
    }

    override fun failure() {
        Log.e("MainActivity", "Problems calling API")
    }
}
