package id.dicoding.nian.footballapps.players

import android.text.Layout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import android.R.color.white
import android.graphics.Color
import android.graphics.Typeface

/**
 * Created by nian on 9/17/18.
 */

class ItemPlayerUI : AnkoComponent<ViewGroup> {

    companion object {
        const val strThumb = 1
        const val strPlayer = 2
        const val strPosition = 3

    }
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL
                imageView {
                    id=strThumb
                }.lparams{
                    margin = dip(15)
                    width = dip(50)
                    height = dip(50)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL
                        textView {
                            id = strPlayer
                            textSize = 16f
                            typeface= Typeface.DEFAULT_BOLD
                            gravity = Gravity.CENTER_VERTICAL
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams {
                            margin = dip(5)
                            width = matchParent
                            height = wrapContent
                        }
                        textView {
                            id = strPosition
                            textSize = 14f
                            gravity = Gravity.CENTER_VERTICAL
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams {
                            margin = dip(5)
                            width = matchParent
                            height = wrapContent
                        }
                }

            }
        }
    }
}