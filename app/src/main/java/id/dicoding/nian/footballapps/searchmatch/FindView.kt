package id.dicoding.nian.footballapps.searchmatch

import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team

/**
 * Created by nian on 9/17/18.
 */
interface FindView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)
    fun showTeamList(data: List<FavoriteTeam>)
}