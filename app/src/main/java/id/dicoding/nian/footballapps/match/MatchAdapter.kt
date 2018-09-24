package id.dicoding.nian.footballapps.match

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.R.drawable.ic_alarm
import id.dicoding.nian.footballapps.main.MainActivity
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import java.util.*
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by nian on 9/17/18.
 */

class MatchAdapter (private val context: Context,private val match: List<Event>, private val listener: (Event)->Unit)  : RecyclerView.Adapter<MatchAdapter.MatchPassViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPassViewHolder {
        return MatchPassViewHolder(ItemMatchUI().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int =match.size

    override fun onBindViewHolder(holder: MatchPassViewHolder, position: Int) {
        holder.bindItem(context,match[position],listener)
    }
    class MatchPassViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val teamHome: TextView = view.find(ItemMatchUI.teamHome)
        private val teamAway: TextView = view.find(ItemMatchUI.teamAway)
        private val scoreMatch: TextView = view.find(ItemMatchUI.scoreMatch)
        private val dateTimeMatch: TextView = view.find(ItemMatchUI.dateTimeMatch)
        private val imageReminder: ImageView = view.find(ItemMatchUI.imageReminder)
        fun bindItem(context: Context, matchs: Event, listener: (Event) -> Unit) {
            val scoreHome:String = if (matchs.intHomeScore.isNullOrEmpty()) "?" else matchs.intHomeScore.toString()
            val scoreAway:String = if (matchs.intAwayScore.isNullOrEmpty()) "?" else matchs.intAwayScore.toString()
            val dateEvent:String = if (matchs.dateEvent.isNullOrEmpty()) "" else matchs.dateEvent.toString()
            val strTime:String = if (matchs.strTime.isNullOrEmpty()) "" else matchs.strTime.toString()
                teamHome.text = matchs.strHomeTeam
                teamAway.text = matchs.strAwayTeam
                scoreMatch.text =  "${scoreHome} VS ${scoreAway}"
                dateTimeMatch.text =  "${dateEvent} ${strTime.split("+")[0]}"
                imageReminder.setImageResource(ic_alarm)
            itemView.setOnClickListener {
                listener(matchs)
            }

            imageReminder.setOnClickListener {
                var strdateEvent:List<String> = matchs.dateEvent.toString().split("-")
                var timeEvent:List<String> = matchs.strTime.toString().split(":")

                val beginTime = Calendar.getInstance()
                beginTime.set(strdateEvent[0].toInt(), strdateEvent[1].toInt(), strdateEvent[2].toInt(), timeEvent[0].toInt(), timeEvent[1].toInt())

                val intent = Intent(Intent.ACTION_EDIT)
                intent.type = "vnd.android.cursor.item/event"
                intent.putExtra(CalendarContract.Events.TITLE, "${matchs.strHomeTeam} vs ${matchs.strAwayTeam}")
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
                intent.putExtra(CalendarContract.CalendarAlerts.ALARM_TIME, beginTime)
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
                context.startActivity(intent)
            }

        }
    }
}