package com.arch.components.ui.activity

import android.os.Bundle
import com.arch.components.R
import com.arch.components.ui.adapter.ConceptAdapter
import kotlinx.android.synthetic.main.activity_concept.*

class ConceptActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concept)

        var concepts : List<String> = listOf("General Example","ViewModel","LifeCycle","LiveData","Room")
        var adapter : ConceptAdapter = ConceptAdapter(this, concepts)
        recylcer_concepts.adapter = adapter
    }
}
