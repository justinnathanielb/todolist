package org.ibda.myfragment

import retrofit2.Call
import retrofit2.http.*

interface TaskService {
    @GET("tasks")
    fun getTasks(): Call<List<TaskInfo>>

    @POST("tasks")
    fun addTask(@Body task: TaskInfo): Call<TaskInfo>

    @PUT("tasks/{id}")
    fun updateTask(@Path("id") id: Int, @Body task: TaskInfo): Call<TaskInfo>
}
