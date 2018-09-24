package id.dicoding.nian.footballapps.team

import android.text.Layout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import android.R.color.white
import android.graphics.Color

/**
 * Created by nian on 9/17/18.
 */

class ItemTeamUI : AnkoComponent<ViewGroup> {

    companion object {
        const val strTeam = 1
        const val strTeamBadge = 2

    }
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL
                imageView {
                    id=strTeamBadge
                }.lparams{
                    margin = dip(15)
                    width = dip(50)
                    height = dip(50)
                }
                textView {
                    id = strTeam
                    textSize = 16f
                    gravity = Gravity.CENTER_VERTICAL
                    textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                }.lparams{
                    margin = dip(15)
                    width = matchParent
                    height = wrapContent
                }

            }
        }
    }
}