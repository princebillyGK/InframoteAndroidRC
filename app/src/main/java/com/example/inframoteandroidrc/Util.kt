package com.example.inframoteandroidrc

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.inframoteandroidrc.database.RemoteButton

class ButtonCardViewHolder(val buttonCardView: CardView) : RecyclerView.ViewHolder(buttonCardView)

class RemoteButtonAdapter(
    val deleteHandler: (buttoncard: RemoteButton) -> Unit,
    val sendHandler: () -> Unit
) :
    RecyclerView.Adapter<ButtonCardViewHolder>() {
    var data = listOf<RemoteButton>()


    fun update(value: List<RemoteButton>) {
        data = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonCardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.remote_button, parent, false) as CardView
        return ButtonCardViewHolder(v)
    }

    override fun onBindViewHolder(holder: ButtonCardViewHolder, position: Int) {
        val item = data[position]
        holder.buttonCardView.findViewById<TextView>(R.id.textNameValue).text = item.name
        holder.buttonCardView.findViewById<TextView>(R.id.textAddressValue).text = item.address
        holder.buttonCardView.findViewById<TextView>(R.id.textCommandValue).text = item.command
        holder.buttonCardView.findViewById<TextView>(R.id.textProtocolValue).text = item.protocol
        holder.buttonCardView.findViewById<Button>(R.id.sendButton).setOnClickListener {
            sendHandler()
        }
        holder.buttonCardView.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            deleteHandler(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}