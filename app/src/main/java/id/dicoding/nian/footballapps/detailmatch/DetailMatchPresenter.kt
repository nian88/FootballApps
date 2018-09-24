package id.dicoding.nian.footballapps.detailmatch

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
class DetailMatchPresenter (private val view: DetailMatchView,
                            private val servicesTheSportsDb: ServicesTheSportsDb,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailTeam(idTeam: String?, flag:Int) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(servicesTheSportsDb
                        .doRequest(ApiTheSportsDb.getDetailTeam(idTeam)),
                        ResultTeam::class.java
                )
            }
            if(data.await().teams.size>0){
                view.showTeamList(data.await().teams,flag)
            }
            view.hideLoading()
        }
    }
}