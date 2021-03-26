package com.example.enterapp


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class DataRepository @Inject constructor() {

    val carsName = listOf("Mazda","Honda","BMW","Nissan","Audi")

    val carsDescription = listOf(
        "Simple family cars",
        "Pure sport cars for young people",
        "cars living at service stations",
        "Boring cars an exception GTR",
        "Cars for show-off")

    val carPhotos = listOf(
        "https://mazda.ua/images/doc/d/e/deb89cd-mzd-site-apr20-slider-1200x630-cx-30.jpg",
        "https://cdn.motor1.com/images/mgl/MQWXX/s1/2020-honda-civic-si-coupe.jpg",
        "https://avtonovostidnya.ru/wp-content/uploads/2020/06/bmw-4-series-1-e1591120875299.jpg",
        "https://funart.pro/uploads/posts/2019-10/1572418525_novinki-nissan-10.jpg",
        "https://aerosus.ru/media/catalog/category/400px-audi-a4-b8.jpg"
    )

    suspend fun getNewData(): List<Car>{
        var list = mutableListOf<Car>()

        withContext(Dispatchers.IO){
            carsName.forEachIndexed { index, name ->
                list.add(index,Car(name,carsDescription[index],carPhotos[index],Date()))
            }
        }
        return list
    }
}