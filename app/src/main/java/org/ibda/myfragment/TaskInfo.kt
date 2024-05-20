package org.ibda.myfragment

import com.squareup.moshi.Json

data class TaskInfo(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description: String,
    @Json(name = "priority") val priority: String,
    @Json(name = "stage") val stage: String,
    @Json(name = "createdtime") val createdTime: String,
    @Json(name = "finishedtime") val finishedTime: String?,
    @Json(name = "duration") val duration: Int? = null // Make duration nullable
)
