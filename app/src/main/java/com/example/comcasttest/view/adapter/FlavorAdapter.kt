package com.example.comcasttest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comcasttest.databinding.FlavorItemViewBinding
import com.example.comcasttest.model.Response
import com.example.comcasttest.model.Topic
import com.squareup.picasso.Picasso

class FlavorAdapter(private var dataSet: Response?, private val listener: (Topic) -> Unit) :
    RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder>() {

        class FlavorViewHolder(private val binding: FlavorItemViewBinding):
            RecyclerView.ViewHolder(binding.root){

                fun onBind(dataItem: Topic, callback: (Topic)-> Unit){
                    Picasso.get().load(dataItem.Icon.URL)
                }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlavorViewHolder {
        return FlavorViewHolder(
            FlavorItemViewBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: FlavorViewHolder, position: Int) {
        dataSet?.let {
            holder.onBind(it.RelatedTopics[position], listener)
        }
    }

    override fun getItemCount(): Int {
        return dataSet?.RelatedTopics?.size ?: 0
    }

    fun setDataSet(data: Response?) {
        this.dataSet = data
        notifyDataSetChanged()
    }
}