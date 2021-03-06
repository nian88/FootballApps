package id.dicoding.nian.footballapps.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by nian on 9/17/18.
 */
class TabAdapter (fm: FragmentManager)  : FragmentPagerAdapter(fm) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment{
        return mFragmentList[position]
    }
    override fun getCount(): Int = mFragmentList.size

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }
    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

}