package com.winnews.justwin.ui.game

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import com.winnews.justwin.model.GameModel
import com.winnews.justwin.model.PlayerModel
import com.winnews.justwin.resultdata.DataHelper
import com.winnews.justwin.resultdata.DataTable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GamePresenter(val view: GameContract.View) : GameContract.Presenter {

    private var answerClick: Int = 0
    private val dbTable: DataTable = DataTable()
    private lateinit var model: GameModel
    private var isWin = false
    private lateinit var user: PlayerModel
    var position: Int = 0
    private lateinit var list: ArrayList<GameModel>

    override fun onAnswerClick(position: Int, context: Context) {
        this.answerClick = position
        if (position == model.rightAnswer.toInt()){
            showWin()
        }else{
            showFail(context)
        }
    }

    private fun showFail(context: Context) {
        if (Build.VERSION.SDK_INT >= 26) {
            (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
                VibrationEffect.createOneShot(
                    150,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(150)
        }

        isWin = false
        user.minusHeals()
        view.setBtnAnim()
    }

    private fun showWin() {
        isWin = true
        user.addScore()
        view.setBtnAnim()
    }

    override fun loadQuest(questPosition: Int, context: Context?): GameModel {
        this.position = questPosition
        user = PlayerModel
        val database = DataHelper(context!!).writableDatabase
        list = dbTable.loadGame(database)
        model = list[questPosition]
        return model
    }

    override fun isWin(): Boolean {
        return isWin
    }

    override fun getHeals(): String {
        return user.getHeals().toString()
    }

    override fun getAnswerClick(): Int {
        return answerClick
    }

    override fun isHealsNotNull(): Boolean {
        return user.getHeals() > 0
    }

    override fun isNextQuest(): Boolean {
        return (position + 1) < list.size
    }

    override fun getNextQuest() {
        if ((position + 1) < list.size){
            position++
        }
        else position = 0
        view.nextQuest(position)
    }

    override fun saveResults(context: Context) {
        user.setDateFinish()
        val currentDate = SimpleDateFormat("dd-MM-yyyy \n HH:mm", Locale.getDefault()).format(Date())
        val mills = kotlin.math.abs(user.getDateStart().time - user.getDateFinish().time)

        val hours = (mills / (1000 * 60 * 60)).toInt()
        val min = (mills / (1000 * 60)).toInt() % 60
        val sec = (mills / 1000).toInt() % 60.toLong()

        val gameTime = if (hours <= 0){
            "$min:$sec"
        } else{
            "$hours:$min:$sec"
        }

        val database = DataHelper(context).writableDatabase
        dbTable.addResult(
            database,
            currentDate,
            user.getScore().toString(),
            (position + 1).toString(),
            user.getHeals() > 0,
            gameTime
        )
    }
}