package id.dicoding.nian.footballapps.match

import id.dicoding.nian.footballapps.model.Event

/**
 * Created by nian on 9/17/18.
 */
interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Event>)
}