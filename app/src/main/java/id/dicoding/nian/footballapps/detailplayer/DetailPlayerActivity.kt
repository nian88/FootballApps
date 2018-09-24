package id.dicoding.nian.footballapps.detailplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.dicoding.nian.footballapps.R
import id.dicoding.nian.footballapps.model.Player
import kotlinx.android.synthetic.main.activity_detail_player.*
import org.jetbrains.anko.ctx

class DetailPlayerActivity : AppCompatActivity() {

    private lateinit var player: Player
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)


        supportActionBar?.title = "Detail Player"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        player = intent.getParcelableExtra<Player>("player")

        Glide.with(ctx).load(player.strCutout).into(strCutout)
        strPlayer.text="Name :"+player.strPlayer
        strNationality.text="Nationality" + player.strNationality
        dateBorn.text="Birth : ${player.strBirthLocation}, ${player.dateBorn}"
        strPosition.text="Position :" + player.strPosition
        strDescriptionEN.text=player.strDescriptionEN
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
