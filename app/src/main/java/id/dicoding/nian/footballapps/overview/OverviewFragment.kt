package id.dicoding.nian.footballapps.overview


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team
import kotlinx.android.synthetic.main.fragment_overview.*
import org.jetbrains.anko.ctx

class OverviewFragment : Fragment() {

    private lateinit var team: FavoriteTeam
    companion object {
        fun getInstance(team:FavoriteTeam) : OverviewFragment {
            val overviewFragment =OverviewFragment()
            val bundle= Bundle()
            bundle.putParcelable("team", team)
            overviewFragment.arguments = bundle
            return overviewFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        team = arguments!!.getParcelable("team")
        strDescriptionEN.text = team.strDescriptionEN
        strTeam.text=team.strTeam
        intFormedYear.text=team.intFormedYear
        Glide.with(this).load(team.strTeamBadge).into(strTeamBadge)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

}
