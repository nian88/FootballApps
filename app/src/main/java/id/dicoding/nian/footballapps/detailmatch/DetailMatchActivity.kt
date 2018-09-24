package id.dicoding.nian.footballapps.detailmatch

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.R.drawable.ic_add_to_favorites
import id.dicoding.nian.footballapps.R.drawable.ic_added_to_favorites
import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.model.Team
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb
import kotlinx.android.synthetic.main.activity_detail_match.*
import id.dicoding.nian.footballapps.R.id.add_to_favorite
import id.dicoding.nian.footballapps.R.menu.menu_detail
import id.dicoding.nian.footballapps.model.FavoriteMatch
import id.dicoding.nian.footballapps.model.FavoriteTeam
import id.dicoding.nian.footballapps.utils.database
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity(),DetailMatchView {

    private var menuItem: Menu? = null
    private lateinit var event: Event
    private lateinit var presenter: DetailMatchPresenter
    private var isFavorite: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        supportActionBar?.title = "Detail Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        event = intent.getParcelableExtra<Event>("match")


        val request = ServicesTheSportsDb()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)

        presenter.getDetailTeam(event.idHomeTeam,0)
        presenter.getDetailTeam(event.idAwayTeam,1)


        dateEvent.text = event.dateEvent
        strTime.text = event.strTime!!.split("+")[0]
        strHomeTeam.text = event.strHomeTeam
        strAwayTeam.text = event.strAwayTeam
        intHomeScore.text = event.intHomeScore
        intAwayScore.text = event.intAwayScore

        strHomeFormation.text = event.strHomeFormation
        strAwayFormation.text = event.strAwayFormation

        strHomeGoalDetails.text = event.strHomeGoalDetails
        strAwayGoalDetails.text = event.strAwayGoalDetails

        strHomeLineupGoalkeeper.text = event.strHomeLineupGoalkeeper
        strAwayLineupGoalkeeper.text = event.strAwayLineupGoalkeeper

        strHomeLineupDefense.text = event.strHomeLineupDefense
        strAwayLineupDefense.text = event.strAwayLineupDefense

        strAwayLineupMidfield.text = event.strAwayLineupMidfield
        strAwayLineupMidfield.text = event.strAwayLineupMidfield

        strHomeLineupForward.text = event.strHomeLineupForward
        strAwayLineupForward.text = event.strAwayLineupForward

        strHomeLineupSubstitutes.text = event.strHomeLineupSubstitutes
        strAwayLineupSubstitutes.text = event.strAwayLineupSubstitutes

        intHomeShots.text = event.intHomeShots
        intAwayShots.text = event.intAwayShots

        favoriteState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_detail, menu)
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
            add_to_favorite -> {
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
            val result = select(FavoriteMatch.TABLE_FAVORITE)
                    .whereArgs("(ID_EVENT = {id})",
                            "id" to event.idEvent.toString())
            val favorite = result.parseList(classParser<Event>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE,
                        FavoriteMatch.IDEVENT to event.idEvent,
                        FavoriteMatch.IDHOMETEAM to event.idHomeTeam,
                        FavoriteMatch.IDAWAYTEAM to event.idAwayTeam,
                        FavoriteMatch.HOMETEAM to event.strHomeTeam,
                        FavoriteMatch.AWAYTEAM to event.strAwayTeam,
                        FavoriteMatch.HOMESCORE to event.intHomeScore,
                        FavoriteMatch.ROUND to event.intRound,
                        FavoriteMatch.AWAYSCORE to event.intAwayScore,
                        FavoriteMatch.HOMEGOALDETAILS to event.strHomeGoalDetails,
                        FavoriteMatch.HOMEREDCARDS to event.strHomeRedCards,
                        FavoriteMatch.HOMEYELLOWCARDS to event.strHomeYellowCards,
                        FavoriteMatch.HOMELINEUPGOALKEEPER to event.strHomeLineupGoalkeeper,
                        FavoriteMatch.HOMELINEUPDEFENSE to event.strHomeLineupDefense,
                        FavoriteMatch.HOMELINEUPMIDFIELD to event.strHomeLineupMidfield,
                        FavoriteMatch.HOMELINEUPFORWARD to event.strHomeLineupForward,
                        FavoriteMatch.HOMELINEUPSUBSTITUTES to event.strHomeLineupSubstitutes,
                        FavoriteMatch.HOMEFORMATION to event.strHomeFormation,
                        FavoriteMatch.AWAYREDCARDS to event.strAwayRedCards,
                        FavoriteMatch.AWAYYELLOWCARDS to event.strAwayYellowCards,
                        FavoriteMatch.AWAYGOALDETAILS to event.strAwayGoalDetails,
                        FavoriteMatch.AWAYLINEUPGOALKEEPER to event.strAwayLineupGoalkeeper,
                        FavoriteMatch.AWAYLINEUPDEFENSE to event.strAwayLineupDefense,
                        FavoriteMatch.AWAYLINEUPMIDFIELD to event.strAwayLineupMidfield,
                        FavoriteMatch.AWAYLINEUPFORWARD to event.strAwayLineupForward,
                        FavoriteMatch.AWAYLINEUPSUBSTITUTES to event.strAwayLineupSubstitutes,
                        FavoriteMatch.AWAYFORMATION to event.strAwayFormation,
                        FavoriteMatch.HOMESHOTS to event.intHomeShots,
                        FavoriteMatch.AWAYSHOTS to event.intAwayShots,
                        FavoriteMatch.DATEEVENT to event.dateEvent,
                        FavoriteMatch.STRTIME to event.strTime)
            }
            toast("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE, "(ID_EVENT = {id})", "id" to event.idEvent.toString())
            }
            toast("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showTeamList(data: List<FavoriteTeam>,flag:Int) {
        if(flag==0){
            Glide.with(ctx).load(data[0].strTeamBadge).into(badgeHome)
        }else{
            Glide.with(ctx).load(data[0].strTeamBadge).into(badgeAway)
        }
    }
}
