package id.dicoding.nian.footballapps.match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.R.id.navSearch
import id.dicoding.nian.footballapps.nextmatch.NextMatchFragment
import id.dicoding.nian.footballapps.pastmatch.PastMatchFragment
import id.dicoding.nian.footballapps.searchmatch.SearchActivity
import id.dicoding.nian.footballapps.utils.TabAdapter
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class MatchFragment : Fragment(){
    private lateinit var adapter:TabAdapter
    private lateinit var menuInflater:MenuInflater
    companion object {
        fun getInstance(): MatchFragment = MatchFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState:Bundle?): View? {

        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = TabAdapter(childFragmentManager)
        adapter.addFragment(PastMatchFragment.getInstance(),"Past Match")
        adapter.addFragment(NextMatchFragment.getInstance(),"Next Match")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setText("Past Match")
        tabLayout.getTabAt(1)!!.setText("Next Match")

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

//

}
