package com.winnews.justwin.model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object PlayerModel {
    private var heals: Int = 5
    private var score: Int = 0
    private var startTime: String = ""
    private var stopTime: String = ""
    private var dateStart: Date = Date()
    private var dateFinish: Date = Date()
    @SuppressLint("SimpleDateFormat")
    var sdf: SimpleDateFormat = SimpleDateFormat("hh:mm:ss aa")

    fun clear(){
        heals = 5
        score = 0
        startTime = ""
        stopTime = ""
        dateStart = Date()
        dateFinish = Date()
    }

    fun minusHeals(){
        heals--
    }

    fun getHeals(): Int{
        return heals
    }

    fun addScore(){
        score++
    }

    fun getScore() : Int{
        return score
    }

    fun setDateStart(){
        val systemDate = Calendar.getInstance().time
        val myDate = sdf.format(systemDate)
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        dateStart = sdf.parse(myDate)
    }

    fun setDateFinish(){
        val systemDate = Calendar.getInstance().time
        val myDate = sdf.format(systemDate)
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        dateFinish = sdf.parse(myDate)
    }

    fun getDateStart() : Date {
        return dateStart
    }

    fun getDateFinish() : Date {
        return dateFinish
    }
}