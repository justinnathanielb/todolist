package org.ibda.myfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.ibda.myfragment.databinding.MainPageFragBinding

class AFragment : Fragment() {

    private val taskViewModel: TaskViewModel by activityViewModels()
    private var _binding: MainPageFragBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskViewModel.getTasks()  // Fetch tasks when fragment is created
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainPageFragBinding.inflate(inflater, container, false)

        binding.newTasksTotal.setOnClickListener {
            logClickedTextView("New Tasks")
            navigateToCFragmentWithStage("new")
        }
        binding.inProgressTasksTotal.setOnClickListener {
            logClickedTextView("In Progress Tasks")
            navigateToCFragmentWithStage("in progress")
        }
        binding.doneTasksTotal.setOnClickListener {
            logClickedTextView("Done Tasks")
            navigateToCFragmentWithStage("done")
        }
        binding.buttonAddData.setOnClickListener {
            navigateToAddTaskFragment()
        }

        observeTaskCounts()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Avoid memory leaks
    }

    private fun logClickedTextView(textViewName: String) {
        Log.d("AFragment", "Clicked TextView: $textViewName")
    }

    private fun navigateToCFragmentWithStage(stage: String) {
        Log.d("AFragment", "Navigating to CFragment with stage: $stage")
        val action = AFragmentDirections.actionAFragmentToCFragment(stage)
        findNavController().navigate(action)
    }

    private fun navigateToAddTaskFragment() {
        Log.d("AFragment", "Navigating to AddTaskFragment")
        val action = AFragmentDirections.actionAFragmentToBFragment()
        findNavController().navigate(action)
    }

    private fun observeTaskCounts() {
        taskViewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let {
                val newTasksCount = it.count { task -> task.stage == "new" }
                val inProgressTasksCount = it.count { task -> task.stage == "in progress" }
                val doneTasksCount = it.count { task -> task.stage == "done" }

                updateTaskCount(binding.newTasksTotal, "New Tasks", newTasksCount)
                updateTaskCount(binding.inProgressTasksTotal, "In Progress Tasks", inProgressTasksCount)
                updateTaskCount(binding.doneTasksTotal, "Done Tasks", doneTasksCount)
            } ?: run {
                Log.e("AFragment", "Task list is null")
            }
        })
    }

    private fun updateTaskCount(textView: TextView, category: String, count: Int) {
        textView.text = "$category: $count"
        Log.d("AFragment", "Updated $category count: $count")
    }
}
