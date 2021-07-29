package com.winnews.justwin.ui.result

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.winnews.justwin.R
import com.winnews.justwin.databinding.ItemResultBinding
import com.winnews.justwin.model.ResultModel

class ResultViewHolder (private val itemResultBinding: ItemResultBinding) : RecyclerView.ViewHolder(itemResultBinding.root) {

    fun bind(resultModel: ResultModel, context: Context) {
        itemResultBinding.resultDate.text = resultModel.date
        itemResultBinding.resultScore.text = resultModel.score
        itemResultBinding.resultGameTime.text = resultModel.questTime
        if (resultModel.isWin){
            itemResultBinding.resultIsWin.text = "Выигрыш"
            itemResultBinding.resultIsWin.setTextColor(ContextCompat.getColor(context.applicationContext, R.color.green_600))
        }else{
            itemResultBinding.resultIsWin.text = "Проигрыш"
            itemResultBinding.resultIsWin.setTextColor(ContextCompat.getColor(context.applicationContext, R.color.red_600))
        }
    }
}