package id.dicoding.nian.footballapps.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson

import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.R.array.*
import id.dicoding.nian.footballapps.detailteam.DetailTeamActivity
import id.dicoding.nian.footballapps.match.MatchUI
import id.dicoding.nian.footballapps.favorite.team.TeamFavoriteAdapter
import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team
import org.jetbrains.anko.AnkoContext
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb
import org.jetbrains.anko.support.v4.*


/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment(),TeamView, SearchView.OnQueryTextListener {

    private lateinit var listTeams : RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinnerLeague: Spinner

    private var team: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamFavoriteAdapter
    private lateinit var strLeague:String


    companion object {
        fun getInstance(): TeamsFragment = TeamsFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return MatchUI().createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listTeams = find(MatchUI.listMatch)
        progressBar = find(MatchUI.progressBar)
        swipeRefresh = find(MatchUI.swipeRefresh)
        spinnerLeague = find(MatchUI.spinnerLeague)

        adapter = TeamFavoriteAdapter(ctx,team) {
            startActivity<DetailTeamActivity>(
                    "data" to it,
                    "flag" to "0"
            )
        }
        listTeams.adapter = adapter

        val request = ServicesTheSportsDb()
        val gson = Gson()
        presenter = TeamPresenter (this, request, gson)
        val strLeagueId = resources.getStringArray(arrLeagueName)
        strLeague =strLeagueId[0].toString().replace(" ","%20")



        val spinnerItems = resources.getStringArray(arrLeagueName)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerLeague.adapter = spinnerAdapter
        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                strLeague=strLeagueId[position].toString().replace(" ","%20")
                presenter.getTeam(strLeague)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getTeam(strLeague)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.searchBar)

        val searchView = searchItem?.getActionView() as SearchView
        searchView.setQueryHint("Cari")
        searchView.setOnQueryTextListener(this)
        searchView.setIconified(false)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        presenter.getSearchTeam(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showTeamList(data: List<FavoriteTeam>) {
        team.clear()
        team.addAll(data)
        adapter.notifyDataSetChanged()
    }
}// Required empty public constructor
