package id.dicoding.nian.footballapps.match

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

class ItemMatchUI : AnkoComponent<ViewGroup> {

    companion object {
        const val teamHome = 1
        const val teamAway = 2
        const val scoreMatch = 3
        const val dateTimeMatch = 4
        const val imageReminder = 5

    }
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                gravity = Gravity.RIGHT
                orientation = LinearLayout.VERTICAL
                imageView {
                    id = imageReminder

                }.lparams {
                    width = matchParent
                    height = wrapContent
                }
            }
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    gravity = Gravity.RIGHT
                    gravity = Gravity.CENTER_VERTICAL
                    orientation = LinearLayout.VERTICAL
                    textView {
                        id = dateTimeMatch
                        textSize = 14f
                        gravity = Gravity.CENTER
                        textColor = Color.RED
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                    }
                    textView {
                        id = scoreMatch
                        textSize = 38f
                        gravity = Gravity.CENTER
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        margin = dip(15)
                    }

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        weightSum = 2f
                        gravity = Gravity.CENTER_VERTICAL
                        orientation = LinearLayout.HORIZONTAL
                        textView {
                            id = teamHome
                            textSize = 16f
                            gravity = Gravity.CENTER
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            margin = dip(15)
                            width = dip(0)
                            weight = 1f
                            height = wrapContent
                        }
                        textView {
                            id = teamAway
                            textSize = 16f
                            gravity = Gravity.CENTER
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            margin = dip(15)
                            width = dip(0)
                            weight = 1f
                            height = wrapContent
                        }
                    }
                }
            }
        }
    }

}