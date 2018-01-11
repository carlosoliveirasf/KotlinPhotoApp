package com.project.shoes.photoapp.abstracts

import com.project.shoes.photoapp.models.PhotoList

/**
 * Created by carlos on 1/9/18.
 */
interface MainView {
    fun callBack()
    fun failure()
    fun sucess(response: retrofit2.Response<PhotoList>?)
}