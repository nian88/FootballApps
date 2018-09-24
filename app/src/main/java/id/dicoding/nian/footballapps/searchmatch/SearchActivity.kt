package id.dicoding.nian.footballapps.searchmatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.model.Event
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.MenuItem
import com.google.gson.Gson
import id.dicoding.nian.footballapps.detailmatch.DetailMatchActivity
import id.dicoding.nian.footballapps.match.MatchAdapter
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb
import id.dicoding.nian.footballapps.team.TeamAdapter
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh


class SearchActivity : AppCompatActivity(),FindView, SearchView.OnQueryTextListener {
    private var menuItem: Menu? = null
    private var strQuery:String?=null

    private var event: MutableList<Event> = mutableListOf()
    private var team: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var presenter: FindPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var adapterTeam: TeamAdapter
    var cari :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.title = "Search"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rvCari.layoutManager= LinearLayoutManager(ctx)
        rvCari.addItemDecoration(DividerItemDecoration(ctx,DividerItemDecoration.VERTICAL))
        rvCari.setHasFixedSize(true)

        cari = intent.getIntExtra("data",0)

        val request = ServicesTheSportsDb()
        val gson = Gson()

        presenter = FindPresenter(this, request, gson)

if(cari==0){
    adapter = MatchAdapter(ctx,event) {
        startActivity<DetailMatchActivity>()
    }
    rvCari.adapter = adapter
}else{
    adapterTeam = TeamAdapter(ctx,team) {
        startActivity<DetailMatchActivity>()
    }
    rvCari.adapter = adapterTeam
}



        swipeRefresh.onRefresh {
            if (cari==0){
                presenter.getSearchMatch(strQuery)
            }else{
                presenter.getSearchTeam(strQuery)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.searchBar)

        val searchView = searchItem?.getActionView() as SearchView
        searchView.setQueryHint("Cari")
        searchView.setOnQueryTextListener(this)
        searchView.setIconified(false)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (cari==0){
            presenter.getSearchMatch(query)
        }else{
            presenter.getSearchTeam(query)
        }
        strQuery=query
        return true;
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true;
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing=true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing=false
    }

    override fun showTeamList(data: List<FavoriteTeam>) {
        team.clear()
        team.addAll(data)
        adapterTeam.notifyDataSetChanged()
    }

    override fun showMatchList(data: List<Event>) {
        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
