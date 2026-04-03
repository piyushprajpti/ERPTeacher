package com.piyushprajpti.feature.hostel.mess.models

data class SpecialEvent(
    val title: String,
    val date: String,
    val description: String,
    val specialMenu: List<String>
)

data class Meal(
    val type: String, // Breakfast, Lunch, Dinner
    val menu: String
)

data class DaySchedule(
    val day: String,
    val meals: List<Meal>
)

data class SatisfactionData(
    val day: String,
    val rating: Float
)
