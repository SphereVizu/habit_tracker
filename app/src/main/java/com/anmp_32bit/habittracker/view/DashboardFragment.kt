package com.anmp_32bit.habittracker.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anmp_32bit.habittracker.R
import com.anmp_32bit.habittracker.databinding.FragmentDashboardBinding
import com.anmp_32bit.habittracker.viewmodel.DashboardViewModel
import androidx.navigation.fragment.findNavController

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: DashboardViewModel
    private val habitAdapter = HabitAdapter(arrayListOf(),
        { habit -> viewModel.updateProgress(habit.id, 1) },
        { habit -> viewModel.updateProgress(habit.id, -1) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        binding.recyclerViewHabit.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = habitAdapter
        }

        viewModel.habitListLD.observe(viewLifecycleOwner) { newList ->
            habitAdapter.habits = newList
            habitAdapter.notifyDataSetChanged()
        }
        viewModel.fetch()

        binding.fabAdd.setOnClickListener {

            val action =
                DashboardFragmentDirections
                    .actionDashboardFragmentToNewHabitFragment()

            findNavController().navigate(action)
        }
    }
}