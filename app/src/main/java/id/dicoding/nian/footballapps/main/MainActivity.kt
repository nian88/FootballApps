package id.dicoding.nian.footballapps.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.R.id.add_to_favorite
import id.dicoding.nian.footballapps.R.id.navSearch
import id.dicoding.nian.footballapps.favorite.FavoriteFragment
import id.dicoding.nian.footballapps.match.MatchFragment
import id.dicoding.nian.footballapps.searchmatch.SearchActivity
import id.dicoding.nian.footballapps.team.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    private var viewIsAtHome: Int = 0
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var menu : Menu = navigation.menu
        selectedMenu(menu.getItem(0))
        navigation.setOnNavigationItemSelectedListener {
            item: MenuItem ->  selectedMenu(item)

            false
        }
    }
    private fun selectedMenu(item : MenuItem) {
        item.isChecked = true
        when(item.itemId) {
            R.id.navMatch -> {
                selectedFragment(MatchFragment.getInstance())
                viewIsAtHome = 0
            }
            R.id.navTeams -> {
                selectedFragment(TeamsFragment.getInstance())
                viewIsAtHome = 1
            }
            R.id.navFavorite -> {
                selectedFragment(FavoriteFragment.getInstance())
                viewIsAtHome = 2
            }
        }
    }
    fun selectedFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (viewIsAtHome==0) {
                startActivity(intentFor<MainActivity>().noAnimation())
                finish()
            } else {
                finish()
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_match, menu)
        menuItem = menu
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            navSearch-> {
                startActivity<SearchActivity>(
                        "data" to viewIsAtHome
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}