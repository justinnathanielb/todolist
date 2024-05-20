import org.ibda.myfragment.TaskService


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:5000/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val taskService: TaskService by lazy {
        retrofit.create(TaskService::class.java)
    }
}

//package org.ibda.myfragment
//import android.app.TaskInfo
//import android.os.Build
//import androidx.annotation.RequiresApi
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import org.ibda.myfragment.TaskService
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//
//object ApiClient {
//    private const val BASE_URL = "http://your-flask-server-url/"
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()
//
//    val taskService: TaskService = retrofit.create(TaskService::class.java)
//
//}
