package com.example.persontest.service

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class DataFormatter
 */
class DataFormatter {

    val formatter = SimpleDateFormat("dd/MM/yyyy")

    fun dateToString( date: Date): String {
        return formatter.format(date);
    }

    fun stringToDate(str: String) :Date{
        try {
            return formatter.parse(str)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return throw Exception("Wrong date format");
    }
}