package com.cube.cubeacademy.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import com.cube.cubeacademy.databinding.ActivityMainBinding
import com.cube.cubeacademy.databinding.ViewNominationListItemBinding
import com.cube.cubeacademy.lib.adapters.NominationsRecyclerViewAdapter
import com.cube.cubeacademy.lib.di.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var makeNominationButton: Button

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
        /**
         * TODO: Populate the UI with data in this function
         * 		 You need to fetch the list of user's nominations from the api and put the data in the recycler view
         * 		 And also add action to the "Create new nomination" button to go to the CreateNominationActivity
         */
        //FIXME :: come back after wiring the rest of the site to see if this worked lol
        //used runBlocking for the async call as an alternative to using Suspend throughout the hierarchy.
//        val nominationList = runBlocking { repository.getAllNominations() } //could us this in IF
        if(binding.nominationsList.isNotEmpty()){
            NominationsRecyclerViewAdapter.ViewHolder(ViewNominationListItemBinding.bind(binding.nominationsList))
        }
    }
}