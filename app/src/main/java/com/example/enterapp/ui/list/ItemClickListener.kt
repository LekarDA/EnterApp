package com.example.enterapp.ui.list

import com.example.enterapp.data.Car

interface ItemClickListener {
    fun onItemClick(car: Car)
}