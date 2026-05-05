package com.anmp_32bit.habittracker.model

data class Habit (
    val id: Int,
    val name: String,
    val description: String,
    var currentProgress: Int,
    val goal: Int,
    val unit: String,
    val iconResId: Int
)

