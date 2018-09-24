package id.dicoding.nian.footballapps.searchmatch

import android.util.Log
import android.widget.SearchView
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
class FindPresenter (private val view: FindView,
                     private val servicesTheSportsDb: ServicesTheSportsDb,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {
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
                view.showMatchList(data.await().event)
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
            view.hideLoading()
            view.showTeamList(data.await().teams)
        }
    }
}