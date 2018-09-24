package id.dicoding.nian.footballapps.team

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
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
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by nian on 9/17/18.
 */

class TeamAdapter (private val context: Context,private val team: List<FavoriteTeam>, private val listener: (FavoriteTeam)->Unit)  : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(ItemTeamUI().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int =team.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(context,team[position],listener)
    }
    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val strTeam: TextView = view.find(ItemTeamUI.strTeam)
        private val strTeamBadge: ImageView = view.find(ItemTeamUI.strTeamBadge)
        fun bindItem(context: Context, team: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
                strTeam.text = team.strTeam
                Glide.with(context).load(team.strTeamBadge).into(strTeamBadge)
            itemView.setOnClickListener {
                listener(team)
            }
        }
    }
}