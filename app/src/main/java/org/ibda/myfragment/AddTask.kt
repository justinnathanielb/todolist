package org.ibda.myfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController

class BFragment : Fragment() {

    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_task, container, false)

        val taskNameEditText = view.findViewById<EditText>(R.id.task_name)
        val taskPriorityEditText = view.findViewById<EditText>(R.id.task_priority)
        val taskStageEditText = view.findViewById<EditText>(R.id.task_stage)
        val taskDescriptionEditText = view.findViewById<EditText>(R.id.task_description)
        val taskStartTimeEditText = view.findViewById<EditText>(R.id.task_start_time)
        val taskEndTimeEditText = view.findViewById<EditText>(R.id.task_end_time)
        val taskDurationEditText = view.findViewById<EditText>(R.id.task_duration)
        val addTaskButton = view.findViewById<Button>(R.id.button_add_task)

        addTaskButton.setOnClickListener {
            val taskName = taskNameEditText.text.toString()
            val taskPriority = taskPriorityEditText.text.toString()
            val taskStage = taskStageEditText.text.toString()
            val taskDescription = taskDescriptionEditText.text.toString()
            val taskStartTime = taskStartTimeEditText.text.toString()
            val taskEndTime = taskEndTimeEditText.text.toString()
            val taskDuration = taskDurationEditText.text.toString().toIntOrNull()

            val newTask = TaskInfo(
                name = taskName,
                description = taskDescription,
                priority = taskPriority,
                stage = taskStage,
                createdTime = taskStartTime,
                finishedTime = taskEndTime,
                duration = taskDuration
            )

            taskViewModel.addTask(newTask)

            // Navigate back to AFragment or wherever necessary
            view.findNavController().navigateUp()
        }

        return view
    }
}
