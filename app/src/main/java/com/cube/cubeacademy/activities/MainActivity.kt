package com.cube.cubeacademy.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cube.cubeacademy.R
import com.cube.cubeacademy.databinding.ActivityMainBinding
import com.cube.cubeacademy.lib.adapters.NominationsRecyclerViewAdapter
import com.cube.cubeacademy.lib.di.Repository
import com.cube.cubeacademy.lib.models.Nomination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var nominationsRecyclerViewAdapter: NominationsRecyclerViewAdapter
    private lateinit var makeNominationButton: Button
    private lateinit var recycler: RecyclerView

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeNominationButtonListener()

        populateUI()

    }


    private fun makeNominationButtonListener() {
        makeNominationButton = binding.createButton
        makeNominationButton.setOnClickListener {
            startActivity(Intent(this, CreateNominationActivity::class.java))
        }
    }

    private fun populateUI() {
        // note : would have liked to have new list for new user but didnt have time
        val nominationList = runBlocking { repository.getAllNominations() }

        recycler = findViewById(R.id.nominations)
        nominationsRecyclerViewAdapter = NominationsRecyclerViewAdapter()
        nominationsRecyclerViewAdapter.submitList(nominationList)

        recycler.setAdapter(nominationsRecyclerViewAdapter)
        listState(nominationList)
    }

    private fun listState(nominationList: List<Nomination>) {
        if (!nominationList.isNullOrEmpty()) {
            findViewById<RecyclerView>(R.id.nominations_list).isVisible = true
            findViewById<RecyclerView>(R.id.empty_nomination_container).isVisible = false
        }else{
            findViewById<RecyclerView>(R.id.nominations_list).isVisible = false
            findViewById<RecyclerView>(R.id.empty_nomination_container).isVisible = true
        }
    }
}