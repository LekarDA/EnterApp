package com.example.enterapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enterapp.R
import com.example.enterapp.data.Car
import com.example.enterapp.ui.list.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val car = intent.getParcelableExtra<Car>(MainActivity.CAR)
        Picasso.get().load(car?.image)
            .placeholder(R.drawable.no_photo).error(R.drawable.ic_error).into(ivDetailPhoto)
        tvCarName.text = car?.title
        tvCarDescription.text = car?.description
        tvDate.text = car?.creationDate?.getStringTimeStampWithDate()
    }
}

fun Date.getStringTimeStampWithDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(this)
}