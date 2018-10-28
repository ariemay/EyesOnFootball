package tech.shalecode.eyesonfootball.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.matches_view.view.*
import tech.shalecode.eyesonfootball.Models.EventsItem
import tech.shalecode.eyesonfootball.R


class MatchAdapter(private val events: List<EventsItem>,
                    val context: Context,
                    private val listener: (EventsItem) -> Unit) : RecyclerView.Adapter<MatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(LayoutInflater.from(context).inflate(R.layout.matches_view, parent, false))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

}

class MatchesViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(events: EventsItem, listener: (EventsItem) -> Unit) {
        itemView.HOME_TEAM_NAME.text = events.strHomeTeam
        itemView.AWAY_TEAM_NAME.text = events.strAwayTeam
        itemView.HOME_TEAM_SCORE.text = events.intHomeScore
        itemView.AWAY_TEAM_SCORE.text = events.intAwayScore
        itemView.DATE_EVENT.text = events.dateEvent
        itemView.setOnClickListener {
            listener(events)
        }
    }
}
