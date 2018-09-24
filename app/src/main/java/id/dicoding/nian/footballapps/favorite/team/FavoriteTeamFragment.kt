package id.dicoding.nian.footballapps.favorite.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.detailteam.DetailTeamActivity
import id.dicoding.nian.footballapps.utils.database
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team
import id.dicoding.nian.footballapps.team.TeamAdapter
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {

    private var team: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: TeamFavoriteAdapter
    companion object {
        fun getInstance(): FavoriteTeamFragment = FavoriteTeamFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvFavoriteTeam.addItemDecoration(DividerItemDecoration(ctx,DividerItemDecoration.VERTICAL))
        rvFavoriteTeam.setHasFixedSize(true)
        rvFavoriteTeam.layoutManager= LinearLayoutManager(ctx)
        adapter = TeamFavoriteAdapter(ctx,team) {
            startActivity<DetailTeamActivity>(
                    "data" to it,
                    "flag" to "1"
            )
        }
        rvFavoriteTeam.adapter = adapter
        showFavorite()

    }
    override fun onResume() {
        super.onResume()
        showFavorite()
    }
    fun showFavorite(){
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            team.clear()
            team.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}// Required empty public constructor
