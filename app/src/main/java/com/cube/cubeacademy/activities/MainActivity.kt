package com.cube.cubeacademy.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cube.cubeacademy.databinding.ActivityMainBinding
import com.cube.cubeacademy.lib.di.Repository
import dagger.hilt.android.AndroidEntryPoint
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
        //suspend issue. research.
        //Psedo : repository.getAllNominations() if List.empty() -> load 01.00 homescreen. Else use recycler to populate the UI.
    }
}