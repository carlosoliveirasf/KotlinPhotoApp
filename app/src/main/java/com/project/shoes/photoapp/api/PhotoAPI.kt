package com.project.shoes.photoapp.api

import com.project.shoes.photoapp.models.PhotoList
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by carlos on 1/8/18.
 */
interface PhotoAPI {

    @GET("?key=7633006-59e460287cf26179fb2ebc1d4&q=computer&image_type=photo")
    fun getPhotos() : Call<PhotoList>
}