package id.dicoding.nian.footballapps.match

import com.google.gson.Gson
import id.dicoding.nian.footballapps.model.ResultEvent
import id.dicoding.nian.footballapps.model.ResultSearch
import id.dicoding.nian.footballapps.repository.ApiTheSportsDb
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb
import id.dicoding.nian.footballapps.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by nian on 9/17/18.
 */
class MatchPresenter (private val view: MatchView,
                      private val servicesTheSportsDb: ServicesTheSportsDb,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMatch(idleague: String?,position: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(servicesTheSportsDb
                        .doRequest(ApiTheSportsDb.getMatch(idleague,position)),
                        ResultEvent::class.java
                )
            }
            view.showTeamList(data.await().events)
            view.hideLoading()
        }
    }
    fun getSearchMatch(strSearch: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(servicesTheSportsDb
                        .doRequest(ApiTheSportsDb.getSearchMatch(strSearch)),
                        ResultSearch::class.java
                )
            }
            if(data.await().event.size>0){
                view.showTeamList(data.await().event)
            }
            view.hideLoading()
        }
    }
}