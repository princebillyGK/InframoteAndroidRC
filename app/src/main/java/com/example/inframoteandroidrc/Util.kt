package com.example.inframoteandroidrc

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.inframoteandroidrc.database.RemoteButton

class ButtonItemViewHolder(val remoteButtonView: Button) : RecyclerView.ViewHolder(remoteButtonView)

class RemoteButtonAdapter(val deleteHandler: (button: RemoteButton) -> Unit) :
    RecyclerView.Adapter<ButtonItemViewHolder>() {
    var data = listOf<RemoteButton>()


    fun update(value: List<RemoteButton>) {
        data = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.remote_button, parent, false) as Button
        return ButtonItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ButtonItemViewHolder, position: Int) {
        val item = data[position]
        holder.remoteButtonView.text = item.name
        holder.remoteButtonView.setOnClickListener {
            deleteHandler(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}