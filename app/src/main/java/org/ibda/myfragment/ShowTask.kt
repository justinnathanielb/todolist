package org.ibda.myfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CFragment : Fragment() {

    private val taskViewModel: TaskViewModel by activityViewModels()

    private lateinit var myRV: RecyclerView
    private lateinit var addBtn: Button
    private lateinit var backBtn: Button
    private lateinit var importantBtn: Button
    private lateinit var urgentBtn: Button
    private lateinit var normalBtn: Button
    private lateinit var _adapter: MyRecyclerViewAdapter

    private var _stage: String = ""
    private var stage: String
        get() = _stage
        set(value) {
            _stage = value
            Log.d("stage", "$value apa nihh")
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.show_task, container, false)

        myRV = view.findViewById(R.id.myRecyclerView)
        addBtn = view.findViewById(R.id.addBtn)
        backBtn = view.findViewById(R.id.backBtn)
        importantBtn = view.findViewById(R.id.Filter_Important)
        urgentBtn = view.findViewById(R.id.Filter_Urgent)
        normalBtn = view.findViewById(R.id.Filter_Normal)

        myRV.layoutManager = LinearLayoutManager(requireContext())

        _adapter = MyRecyclerViewAdapter(listOf(), taskViewModel)
        myRV.adapter = _adapter

        // Retrieve stage from arguments using Safe Args
        arguments?.let {
            val args = CFragmentArgs.fromBundle(it)
            stage = args.stage
        }

        taskViewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            // Filter tasks by stage
            val filteredTasks = tasks.filter { it.stage == stage }
            _adapter.updateData(filteredTasks)
        })

        importantBtn.setOnClickListener {
            _adapter.filterData("important")
        }

        urgentBtn.setOnClickListener {
            _adapter.filterData("urgent")
        }

        normalBtn.setOnClickListener {
            _adapter.filterData("normal")
        }

        backBtn.setOnClickListener {
            view.findNavController().navigateUp()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            // Print all tasks
            tasks.forEachIndexed { index, task ->
                Log.d("TaskViewModel", "Task $index: $task")
            }
        })
    }
}
