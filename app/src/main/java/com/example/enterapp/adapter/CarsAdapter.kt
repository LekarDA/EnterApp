
package com.example.enterapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enterapp.data.Car
import com.example.enterapp.ui.list.ItemClickListener
import com.example.enterapp.ui.list.ItemLongClickListener
import com.example.enterapp.R

class CarsAdapter: RecyclerView.Adapter<CarsViewHolder>() {

    private val listOfCars: ArrayList<Car> = ArrayList()
    private var listener: ItemClickListener? = null
    private var longClickListener: ItemLongClickListener? = null
    private var longClickPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_of_car, parent, false)
        return CarsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.initView(listOfCars[position])
        holder.itemView.setOnClickListener {
            listener?.onItemClick(listOfCars[position])
        }
        holder.itemView.setOnLongClickListener {
            longClickPosition = position
            longClickListener?.onLongItemClick(listOfCars[position])
            true
        }
    }

    override fun getItemCount() = listOfCars.size

    fun setData(cars: List<Car>?){
        listOfCars.clear()
        cars?.let { listOfCars.addAll(cars) }
        notifyDataSetChanged()
    }

    fun deleteItem(car: Car){
        listOfCars.remove(car)
        notifyItemRemoved(longClickPosition)
        notifyItemRangeChanged(longClickPosition, getItemCount())
    }

    fun setItemClickListener(clicklistener: ItemClickListener?) {
        this.listener = clicklistener
    }

    fun setItemLongClickListener(clicklistener: ItemLongClickListener?) {
        longClickListener = clicklistener
    }
}