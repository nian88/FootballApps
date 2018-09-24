package id.dicoding.nian.footballapps.match

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import id.dicoding.nian.footballapps.R.color.colorAccent
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by nian on 9/17/18.
 */
class MatchUI : AnkoComponent<Any> {
    companion object {
        const val listMatch = 1
        const val swipeRefresh = 2
        const val progressBar = 3
        const val spinnerLeague = 4
    }
    override fun createView(ui: AnkoContext<Any>): View = with(ui){
        relativeLayout {
            lparams(width= matchParent, height = wrapContent)
            progressBar { visibility=View.GONE }.lparams{centerHorizontally()}.id= progressBar
            swipeRefreshLayout {
                setColorSchemeResources(colorAccent, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light)
                linearLayout {
                    orientation=LinearLayout.VERTICAL
                    lparams(width= matchParent, height = wrapContent)
                    spinner().id=spinnerLeague
                    recyclerView {
                        lparams(width= matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                        addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))
                    }.id= listMatch
                }
            }.id= swipeRefresh

        }
    }

}