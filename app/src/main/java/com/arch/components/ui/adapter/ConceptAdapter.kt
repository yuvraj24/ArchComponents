package com.arch.components.ui.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.arch.components.R
import kotlinx.android.synthetic.main.holder_characters.view.*

/**
 * Created by Yuvraj on 27/03/18.
 */
class ConceptAdapter(mContext: Activity, objects: List<String>) : RecyclerView.Adapter<ConceptAdapter.ViewHolder>() {

    private var context: Activity = mContext
    private var listCharacters: List<String> = objects

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConceptAdapter.ViewHolder {
        val view = context.layoutInflater.inflate(R.layout.holder_characters, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    override fun onBindViewHolder(holder: ConceptAdapter.ViewHolder, position: Int) {
        holder.itemView.text_name.text = listCharacters[position]
        holder.itemView.text_description.visibility = GONE
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {

            }
        }
    }
}