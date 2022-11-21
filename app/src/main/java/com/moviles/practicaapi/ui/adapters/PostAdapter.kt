package com.moviles.practicaapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicaapi.R
import com.moviles.practicaapi.models.Post

class PostAdapter(val data: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblTitle = itemView.findViewById<TextView>(R.id.lblTitle)
        val lblId = itemView.findViewById<TextView>(R.id.lblId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_item_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = data[position]
        holder.lblTitle.text = post.title
        holder.lblId.text = post.id.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
