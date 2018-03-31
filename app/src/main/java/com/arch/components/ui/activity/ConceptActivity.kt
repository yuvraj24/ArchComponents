package com.arch.components.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arch.components.R
import com.arch.components.listener.onClickCallback
import com.arch.components.ui.adapter.ConceptAdapter
import kotlinx.android.synthetic.main.activity_concept.*
import org.jetbrains.anko.intentFor

/**
 * Created by Yuvraj.
 */
class ConceptActivity : BaseActivity(), onClickCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concept)

        var concepts: List<String> = listOf("General Example", "ViewModel", "LifeCycle", "LiveData")
        var adapter: ConceptAdapter = ConceptAdapter(this, concepts, this)

        recylcer_concepts.layoutManager = LinearLayoutManager(this)
        recylcer_concepts.adapter = adapter
    }

    override fun onClick(position: Int) {
        when (position) {
            0 -> startActivity(intentFor<ActionCharacterActivity>())
            1 -> startActivity(intentFor<ViewModelActivity>())
            2 -> startActivity(intentFor<LifecycleActivity>())
            3 -> startActivity(intentFor<LiveDataActivity>())
        }
    }
}
