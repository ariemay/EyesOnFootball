package tech.shalecode.eyesonfootball.Adapter

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import tech.shalecode.eyesonfootball.Models.EventsItem
import tech.shalecode.eyesonfootball.R
import tech.shalecode.eyesonfootball.R.id.*


class MatchAdapter(private val events: List<EventsItem>) : RecyclerView.Adapter<MatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bindItem(events[position])
    }

    override fun getItemCount(): Int = events.size

}

class MatchUI : AnkoComponent<ViewGroup> {
//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                orientation = LinearLayout.VERTICAL

                textView("Sun, 01-05-1988") {
                    id = R.id.dateEvent
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }
                    textColor = R.color.blue_violet
                    textSize = 15f
                }.lparams(width = matchParent, height = wrapContent)
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    gravity = Gravity.CENTER_VERTICAL

                    linearLayout {
                        lparams(width = matchParent, height = matchParent)
                        orientation = LinearLayout.HORIZONTAL

                        textView("Juventus") {
                            gravity = Gravity.CENTER
                            id = R.id.homeTeamName
                        }.lparams(width = matchParent, height = wrapContent) {
                            weight = 0.8F
                        }
                        textView("5") {
                            gravity = Gravity.CENTER
                            id = R.id.homeTeamScore
                        }.lparams(width = matchParent, height = wrapContent) {
                            weight = 1F
                        }
                        textView("vs") {
                            gravity = Gravity.CENTER
                            id = R.id.vsTV
                        }.lparams(width = matchParent, height = wrapContent) {
                            weight = 1F
                        }
                        textView("0") {
                            gravity = Gravity.CENTER
                            id = R.id.awayTeamScore
                        }.lparams(width = matchParent, height = wrapContent) {
                            weight = 1F
                        }
                        textView("Barcelona") {
                            gravity = Gravity.CENTER
                            id = R.id.awayTeamName
                        }.lparams(width = matchParent, height = wrapContent) {
                            weight = 0.8F
                        }
                    }
                }
            }
        }
    }
}


class MatchesViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val HOME_TEAM_NAME : TextView = view.find(homeTeamName)
    private val AWAY_TEAM_NAME : TextView = view.find(awayTeamName)
    private val HOME_TEAM_SCORE : TextView = view.find(homeTeamScore)
    private val AWAY_TEAM_SCORE : TextView = view.find(awayTeamScore)
    private val DATE_EVENT : TextView = view.find(dateEvent)

    fun bindItem(events: EventsItem) {
        HOME_TEAM_NAME.text = events.strHomeTeam
        HOME_TEAM_SCORE.text = events.intHomeScore
        AWAY_TEAM_NAME.text = events.strAwayTeam
        AWAY_TEAM_SCORE.text = events.intAwayScore
        DATE_EVENT.text = events.dateEvent
    }
}
