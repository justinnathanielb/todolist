package org.ibda.myfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(private var myDatas: List<TaskInfo>, private val taskViewModel: TaskViewModel) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    private var filteredDatas: List<TaskInfo> = myDatas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = filteredDatas[position]
        holder.textView.text = task.name

        holder.itemButton.setOnClickListener {
            when (task.stage) {
                "new" -> taskViewModel.updateTaskStage(task, "in progress")
                "in progress" -> taskViewModel.updateTaskStage(task, "done")
                "done" -> showTaskDetails(holder.itemView.context, task)
            }
        }

        holder.itemView.setOnClickListener {
            showTaskDetails(holder.itemView.context, task)
        }
    }

    override fun getItemCount(): Int {
        return filteredDatas.size
    }

    fun updateData(newData: List<TaskInfo>) {
        myDatas = newData
        filteredDatas = newData
        notifyDataSetChanged()
    }

    fun filterData(priority: String) {
        filteredDatas = myDatas.filter { it.priority == priority }
        notifyDataSetChanged()
    }

    private fun showTaskDetails(context: Context, task: TaskInfo) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Task Details")
        builder.setMessage(
            "Name: ${task.name}\n" +
                    "Priority: ${task.priority}\n" +
                    "Stage: ${task.stage}\n" +
                    "Description: ${task.description}\n" +
                    "Created Time: ${task.createdTime}\n" +
                    "Finished Time: ${task.finishedTime}\n" +
                    "Duration: ${task.duration}"
        )
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val itemButton: Button = itemView.findViewById(R.id.itemButton)
    }
}
