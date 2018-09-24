package id.dicoding.nian.footballapps.favorite.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.detailmatch.DetailMatchActivity
import id.dicoding.nian.footballapps.match.MatchAdapter
import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.model.FavoriteMatch
import id.dicoding.nian.footballapps.utils.database
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : Fragment() {

    private var event: MutableList<Event> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    companion object {
        fun getInstance(): FavoriteMatchFragment = FavoriteMatchFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvFavoriteMatch.addItemDecoration(DividerItemDecoration(ctx,DividerItemDecoration.VERTICAL))
        rvFavoriteMatch.setHasFixedSize(true)
        rvFavoriteMatch.layoutManager=LinearLayoutManager(ctx)
        adapter = MatchAdapter(ctx,event) {
            startActivity<DetailMatchActivity>(
                    "match" to it
            )
        }
        rvFavoriteMatch.adapter = adapter

        showFavorite()

    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }
    fun showFavorite(){
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Event>())
            event.clear()
            event.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}// Required empty public constructor
