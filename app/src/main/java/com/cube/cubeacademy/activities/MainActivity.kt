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
    private fun populateUI() {
//        val nominationList = runBlocking { repository.getAllNominations()}
        val nominationList = runBlocking { listOf(Nomination("nominationId","nomineeid","good reason","p","d1","d2")) }
        val viewById = findViewById<RecyclerView>(R.id.nominations)
        recycler = viewById
        nominationsRecyclerViewAdapter = NominationsRecyclerViewAdapter()

//        recycler.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = nominationsRecyclerViewAdapter
//        }

        recycler.setAdapter(nominationsRecyclerViewAdapter)
        nominationsRecyclerViewAdapter.submitList(nominationList)
        findViewById<RecyclerView>(R.id.nominations_list).isVisible = true
        findViewById<RecyclerView>(R.id.empty_nomination_container).isVisible = false

    }

//    yViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
//        RecyclerView recyclerView = findViewById(R.id.user_list);
//        UserAdapter<User> adapter = new UserAdapter();
//        viewModel.usersList.observe(this, list -> adapter.submitList(list));
//        recyclerView.setAdapter(adapter);
}