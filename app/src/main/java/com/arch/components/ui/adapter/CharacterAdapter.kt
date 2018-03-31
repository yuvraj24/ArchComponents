package com.arch.components.ui.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.arch.components.R
import com.arch.components.helper.CharacterResponse
import kotlinx.android.synthetic.main.holder_characters.view.*

/**
 * Created by Yuvraj
 */
class CharacterAdapter(mContext: Activity, objects: List<CharacterResponse.ResultsItem>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var context: Activity = mContext
    private var listCharacters: List<CharacterResponse.ResultsItem> = objects

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.ViewHolder {
        val view = context.layoutInflater.inflate(R.layout.holder_characters, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {
        holder.itemView.text_name.text = listCharacters[position].name
        holder.itemView.text_description.text = listCharacters[position].description
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {

            }
        }
    }
}