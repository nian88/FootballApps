package id.dicoding.nian.footballapps.players

import com.google.gson.Gson
import id.dicoding.nian.footballapps.model.ResultEvent
import id.dicoding.nian.footballapps.model.ResultPlayers
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
class PlayersPresenter (private val view: PlayersView,
                        private val servicesTheSportsDb: ServicesTheSportsDb,
                        private val gson: Gson,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayers(idTeam: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(servicesTheSportsDb
                        .doRequest(ApiTheSportsDb.getPlayers(idTeam)),
                        ResultPlayers::class.java
                )
            }
            if(data.await().player.size>0){
                view.showPlayerList(data.await().player)
            }
            view.hideLoading()
        }
    }
}