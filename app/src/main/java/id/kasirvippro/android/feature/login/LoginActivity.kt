package id.kasirvippro.android.feature.login

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.forgot.ForgotActivity
import id.kasirvippro.android.feature.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity<LoginPresenter, LoginContract.View>(), LoginContract.View {
    override fun createPresenter(): LoginPresenter {
        return LoginPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_login
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    override fun enableLoginBtn(isLogin: Boolean) {
        btn_login.isEnabled = isLogin
    }

    private fun renderView(){
        val rotation = this.getResources().getConfiguration().orientation;
        val metrics = this.resources.displayMetrics

        val x = Math.pow((metrics.widthPixels / metrics.xdpi).toDouble(), 2.0)
        val y = Math.pow((metrics.heightPixels / metrics.ydpi).toDouble(), 2.0)
        val screenInches = Math.sqrt(x + y)
        val hasil = +Math.round(screenInches)

        if(hasil >= 7) {
            if (rotation == Configuration.ORIENTATION_PORTRAIT) {
                ll_error.visibility = View.VISIBLE
                ll_content.visibility = View.GONE
            } else {
                ll_error.visibility = View.GONE
                ll_content.visibility = View.VISIBLE
            }

        }else{

            if (rotation == Configuration.ORIENTATION_PORTRAIT) {
                ll_error.visibility = View.GONE
                ll_content.visibility = View.VISIBLE
            } else {
                ll_error.visibility = View.VISIBLE
                ll_content.visibility = View.GONE
            }

        }

        btn_password.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else{
                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        btn_login.setOnClickListener {
            val mail = et_email.text.toString()
            val pwd = et_password.text.toString()
            getPresenter()?.onLogin(mail,pwd)
        }

        btn_register.setOnClickListener {
            openRegisterPage()
        }

        btn_forgot.setOnClickListener {
            openForgotPage()
        }


    }

    override fun showLoginSuccess() {
        restartMainActivity()
    }

    override fun openRegisterPage() {
        startActivity(Intent(this, RegisterActivity::class.java))

    }

    override fun openForgotPage() {
        startActivity(Intent(this, ForgotActivity::class.java))

    }


    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }


}
