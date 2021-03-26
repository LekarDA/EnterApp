package com.example.enterapp.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.enterapp.data.Car
import com.example.enterapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_of_car.view.*

class CarsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun initView(car: Car) {
        Picasso.get().load(car.image)
            .placeholder(R.drawable.no_photo).error(R.drawable.ic_error).into(itemView.ivCarPhoto)
        itemView.tvCarName?.text = car.title
    }
}