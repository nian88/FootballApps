package id.dicoding.nian.footballapps.players

import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.model.Player
import id.dicoding.nian.footballapps.model.Team

/**
 * Created by nian on 9/17/18.
 */
interface PlayersView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}