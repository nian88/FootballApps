package id.dicoding.nian.footballapps.players


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.detailplayer.DetailPlayerActivity
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Player
import id.dicoding.nian.footballapps.model.Team
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class PlayersFragment : Fragment(),PlayersView  {

    private lateinit var team: FavoriteTeam
    private var player: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayersPresenter
    private lateinit var adapter: PlayersAdapter

    companion object {
        fun getInstance(team: FavoriteTeam) : PlayersFragment {
            val playersFragment =PlayersFragment()
            val bundle= Bundle()
            bundle.putParcelable("team", team)
            playersFragment.arguments = bundle
            return playersFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        team = arguments!!.getParcelable("team")
        rvPlayers.layoutManager = LinearLayoutManager(ctx)
        rvPlayers.addItemDecoration(DividerItemDecoration(ctx,DividerItemDecoration.VERTICAL))

        val request = ServicesTheSportsDb()
        val gson = Gson()
        presenter = PlayersPresenter(this, request, gson)


        adapter = PlayersAdapter(ctx,player) {
            startActivity<DetailPlayerActivity>(
                    "player" to it
            )
        }
        rvPlayers.adapter = adapter
        presenter.getPlayers(team.idTeam)

        swipeRefresh.setOnRefreshListener {
            presenter.getPlayers(team.idTeam)
        }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing=true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing=false
    }

    override fun showPlayerList(data: List<Player>) {
        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()
    }
}// Required empty public constructor
