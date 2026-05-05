package com.anmp_32bit.habittracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anmp_32bit.habittracker.model.Habit
import com.anmp_32bit.habittracker.model.HabitStorage
class DashboardViewModel : ViewModel() {
    val habitListLD = MutableLiveData<ArrayList<Habit>>()

    fun fetch() {
        val dummy = arrayListOf(
            Habit(1, "Drink Water", "Stay hydrated throughout the day", 3, 8, "glasses", com.anmp_32bit.habittracker.R.drawable.ic_water),
            Habit(2, "Exercise", "Daily workout routine", 30, 30, "minutes", com.anmp_32bit.habittracker.R.drawable.ic_exercise)
        )
        if (HabitStorage.habitList.isEmpty()) {
            HabitStorage.habitList.addAll(dummy)
        }

        habitListLD.value = HabitStorage.habitList
    }
    fun addHabit(habit: Habit) {

        HabitStorage.habitList.add(habit)

        habitListLD.value = HabitStorage.habitList
    }

    fun updateProgress(habitId: Int, delta: Int) {
        val list = habitListLD.value
        val habit = list?.find { it.id == habitId }
        habit?.let {
            val newProgress = it.currentProgress + delta
            if (newProgress in 0..it.goal) {
                it.currentProgress = newProgress
                habitListLD.value = list
            }
        }
    }
}