package com.winnews.justwin.resultdata

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataHelper (private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val ASSETS_PATH = "databases"
        const val DATABASE_NAME = "winquest"
        const val DATABASE_VERSION = 1
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE winquest (\n" +
                    "    id           INTEGER PRIMARY KEY\n" +
                    "                         NOT NULL,\n" +
                    "    answer_1     STRING  NOT NULL,\n" +
                    "    answer_2     STRING  NOT NULL,\n" +
                    "    answer_3     STRING  NOT NULL,\n" +
                    "    right_answer INTEGER NOT NULL\n" +
                    ");"
        )

        db?.execSQL(
            "CREATE TABLE result (\n" +
                    "    id         INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    date       STRING  NOT NULL,\n" +
                    "    score      STRING,\n" +
                    "    last_quest STRING,\n" +
                    "    win        BOOLEAN NOT NULL,\n" +
                    "    game_time  STRING  NOT NULL\n" +
                    ");"
        )

        db?.execSQL(
            "INSERT INTO winquest (\n" +
                    "                     id,\n" +
                    "                     answer_1,\n" +
                    "                     answer_2,\n" +
                    "                     answer_3,\n" +
                    "                     right_answer\n" +
                    "                 )\n" +
                    "                 VALUES (\n" +
                    "                     '1',\n" +
                    "                     'Бобслей',\n" +
                    "                     'Скелетон',\n" +
                    "                     'Слалом',\n" +
                    "                     '2'\n" +
                    "                 ),(\n" +
                    "                     '2',\n" +
                    "                     'Франция',\n" +
                    "                     'Италия',\n" +
                    "                     'Португалия',\n" +
                    "                     '1'\n" +
                    "                 ),(\n" +
                    "                    '3',\n" +
                    "                    'Хоккейное',\n" +
                    "                    'Футбольное',\n" +
                    "                    'Бейсбольное',\n" +
                    "                    '1'\n" +
                    "                 ),(\n" +
                    "                    '4',\n" +
                    "                    'Индия',\n" +
                    "                    'Италия',\n" +
                    "                    'Шотландния',\n" +
                    "                    '3'\n" +
                    "                 ),(\n" +
                    "                    '5',\n" +
                    "                    'Афины',\n" +
                    "                    'Париж',\n" +
                    "                    'Мадрид',\n" +
                    "                    '1'\n" +
                    "                 ),(\n" +
                    "                    '6',\n" +
                    "                    'Греция',\n" +
                    "                    'Англия',\n" +
                    "                    'Испания',\n" +
                    "                    '2'\n" +
                    "                 ),(\n" +
                    "                    '7',\n" +
                    "                    'Бутсы',\n" +
                    "                    'Кроссовки',\n" +
                    "                    'Кеды',\n" +
                    "                    '1'\n" +
                    "                 ),(\n" +
                    "                    '8',\n" +
                    "                    'Баттерфляй',\n" +
                    "                    'Кроль',\n" +
                    "                    'Брасс',\n" +
                    "                    '3'\n" +
                    "                 ),(\n" +
                    "                    '9',\n" +
                    "                    'Конь',\n" +
                    "                    'Брусья',\n" +
                    "                    'Бревно',\n" +
                    "                    '3'\n" +
                    "                 ),(\n" +
                    "                    '10',\n" +
                    "                    'Теннис',\n" +
                    "                    'Бадминтон',\n" +
                    "                    'Хоккей',\n" +
                    "                    '2'\n" +
                    "                 ),(\n" +
                    "                    '11',\n" +
                    "                    'Белый',\n" +
                    "                    'Голубой',\n" +
                    "                    'Черный',\n" +
                    "                    '1'\n" +
                    "                 ),(\n" +
                    "                    '12',\n" +
                    "                    'Чаша',\n" +
                    "                    'Тарелка',\n" +
                    "                    'Блюдце',\n" +
                    "                    '2'\n" +
                    "                 ),(\n" +
                    "                    '13',\n" +
                    "                    'Плавание',\n" +
                    "                    'Бег',\n" +
                    "                    'Гребля',\n" +
                    "                    '3'\n" +
                    "                 ),(\n" +
                    "                    '14',\n" +
                    "                    'Джиу-джитсу',\n" +
                    "                    'Карате',\n" +
                    "                    'Дзюдо',\n" +
                    "                    '3'\n" +
                    "                 ),(\n" +
                    "                    '15',\n" +
                    "                    'Желтую майку',\n" +
                    "                    'Синюю майку',\n" +
                    "                    'Красный шлем',\n" +
                    "                    '1'\n" +
                    "                 ),(\n" +
                    "                    '16',\n" +
                    "                    'Крокет',\n" +
                    "                    'Городки',\n" +
                    "                    'Лапта',\n" +
                    "                    '1'\n" +
                    "                 ),(\n" +
                    "                    '17',\n" +
                    "                    'Мяч для регби',\n" +
                    "                    'Мяч для бейсбола',\n" +
                    "                    'Мяч для тенниса',\n" +
                    "                    '1'\n" +
                    "                 ),(\n" +
                    "                    '18',\n" +
                    "                    'Штрафной удар',\n" +
                    "                    'Мяч вне поля',\n" +
                    "                    'Удаление игрока',\n" +
                    "                    '2'\n" +
                    "                 ),(\n" +
                    "                    '19',\n" +
                    "                    'За сосиской',\n" +
                    "                    'За котом',\n" +
                    "                    'За механическим зайцем',\n" +
                    "                    '3'\n" +
                    "                 ),(\n" +
                    "                    '20',\n" +
                    "                    'Метание копья',\n" +
                    "                    'Прыжки с шестом',\n" +
                    "                    'Гимнастика',\n" +
                    "                    '2'\n" +
                    "                 ),(\n" +
                    "                    '21',\n" +
                    "                    'Бейсбол',\n" +
                    "                    'Футбол',\n" +
                    "                    'Поло',\n" +
                    "                    '3'\n" +
                    "                 ),(\n" +
                    "                    '22',\n" +
                    "                    'Пьедестал',\n" +
                    "                    'Скамья',\n" +
                    "                    'Подиум',\n" +
                    "                    '1'\n" +
                    "                 ),(\n" +
                    "                    '23',\n" +
                    "                    '50',\n" +
                    "                    '75',\n" +
                    "                    '90',\n" +
                    "                    '2'\n" +
                    "                 ),(\n" +
                    "                    '24',\n" +
                    "                    'Бобслеист',\n" +
                    "                    'Гонщик',\n" +
                    "                    'Скелетонец',\n" +
                    "                    '1'\n" +
                    "                 ),(\n" +
                    "                    '25',\n" +
                    "                    'Конь',\n" +
                    "                    'Кольца',\n" +
                    "                    'Перекладина',\n" +
                    "                    '3'\n" +
                    "                    );"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}