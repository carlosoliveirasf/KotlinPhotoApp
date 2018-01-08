package com.project.shoes.photoapp.models

import java.io.Serializable

/**
 * Created by carlos on 1/8/18.
 */
data class Photo(val id: String,
                 val likes: Int,
                 val favorites: Int,
                 val tags: String,
                 val previewURL: String,
                 val webformatURL: String) : Serializable {
}