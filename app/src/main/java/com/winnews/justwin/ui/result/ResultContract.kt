package com.winnews.justwin.ui.result

import android.content.Context
import com.winnews.justwin.model.ResultModel
import java.util.ArrayList

interface ResultContract {
    interface View {

    }
    interface Presenter {
        fun loadResults(context: Context?): ArrayList<ResultModel>

    }
}