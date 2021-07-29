package com.winnews.justwin.ui.result

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.winnews.justwin.databinding.ItemResultBinding
import com.winnews.justwin.model.ResultModel

class ResultAdapter (private val context: Context) : RecyclerView.Adapter<ResultViewHolder>() {

    private var data: ArrayList<ResultModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemResultBinding = ItemResultBinding.inflate(layoutInflater, parent, false)
        return ResultViewHolder(itemResultBinding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(data[position], context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setList(data: ArrayList<ResultModel>){
        this.data = data
        notifyDataSetChanged()
    }
}