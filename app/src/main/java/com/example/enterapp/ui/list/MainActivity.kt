package com.example.enterapp.ui.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.enterapp.*
import com.example.enterapp.adapter.CarsAdapter
import com.example.enterapp.data.Car
import com.example.enterapp.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemClickListener, ItemLongClickListener {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var carsAdapter : CarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createList()

        viewModel.testDateLiveData.observe(this,  {
            carsAdapter.setData(it)
        })

        rlSwipe.setOnRefreshListener(OnRefreshListener {
            carsAdapter.setData(viewModel.getLastData())
            rlSwipe.isRefreshing = false
        })
    }

    private fun createList() {
        carsAdapter = CarsAdapter()
        carsAdapter.setItemClickListener(this)
        carsAdapter.setItemLongClickListener(this)
        val listlayoutManager = LinearLayoutManager(this)
        rvListOfCars.apply {
            layoutManager = listlayoutManager
            setHasFixedSize(true)
            adapter = carsAdapter
        }
    }

    override fun onItemClick(car: Car) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(CAR,car)
        startActivity(intent)
    }

    override fun onLongItemClick(car: Car) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.positive){ dialod, id ->
                    carsAdapter.deleteItem(car)
                    Toast.makeText(context, R.string.success,Toast.LENGTH_SHORT).show()
                }
            setNegativeButton(R.string.negative){ dialog, id -> dialog.cancel()}
        }
        val dialog = builder.create()
        dialog.show()
    }

    companion object{
        const val CAR = "CAR"
    }
}