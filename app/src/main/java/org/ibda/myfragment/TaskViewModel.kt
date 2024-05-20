package org.ibda.myfragment

import ApiClient.taskService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<TaskInfo>>()
    val tasks: LiveData<List<TaskInfo>> get() = _tasks

    init {
        getTasks()
    }

    fun getTasks() {
        val call = taskService.getTasks()

        call.enqueue(object : Callback<List<TaskInfo>> {
            override fun onResponse(call: Call<List<TaskInfo>>, response: Response<List<TaskInfo>>) {
                if (response.isSuccessful) {
                    _tasks.value = response.body()
                    Log.d("TaskViewModel", "Tasks loaded successfully: ${response.body()}")
                } else {
                    Log.e("TaskViewModel", "Failed to load tasks: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<TaskInfo>>, t: Throwable) {
                Log.e("TaskViewModel", "Error fetching tasks", t)
            }
        })
    }

    fun addTask(task: TaskInfo) {
        val call = taskService.addTask(task)

        call.enqueue(object : Callback<TaskInfo> {
            override fun onResponse(call: Call<TaskInfo>, response: Response<TaskInfo>) {
                if (response.isSuccessful) {
                    Log.d("TaskViewModel", "Task added successfully: ${response.body()}")
                    getTasks()  // Refresh the task list after adding a new task
                } else {
                    Log.e("TaskViewModel", "Failed to add task: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TaskInfo>, t: Throwable) {
                Log.e("TaskViewModel", "Error adding task", t)
            }
        })
    }

    fun updateTaskStage(task: TaskInfo, newStage: String) {
        val updatedTask = task.copy(stage = newStage)
        val call = taskService.updateTask(task.id!!, updatedTask) // Assuming task.id is not null

        call.enqueue(object : Callback<TaskInfo> {
            override fun onResponse(call: Call<TaskInfo>, response: Response<TaskInfo>) {
                if (response.isSuccessful) {
                    Log.d("TaskViewModel", "Task updated successfully: ${response.body()}")
                    getTasks()  // Refresh the task list after updating a task
                } else {
                    Log.e("TaskViewModel", "Failed to update task: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TaskInfo>, t: Throwable) {
                Log.e("TaskViewModel", "Error updating task", t)
            }
        })
    }
}
