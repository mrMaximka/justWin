package com.winnews.justwin.ui.game

import android.content.Context
import com.winnews.justwin.model.GameModel

interface GameContract {
    interface View {
        fun setBtnAnim()
        fun nextQuest(position: Int)
    }
    interface Presenter {
        fun onAnswerClick(position: Int, context: Context)
        fun loadQuest(questPosition: Int, context: Context?): GameModel
        fun isWin(): Boolean
        fun getHeals(): String
        fun getAnswerClick(): Int
        fun isHealsNotNull(): Boolean
        fun isNextQuest(): Boolean
        fun getNextQuest()
        fun saveResults(context: Context)
    }
}