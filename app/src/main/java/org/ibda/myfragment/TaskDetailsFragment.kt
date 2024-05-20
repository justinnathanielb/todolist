package org.ibda.myfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class TaskDetailFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_details, container, false)
        val taskName = arguments?.getString("taskName")
        val taskPriority = arguments?.getString("taskPriority")

        view.findViewById<TextView>(R.id.taskNameTextView).text = taskName
        view.findViewById<TextView>(R.id.taskPriorityTextView).text = taskPriority

        return view
    }
}
