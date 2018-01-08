package com.project.shoes.photoapp

import android.provider.ContactsContract
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.project.shoes.photoapp.models.Photo
import java.text.FieldPosition

/**
 * Created by carlos on 1/8/18.
 */
class MainAdapter(var photos : List<Photo>,
                  var clickListener: View.OnClickListener) : RecyclerView.Adapter<MainAdapter.PhotoViewHolder>() {

    fun getPhoto(adapterPosition: Int) : Photo {
        return photos[adapterPosition]

    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.photo_item, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder?, position: Int) {
        val photo = photos[position]
        holder?.tags?.text = photo.tags
        holder?.likes?.text = photo.likes.toString()
        holder?.favorites?.text = photo.favorites.toString()
        if(photo.previewURL.isNotEmpty()){
            Glide.with(holder?.tags?.context)
                    .load(photo.previewURL)
                    .into(holder?.photo_item)
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tags : TextView
        var likes : TextView
        var favorites : TextView
        var photo_item : ImageView

        init {
            if(clickListener != null){
                itemView.setOnClickListener(clickListener)
            }
            itemView.tag = this
            tags = itemView.findViewById(R.id.tags)
            likes = itemView.findViewById(R.id.likes)
            favorites = itemView.findViewById(R.id.favorites)
            photo_item = itemView.findViewById(R.id.photo_item)
        }

    }

}