package ru.tinkoff.android.ashihmin.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import ru.tinkoff.android.ashihmin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val pagerAdapter = SectionPagerAdapter(supportFragmentManager, this)
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = pagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
    }



    private class SectionPagerAdapter(fm: FragmentManager?, val context: Context) : FragmentPagerAdapter(
        fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

        override fun getCount(): Int = 3

        override fun getItem(p0: Int): Fragment {
            when(p0) {
                0 -> return RandomGifFragment()
                1 -> return LatestGifsFragment()
                2 -> return TopGifsFragment()
            }
            return Fragment()
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position) {
                0 -> return context.resources.getText(R.string.random)
                1 -> return context.resources.getText(R.string.latest)
                2 -> return context.resources.getText(R.string.top)
            }
            return null
        }
    }

}