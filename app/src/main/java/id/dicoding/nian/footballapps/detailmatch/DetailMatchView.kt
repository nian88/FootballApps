package id.dicoding.nian.footballapps.detailmatch

import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team

/**
 * Created by nian on 9/17/18.
 */
interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<FavoriteTeam>,flag:Int)
}