package com.kebob.geta

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kebob.geta.data.Meal

class TimeListAdapter(private val dataSet: Array<Meal>)
    : RecyclerView.Adapter<TimeListAdapter.ViewHolder>() {

    class ViewHolder(view: View, listener: OnItemLongClickListener) : RecyclerView.ViewHolder(view) {
        val tvMeal: TextView = view.findViewById(R.id.tv_meal)
        val tvTime: TextView = view.findViewById(R.id.tv_time_range)

        init {
            view.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemLongClick(view, position)
                }

                false
            }
        }
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int)
    }

    lateinit var onItemLongClickListener: OnItemLongClickListener

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_registered_meal_list, viewGroup, false)

        return ViewHolder(view, onItemLongClickListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvMeal.text = dataSet[position].mealType
        holder.tvTime.text = "${dataSet[position].startTime} ~ ${dataSet[position].endTime}"
    }

    override fun getItemCount() = dataSet.size

    fun setOnMyItemLongClickListener(listener: OnItemLongClickListener) {
        this.onItemLongClickListener = listener
    }
}
