package id.dicoding.nian.footballapps.favorite.team

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team
import id.dicoding.nian.footballapps.team.ItemTeamUI
import id.dicoding.nian.footballapps.team.TeamAdapter
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by nian on 9/22/18.
 */

class TeamFavoriteAdapter (private val context: Context, private val team: List<FavoriteTeam>, private val listener: (FavoriteTeam)->Unit)  : RecyclerView.Adapter<TeamFavoriteAdapter.TeamFavoriteViewHolder >(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamFavoriteViewHolder {
        return TeamFavoriteViewHolder (ItemTeamUI().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int =team.size

    override fun onBindViewHolder(holder: TeamFavoriteViewHolder , position: Int) {
        holder.bindItem(context,team[position],listener)
    }
    class TeamFavoriteViewHolder (view: View) : RecyclerView.ViewHolder(view){
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