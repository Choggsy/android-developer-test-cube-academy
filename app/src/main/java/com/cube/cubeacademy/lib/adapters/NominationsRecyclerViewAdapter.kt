package com.cube.cubeacademy.lib.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cube.cubeacademy.databinding.ViewNominationListItemBinding
import com.cube.cubeacademy.lib.di.AppModule
import com.cube.cubeacademy.lib.models.Nomination
import com.cube.cubeacademy.lib.models.Nominee
import kotlinx.coroutines.runBlocking

class NominationsRecyclerViewAdapter :
    ListAdapter<Nomination, NominationsRecyclerViewAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ViewNominationListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewNominationListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val firstName = runBlocking { findNomineeName(item.nominationId)!!.firstName }

        holder.binding.apply {
            /**
             * TODO: This should show the nominee name instead of their id! Where can you get their name from?
             * // get on nomineeList // or could use the getFullName from repository
             * getNomineeNameList
             */
            name.text = firstName
            reason.text = item.reason
        }
    }

    //FIXME :: unit test validation on this one
    private suspend fun findNomineeName(nomineeId: String): Nominee? {
        return AppModule.provideRepository(AppModule.provideApi())
            .getAllNominees()
            .find { nominee: Nominee -> nominee.nomineeId.equals(nomineeId) }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Nomination>() {
            override fun areItemsTheSame(oldItem: Nomination, newItem: Nomination) =
                oldItem.nominationId == newItem.nominationId

            override fun areContentsTheSame(oldItem: Nomination, newItem: Nomination) =
                oldItem == newItem
        }
    }
}