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
         */

        //note: used runBlocking for the async call as an alternative to using Suspend throughout the hierarchy.
        if (binding.nominationsList.isNotEmpty()) {
            //make fragement for using the nomination list. --> similar to LeaveNomination Set up but not modal
//            SubmittedNominations() -> all the nomimation the user made
        }
    }
}