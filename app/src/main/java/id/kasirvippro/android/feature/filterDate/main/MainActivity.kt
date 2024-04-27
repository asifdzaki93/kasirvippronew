package id.kasirvippro.android.feature.filterDate.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import androidx.core.content.ContextCompat
import id.kasirvippro.android.feature.filterDate.daily.DailyFragment
import id.kasirvippro.android.feature.filterDate.monthly.MonthlyFragment
import id.kasirvippro.android.feature.filterDate.weekly.WeeklyFragment
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_filter.*


class MainActivity : BaseActivity<MainPresenter, MainContract.View>(), MainContract.View,
DailyFragment.Listener,WeeklyFragment.Listener,MonthlyFragment.Listener{

    private var dailyFragment: DailyFragment = DailyFragment.newInstance()
    private var weeklyFragment: WeeklyFragment = WeeklyFragment.newInstance()
    private var monthlyFragment: MonthlyFragment = MonthlyFragment.newInstance()

    private var ft: FragmentTransaction? = null

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_filter
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
        showDaily()
    }

    private fun renderView(){

        fl_parent.setOnClickListener {
            onBackPressed()
        }

        btn_close.setOnClickListener {
            onBackPressed()
        }

        btn_daily.setOnClickListener {
            getPresenter()?.onCheckDaily()
        }

        btn_weekly.setOnClickListener {
            getPresenter()?.onCheckWeekly()
        }

        btn_monthly.setOnClickListener {
            getPresenter()?.onCheckMonthly()
        }




    }

    private fun replaceContent(resId: Int) {
        ft = supportFragmentManager.beginTransaction()
        when (resId) {
            AppConstant.FilterDate.DAILY -> {
                if (dailyFragment.isAdded) {
                    ft!!.show(dailyFragment)
                } else {
                    ft!!.add(R.id.fragment_container, dailyFragment)
                }
                ft!!.commit()
                hideFragment(ft!!, weeklyFragment)
                hideFragment(ft!!, monthlyFragment)
            }
            AppConstant.FilterDate.WEEKLY -> {
                if (weeklyFragment.isAdded) {
                    ft!!.show(weeklyFragment)
                } else {
                    ft!!.add(R.id.fragment_container, weeklyFragment)
                }
                ft!!.commit()
                hideFragment(ft!!, dailyFragment)
                hideFragment(ft!!, monthlyFragment)
            }
            else -> {
                if (monthlyFragment.isAdded) {
                    ft!!.show(monthlyFragment)
                } else {
                    ft!!.add(R.id.fragment_container, monthlyFragment)
                }
                ft!!.commit()
                hideFragment(ft!!, dailyFragment)
                hideFragment(ft!!, weeklyFragment)
            }
        }
    }

    private fun hideFragment(ft: FragmentTransaction, fragment: Fragment) {
        if (fragment.isAdded) {
            ft.hide(fragment)
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onSelected(data: FilterDialogDate?) {
        if(data == null){
            onBackPressed()
        }
        else{
            val intent = Intent()
            intent.putExtra(AppConstant.DATA,data)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    override fun showDaily() {
        btn_daily.background = ContextCompat.getDrawable(this,R.drawable.rounded_accent_4dp)
        btn_daily.setTextColor(ContextCompat.getColor(this,R.color.white))

        btn_weekly.background = ContextCompat.getDrawable(this,R.drawable.rounded_accent_outline_4dp)
        btn_weekly.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

        btn_monthly.background = ContextCompat.getDrawable(this,R.drawable.rounded_accent_outline_4dp)
        btn_monthly.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

        getPresenter()?.setSelectedMenu(AppConstant.FilterDate.DAILY)
        replaceContent(AppConstant.FilterDate.DAILY)
    }

    override fun showWeekly() {
        btn_weekly.background = ContextCompat.getDrawable(this,R.drawable.rounded_accent_4dp)
        btn_weekly.setTextColor(ContextCompat.getColor(this,R.color.white))

        btn_daily.background = ContextCompat.getDrawable(this,R.drawable.rounded_accent_outline_4dp)
        btn_daily.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

        btn_monthly.background = ContextCompat.getDrawable(this,R.drawable.rounded_accent_outline_4dp)
        btn_monthly.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

        getPresenter()?.setSelectedMenu(AppConstant.FilterDate.WEEKLY)
        replaceContent(AppConstant.FilterDate.WEEKLY)
    }

    override fun showMonthly() {
        btn_monthly.background = ContextCompat.getDrawable(this,R.drawable.rounded_accent_4dp)
        btn_monthly.setTextColor(ContextCompat.getColor(this,R.color.white))

        btn_weekly.background = ContextCompat.getDrawable(this,R.drawable.rounded_accent_outline_4dp)
        btn_weekly.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

        btn_daily.background = ContextCompat.getDrawable(this,R.drawable.rounded_accent_outline_4dp)
        btn_daily.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

        getPresenter()?.setSelectedMenu(AppConstant.FilterDate.MONTHLY)
        replaceContent(AppConstant.FilterDate.MONTHLY)
    }




}
