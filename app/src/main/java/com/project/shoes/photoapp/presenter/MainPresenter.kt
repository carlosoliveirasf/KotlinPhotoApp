package com.project.shoes.photoapp.presenter

import com.project.shoes.photoapp.abstracts.MainView
import com.project.shoes.photoapp.api.PhotoRetriever
import com.project.shoes.photoapp.models.PhotoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by carlos on 1/9/18.
 */
class MainPresenter {
    var mainView : MainView
    var retriever = PhotoRetriever()

    constructor(mainView: MainView){
        this.mainView = mainView
    }

    fun call() : Callback<PhotoList>{
        val callback = object : Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {

                return mainView.failure()
            }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                return mainView.sucess(response)
            }
        }
        retriever.getPhotos(callback)
        return callback
    }

}