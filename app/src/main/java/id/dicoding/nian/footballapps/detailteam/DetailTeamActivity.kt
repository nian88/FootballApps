package id.dicoding.nian.footballapps.detailteam

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.model.Team
import id.dicoding.nian.footballapps.overview.OverviewFragment
import id.dicoding.nian.footballapps.players.PlayersFragment
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb
import id.dicoding.nian.footballapps.utils.TabAdapter
import id.dicoding.nian.footballapps.utils.database
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    private lateinit var team: FavoriteTeam
    private lateinit var adapter: TabAdapter
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        supportActionBar?.title = "Detail Team"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        team = intent.getParcelableExtra<FavoriteTeam>("data")
        favoriteState()


        adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(OverviewFragment.getInstance(team),"Overview")
        adapter.addFragment(PlayersFragment.getInstance(team),"Players")
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.setText("Overview")
        tabLayout.getTabAt(1)!!.setText("Players")

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_TEAM)
                    .whereArgs("(ID_TEAM = {id})",
                            "id" to team.idTeam.toString())
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteTeam.TABLE_TEAM,
                        FavoriteTeam.IDTEAM to team.idTeam,
                        FavoriteTeam.STRTEAM to team.strTeam,
                        FavoriteTeam.STRTEAMBADGE to team.strTeamBadge,
                        FavoriteTeam.STRDESCRIPTIONEN to team.strDescriptionEN,
                        FavoriteTeam.INTFORMEDYEAR to team.intFormedYear
                        )
            }
            toast("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_TEAM, "(ID_TEAM = {id})", "id" to team.idTeam.toString())
            }
            toast("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }


}
