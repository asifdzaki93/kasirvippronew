package id.kasirvippro.android.feature.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.kasirvippro.android.MyApplication
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.home.HomeFragment
import id.kasirvippro.android.feature.menu.MenuFragment
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.feature.news.NewsFragment
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import id.kasirvippro.android.models.user.User
import java.util.*


class MainActivity : BaseActivity<MainPresenter, MainContract.View>(), MainContract.View,MenuFragment.MenuClick{

    private var menuFragment:MenuFragment = MenuFragment.newInstance()
    private var homeFragment:HomeFragment = HomeFragment.newInstance()
    private var newsFragment:NewsFragment = NewsFragment.newInstance()
    private var ft: FragmentTransaction? = null
    private var isPressTwice: Boolean = false
    private var appSession = AppSession()

    lateinit var adView : AdView
    lateinit var mBtn : Button


    override fun createPresenter(): MainPresenter {
        return MainPresenter(this, this)
    }


    override fun createLayout(): Int {
        return R.layout.activity_main

    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){

        loadLocate() // call LoadLocate

        // Load an ad into the AdMob banner view.
        adView = findViewById<View>(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.visibility = View.GONE



        val navigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        replaceContent(R.id.navigation_home)
                        true
                    }
                    R.id.navigation_account -> {
                        replaceContent(R.id.navigation_account)
                        true
                    }
                    R.id.navigation_news -> {
                        replaceContent(R.id.navigation_news)
                        true
                    }
                    else -> {
                        true
                    }
                }
            }

        bottomBar.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        bottomBar.itemIconTintList = null
    }



    private fun replaceContent(resId: Int) {
        ft = supportFragmentManager.beginTransaction()
        getPresenter()?.setSelectedIdMenu(resId)
        when (resId) {
            R.id.navigation_home -> {
                supportActionBar?.title = getString(R.string.app_name)
                if (homeFragment.isAdded) {
                    ft!!.show(homeFragment)
                } else {
                    ft!!.add(R.id.fragment_container, homeFragment)
                }
                ft!!.commit()
                hideFragment(ft!!, menuFragment)
                hideFragment(ft!!, newsFragment)
            }

            R.id.navigation_account -> {
                supportActionBar?.title = getString(R.string.account)
                if (menuFragment.isAdded) {
                    ft!!.show(menuFragment)
                } else {
                    ft!!.add(R.id.fragment_container, menuFragment)
                }
                ft!!.commit()
                hideFragment(ft!!, homeFragment)
                hideFragment(ft!!, newsFragment)
            }
            R.id.navigation_news -> {
                supportActionBar?.title = getString(R.string.tab_news)
                if (newsFragment.isAdded) {
                    ft!!.show(newsFragment)
                } else {
                    ft!!.add(R.id.fragment_container, newsFragment)
                }
                ft!!.commit()
                hideFragment(ft!!, homeFragment)
                hideFragment(ft!!, menuFragment)
            }

            else -> {
                supportActionBar?.title = getString(R.string.app_name)
                if (homeFragment.isAdded) {
                    ft!!.show(homeFragment)
                } else {
                    ft!!.add(R.id.fragment_container, homeFragment)
                }
                ft!!.commit()
                hideFragment(ft!!, menuFragment)
                hideFragment(ft!!, newsFragment)

            }
        }
    }

    private fun hideFragment(ft: FragmentTransaction, fragment: Fragment) {
        if (fragment.isAdded) {
            ft.hide(fragment)
        }
    }

    override fun selectMenu(resId: Int) {
        bottomBar.selectedItemId = resId
    }

    override fun onBackPressed() {
        if(R.id.navigation_home == getPresenter()?.getSelectedIdMenu()){
            if (!isPressTwice) {
                isPressTwice = true
                toast(this, "Click again to exit")
                Handler().postDelayed({ isPressTwice = false }, 2000)
                return
            }


            if (Build.VERSION.SDK_INT >= 16) {
                finishAffinity()
            } else {
                MyApplication.exit(this)
            }
        }
        else{
            selectMenu(R.id.navigation_home)
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            elevation = 0f
            //setBackgroundDrawable(ContextCompat.getDrawable(this@MainActivity,R.drawable.gradient_blue))
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun setInfo(user: User) {

        if(user.type == "free"){
            adView.visibility = View.VISIBLE
        }
        if(user.type == "premium"){
            adView.visibility = View.GONE
        }

    }


    override fun loadStore() {
        if(homeFragment.isAdded){
            homeFragment.reloadData()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun showErrorMessage(code: Int, msg: String) {
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> toast(this,msg)
        }

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_support) {
            openCS()
            return true
        }
        if (id == R.id.action_language) {
            showChangeLang()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun openCS() {
        val url = "https://api.whatsapp.com/send?phone=+6287788934569"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
        /*val tags = ArrayList<String>()
        tags.add("order_queries")

        val convOptions = ConversationOptions()
            .filterByTags(tags, "Order Queries")
        Freshchat.showConversations(this,convOptions)
         */
    }

    private fun showChangeLang() {

        val listItmes = arrayOf("English", "Indonesia")

        val mBuilder = AlertDialog.Builder(this@MainActivity)
        mBuilder.setTitle(getString(R.string.change_language))
        mBuilder.setSingleChoiceItems(listItmes, -1) { dialog, which ->
            if (which == 0) {
                setLocate("en")
                recreate()
            } else if (which == 1) {
                setLocate("in")
                recreate()
            }

            dialog.dismiss()
        }
        val mDialog = mBuilder.create()

        mDialog.show()

    }

    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            setLocate(language)
        }
    }



}
