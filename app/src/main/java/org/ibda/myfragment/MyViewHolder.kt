package org.ibda.myfragment

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    private val rootElement = rootView

    fun bindData(no : Int, name: String) {

        val numberTV = rootElement.findViewById<TextView>(R.id.myDataNumber)
        val stringTV = rootElement.findViewById<TextView>(R.id.myDataString)
        val myShowBtn = rootElement.findViewById<Button>(R.id.myShowBtn)

        numberTV.text = no.toString()
        stringTV.text = name

        myShowBtn.setOnClickListener {
            Toast.makeText(rootElement.context, "Halo : ${name}", Toast.LENGTH_LONG).show()
        }

    }

}