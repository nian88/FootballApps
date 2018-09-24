package id.dicoding.nian.footballapps.team

import com.google.gson.Gson
import id.dicoding.nian.footballapps.model.ResultEvent
import id.dicoding.nian.footballapps.model.ResultSearch
import id.dicoding.nian.footballapps.model.ResultTeam
import id.dicoding.nian.footballapps.repository.ApiTheSportsDb
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb
import id.dicoding.nian.footballapps.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by nian on 9/17/18.
 */
class TeamPresenter (private val view: TeamView,
                     private val servicesTheSportsDb: ServicesTheSportsDb,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeam(idLeague: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(servicesTheSportsDb
                        .doRequest(ApiTheSportsDb.getTeam(idLeague)),
                        ResultTeam::class.java
                )
            }
            if(data.await().teams.size>0){
                view.showTeamList(data.await().teams)
            }
            view.hideLoading()
        }
    }
    fun getSearchTeam(strSearch: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(servicesTheSportsDb
                        .doRequest(ApiTheSportsDb.getSearchTeam(strSearch)),
                        ResultTeam::class.java
                )
            }
            if(data.await().teams.size>0){
                view.showTeamList(data.await().teams)
            }
            view.hideLoading()
        }
    }
}