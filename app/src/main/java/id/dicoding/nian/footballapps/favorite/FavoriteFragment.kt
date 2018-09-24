package id.dicoding.nian.footballapps.favorite


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.utils.TabAdapter
import id.dicoding.nian.footballapps.favorite.match.*
import id.dicoding.nian.footballapps.favorite.team.*
import kotlinx.android.synthetic.main.fragment_match.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment:Fragment() {
    private lateinit var adapter: TabAdapter
    private lateinit var menuInflater: MenuInflater
    companion object {
        fun getInstance(): FavoriteFragment = FavoriteFragment()
    }

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
    savedInstanceState:Bundle?):View? {
            return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = TabAdapter(childFragmentManager)
        adapter.addFragment(FavoriteMatchFragment.getInstance(),"Match")
        adapter.addFragment(FavoriteTeamFragment.getInstance(),"Team")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setText("Match")
        tabLayout.getTabAt(1)!!.setText("Team")

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}// Required empty public constructor
