package com.example.enterapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Car(
    var title: String,
    var description: String,
    var image: String,
    var creationDate: Date
):Parcelable