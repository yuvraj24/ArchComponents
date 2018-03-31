package com.arch.components.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arch.components.R
import com.arch.components.architecture.viewmodels.CharacterViewModel
import com.arch.components.helper.CharacterResponse
import com.arch.components.ui.adapter.CharacterAdapter
import com.arch.components.utils.AppUtils
import kotlinx.android.synthetic.main.activity_action_character.*


class ActionCharacterActivity : BaseActivity() {

    private lateinit var viewModel: CharacterViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_character)

        setUpRecyclerView();
        initViewModel()
    }

    private fun setUpRecyclerView() {
        recycler_characters.layoutManager = LinearLayoutManager(this)
        recycler_characters.hasFixedSize()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        viewModel.getActionCharacters()
                .observe(this, Observer<CharacterResponse>() {
                    AppUtils.logMe("ActionCharacterActivity", "onChanged")
                    progressBar.visibility = View.GONE
                    if (it?.data?.results?.size != 0) {
                        recycler_characters.adapter = CharacterAdapter(this, it!!.data.results)
                    }
                })
    }
}
