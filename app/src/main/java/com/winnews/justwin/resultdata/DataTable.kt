package com.winnews.justwin.resultdata

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.winnews.justwin.model.GameModel
import com.winnews.justwin.model.ResultModel
import java.lang.Exception

class DataTable {

    fun loadGame(db: SQLiteDatabase): ArrayList<GameModel>{
        val cursor: Cursor = db.query("winquest",
            null, null, null, null, null, null)

        val list: ArrayList<GameModel> = ArrayList()

        cursor.moveToFirst()
        val id = cursor.getColumnIndex("id")
        val answer1 = cursor.getColumnIndex("answer_1")
        val answer2 = cursor.getColumnIndex("answer_2")
        val answer3 = cursor.getColumnIndex("answer_3")
        val rightAnswer = cursor.getColumnIndex("right_answer")

        try {
            do {
                val model = GameModel()
                model.id = cursor.getString(id)
                model.answer1 = cursor.getString(answer1)
                model.answer2 = cursor.getString(answer2)
                model.answer3 = cursor.getString(answer3)
                model.rightAnswer = cursor.getString(rightAnswer)
                model.image = "game_" + model.id

                list.add(model)
            }while (cursor.moveToNext())
        }catch (e: Exception){
        }
        cursor.close()

        return list
    }

    fun loadResults(db: SQLiteDatabase): ArrayList<ResultModel>{
        val cursor: Cursor = db.query("result",
            null, null, null, null, null, null)

        val list: ArrayList<ResultModel> = ArrayList()

        cursor.moveToLast()
        val date = cursor.getColumnIndex("date")
        val score = cursor.getColumnIndex("score")
        val lastQuest = cursor.getColumnIndex("last_quest")
        val gameTime = cursor.getColumnIndex("game_time")
        val isWin = cursor.getColumnIndex("win")

        try {
            do {
                val model = ResultModel()
                model.date = cursor.getString(date)
                model.score = cursor.getString(score)
                model.lastQuest = cursor.getString(lastQuest)
                model.questTime = cursor.getString(gameTime)
                model.isWin = cursor.getString(isWin).toBoolean()

                list.add(model)
            }while (cursor.moveToPrevious())
        }catch (e: Exception){
        }
        cursor.close()

        return list
    }

    fun addResult(database: SQLiteDatabase, date: String, score: String, lastQuest: String, win: Boolean, gameTime: String){
        database.execSQL("INSERT INTO result (\n" +
                "                       date,\n" +
                "                       score,\n" +
                "                       last_quest,\n" +
                "                       win,\n" +
                "                       game_time\n" +
                "                   )\n" +
                "                   VALUES (\n" +
                "                       '$date',\n" +
                "                       '$score',\n" +
                "                       '$lastQuest',\n" +
                "                       '$win',\n" +
                "                       '$gameTime'\n" +
                "                   );")
    }
}