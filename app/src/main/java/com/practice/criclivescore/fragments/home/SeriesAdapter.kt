package com.practice.criclivescore.fragments.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.criclivescore.databinding.RvItemCricketSeriesBinding
import com.practice.criclivescore.models.series.Match
import java.util.Random


class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

/*
    val onClickItem: ((SeriesData) -> Unit)? = null
*/

    private val diffUtil = object : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
           return oldItem.id == newItem.id
        }
    }

    val asyncDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesAdapter.SeriesViewHolder {
       val view = RvItemCricketSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesAdapter.SeriesViewHolder, position: Int) {
       holder.binding.apply {
           val rnd = Random()
           val currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
           mcvRootCricketSeries.setCardBackgroundColor(currentColor)

          matchTitle.text = asyncDiffer.currentList[position].name
          matchType.text = asyncDiffer.currentList[position].matchType
          var teamName = ""
          for (team in asyncDiffer.currentList[position].teams){
             teamName += String.format("$team, ")
          }
          matchTeam.text = String.format("Teams: $teamName")
          matchStatus.text = String.format("Status: ${asyncDiffer.currentList[position].status}")
          matchVenue.text = String.format("Venue: ${asyncDiffer.currentList[position].venue}")
          matchDate.text = String.format("Date: ${asyncDiffer.currentList[position].date}")
       }
    }

    override fun getItemCount(): Int {
      return asyncDiffer.currentList.size
    }

    inner class SeriesViewHolder (val binding: RvItemCricketSeriesBinding) : RecyclerView.ViewHolder (binding.mcvRootCricketSeries)
}