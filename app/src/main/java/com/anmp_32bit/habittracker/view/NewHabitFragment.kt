package com.anmp_32bit.habittracker.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anmp_32bit.habittracker.R
import com.anmp_32bit.habittracker.databinding.FragmentNewHabitBinding
import com.anmp_32bit.habittracker.model.Habit
import com.anmp_32bit.habittracker.model.HabitStorage

class NewHabitFragment : Fragment(R.layout.fragment_new_habit) {

    private lateinit var binding: FragmentNewHabitBinding

    private val iconNames = arrayOf(
        "Fitness",
        "Water",
        "Book"
    )

    private val iconResources = arrayOf(
        R.drawable.ic_exercise,
        R.drawable.ic_water,
        R.drawable.ic_book
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewHabitBinding.bind(view)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            iconNames
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerIcon.adapter = adapter

        binding.btnCreate.setOnClickListener {

            val name = binding.txtTitle.text.toString()

            val description =
                binding.txtDescription.text.toString()

            val goal =
                binding.txtGoal.text.toString().toInt()

            val unit =
                binding.txtUnit.text.toString()

            val selectedIcon =
                iconResources[
                    binding.spinnerIcon.selectedItemPosition
                ]

            val newHabit = Habit(
                id = HabitStorage.habitList.size + 1,
                name = name,
                description = description,
                currentProgress = 0,
                goal = goal,
                unit = unit,
                iconResId = selectedIcon
            )

            HabitStorage.habitList.add(newHabit)

            findNavController().popBackStack()
        }
    }
}