package id.dicoding.nian.footballapps.players

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
import id.dicoding.nian.footballapps.model.Player
import id.dicoding.nian.footballapps.model.Team
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by nian on 9/17/18.
 */


class PlayersAdapter (private val context: Context, private val player: List<Player>, private val listener: (Player)->Unit)  : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(ItemPlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int =player.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(context,player[position],listener)
    }
    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val strPlayer: TextView = view.find(ItemPlayerUI.strPlayer)
        private val strThumb: ImageView = view.find(ItemPlayerUI.strThumb)
        private val strPosition: TextView = view.find(ItemPlayerUI.strPosition)
        fun bindItem(context: Context, player: Player, listener: (Player) -> Unit) {
                strPlayer.text = player.strPlayer
                Glide.with(context).load(player.strThumb).into(strThumb)
            strPosition.text = player.strPosition
            itemView.setOnClickListener {
                listener(player)
            }
        }
    }

}