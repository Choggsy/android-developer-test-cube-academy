package com.cube.cubeacademy.activities

import android.accounts.AccountManager
import android.app.usage.NetworkStats
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.UserHandle
import android.os.UserManager
import android.webkit.ClientCertRequest
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cube.cubeacademy.BuildConfig
import com.cube.cubeacademy.MainApplication
import com.cube.cubeacademy.R
import com.cube.cubeacademy.databinding.ActivityMainBinding
import com.cube.cubeacademy.lib.adapters.NominationsRecyclerViewAdapter
import com.cube.cubeacademy.lib.di.AppModule
import com.cube.cubeacademy.lib.di.Repository
import com.cube.cubeacademy.lib.models.Nomination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.sql.ClientInfoStatus
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var makeNominationButton: Button
    private lateinit var nominationsRecyclerViewAdapter: NominationsRecyclerViewAdapter
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

    /**
     * TODO: Populate the UI with data in this function
     * 		 You need to fetch the list of user's nominations from the api and put the data in the recycler view
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun populateUI() {
//        val userNominationId =
        val nominationList = runBlocking { repository.getAllNominations() }
//        nominationList.distinctBy { nomination: Nomination -> nomination.nominationId.equals(userNominationId) }

        recycler = findViewById(R.id.nominations)
        nominationsRecyclerViewAdapter = NominationsRecyclerViewAdapter()
        nominationsRecyclerViewAdapter.submitList(nominationList)

        recycler.setAdapter(nominationsRecyclerViewAdapter)

        if (!nominationList.isNullOrEmpty()) {
            findViewById<RecyclerView>(R.id.nominations_list).isVisible = true
            findViewById<RecyclerView>(R.id.empty_nomination_container).isVisible = false
        }
    }
}