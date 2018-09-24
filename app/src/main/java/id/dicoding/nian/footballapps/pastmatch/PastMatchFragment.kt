package id.dicoding.nian.footballapps.pastmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.R
import android.widget.*
import com.google.gson.Gson
import id.dicoding.nian.footballapps.match.MatchUI
import id.dicoding.nian.footballapps.R.array.arrLeagueName
import id.dicoding.nian.footballapps.R.array.arrLeagueId
import id.dicoding.nian.footballapps.detailmatch.DetailMatchActivity
import id.dicoding.nian.footballapps.match.MatchAdapter
import id.dicoding.nian.footballapps.match.MatchPresenter
import id.dicoding.nian.footballapps.match.MatchView
import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb

import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class PastMatchFragment : Fragment(), MatchView {
    private lateinit var listMatch : RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinnerLeague: Spinner

    private var event: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var idLeague:String
    private var flag:String = "eventspastleague"

    companion object {
        fun getInstance() : PastMatchFragment = PastMatchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return MatchUI().createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listMatch = find(MatchUI.listMatch)
        progressBar = find(MatchUI.progressBar)
        swipeRefresh = find(MatchUI.swipeRefresh)
        spinnerLeague = find(MatchUI.spinnerLeague)

        adapter = MatchAdapter(ctx,event) {
            startActivity<DetailMatchActivity>(
                  "match" to it
            )
        }
        listMatch.adapter = adapter

        val request = ServicesTheSportsDb()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)


        val strLeagueId = resources.getStringArray(arrLeagueId)
        idLeague =strLeagueId[0].toString()


        val spinnerItems = resources.getStringArray(arrLeagueName)
        val spinnerAdapter = ArrayAdapter(ctx, R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerLeague.adapter = spinnerAdapter
        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                idLeague=strLeagueId[position].toString()
                presenter.getMatch(idLeague,flag)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getMatch(idLeague,flag)
        }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showTeamList(data: List<Event>) {
        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()
    }
}// Required empty public constructor
