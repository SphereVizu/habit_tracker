package com.anmp_32bit.habittracker.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anmp_32bit.habittracker.databinding.HabitListItemBinding
import com.anmp_32bit.habittracker.model.Habit
import androidx.core.graphics.toColorInt

class HabitAdapter(
    var habits: List<Habit>,
    val onPlusClick: (Habit) -> Unit,
    val onMinusClick: (Habit) -> Unit
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    inner class HabitViewHolder(val binding: HabitListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        with(holder.binding) {
            txtHabitName.text = habit.name
            txtHabitDesc.text = habit.description
            txtProgressRatio.text = "${habit.currentProgress} / ${habit.goal} ${habit.unit}"

            progressBar.max = habit.goal
            progressBar.progress = habit.currentProgress
            imgIcon.setImageResource(habit.iconResId)

            if (habit.currentProgress >= habit.goal) {
                txtStatus.text = "Completed"
                txtStatus.setTextColor("#4CAF50".toColorInt())
                progressBar.progressTintList = android.content.res.ColorStateList.valueOf("#4CAF50".toColorInt())
                holder.binding.root.setCardBackgroundColor("#E8F5E9".toColorInt())
            } else {
                txtStatus.text = "In Progress"
                txtStatus.setTextColor("#2196F3".toColorInt())
                progressBar.progressTintList = android.content.res.ColorStateList.valueOf("#2196F3".toColorInt())
                holder.binding.root.setCardBackgroundColor("#FFFFFF".toColorInt())
            }

            if (habit.currentProgress >= habit.goal) {
                btnPlus.isEnabled = false
                btnPlus.alpha = 0.3f
            } else {
                btnPlus.isEnabled = true
                btnPlus.alpha = 1.0f
            }

            if (habit.currentProgress <= 0) {
                btnMinus.isEnabled = false
                btnMinus.alpha = 0.3f
            } else {
                btnMinus.isEnabled = true
                btnMinus.alpha = 1.0f
            }

            btnPlus.setOnClickListener { onPlusClick(habit) }
            btnMinus.setOnClickListener { onMinusClick(habit) }
        }
    }

    override fun getItemCount() = habits.size
}