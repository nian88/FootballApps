package id.dicoding.nian.footballapps.team

import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team

/**
 * Created by nian on 9/17/18.
 */
interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<FavoriteTeam>)
}