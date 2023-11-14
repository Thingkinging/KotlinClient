package com.kwh.dailyq.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kwh.dailyq.api.response.Answer
import com.kwh.dailyq.api.response.QuestionAndAnswer
import com.kwh.dailyq.databinding.ItemAnswerBinding
import com.kwh.dailyq.databinding.ItemUserAnswerCardBinding
import com.kwh.dailyq.ui.profile.UserAnswerViewHolder

class AnswerAdapter(context: Context) : RecyclerView.Adapter<AnswerViewHolder>() {

    val inflater = LayoutInflater.from(context)

    var items: List<Answer>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(ItemAnswerBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(items!![position])
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}