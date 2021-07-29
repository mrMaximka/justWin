package com.winnews.justwin.ui.result

import android.content.Context
import com.winnews.justwin.model.ResultModel
import com.winnews.justwin.resultdata.DataHelper
import com.winnews.justwin.resultdata.DataTable
import java.util.ArrayList

class ResultPresenter(val view: ResultContract.View) : ResultContract.Presenter {

    private val dbTable: DataTable = DataTable()

    override fun loadResults(context: Context?): ArrayList<ResultModel> {
        val database = DataHelper(context!!).writableDatabase
        return dbTable.loadResults(database)
    }
}