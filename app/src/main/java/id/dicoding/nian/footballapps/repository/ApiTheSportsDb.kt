package id.dicoding.nian.footballapps.repository

import android.icu.text.StringSearch
import android.net.Uri
import id.dicoding.nian.footballapps.BuildConfig

/**
 * Created by nian on 9/17/18.
 */
object ApiTheSportsDb {
    fun getMatch(idleague: String?,position: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/${position}.php?id="+idleague
    }
    fun getSearchMatch(stringSearch: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e="+stringSearch
    }
    fun getTeam(strLeague: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_teams.php?l="+strLeague
    }
    fun getDetailTeam(idTeam: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id="+idTeam
    }
    fun getSearchTeam(stringSearch: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?t="+stringSearch
    }
    fun getPlayers(strTeam: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php?id="+strTeam
    }
}