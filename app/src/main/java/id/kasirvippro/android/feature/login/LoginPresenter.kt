package id.kasirvippro.android.feature.login

import android.app.Activity
import android.app.Service
import android.content.Context
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.user.Login
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.PermissionUtil
import mumayank.com.airlocationlibrary.AirLocation

class LoginPresenter(val context: Context, val view: LoginContract.View) :
    BasePresenter<LoginContract.View>(),
    LoginContract.Presenter, LoginContract.InteractorOutput {

    private var interactor: LoginInteractor = LoginInteractor(this)
    private var userRestModel = UserRestModel(context)
    private var phone = ""
    private var password = ""
    private lateinit var locationPermission: PermissionCallback
    private var airLocation: AirLocation? = null
    private var permissionUtil = PermissionUtil(context)

    override fun onViewCreated() {
        interactor.clearSession()
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                interactor.saveDeviceToken(token)

            })

        locationPermission = object : PermissionCallback {
            override fun onSuccess() {
                airLocation = AirLocation(context as Activity,
                    shouldWeRequestPermissions = true,
                    shouldWeRequestOptimization = true,
                    callbacks = object: AirLocation.Callbacks {
                        override fun onSuccess(location: Location) {
                            view.showLoadingDialog()
                            var connectivity : ConnectivityManager? = null
                            var info : NetworkInfo? = null
                            connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                                    as ConnectivityManager
                            if ( connectivity != null)
                            {
                                info = connectivity!!.activeNetworkInfo
                                if (info != null)
                                {
                                    if (info!!.state == NetworkInfo.State.CONNECTED)
                                    {
                                        interactor.callLoginAPI(context, userRestModel, phone, password, location.latitude,location.longitude)
                                    }
                                }
                                else
                                {
                                    val dataManager = DataManager (context)
                                    val result = dataManager.user(phone,password)
                                    if(result != null){
                                        Log.i("TAG", result.key)
                                        var response: ArrayList<Login> = arrayListOf()
                                        var login = Login(
                                            result.key,
                                            result.currency,
                                            result.user,
                                            result.level,
                                            result.master,
                                            result.packages,
                                            result.typestore,
                                            result.decimal,
                                            result.id_store
                                        );
                                        response.add(login);
                                        interactor.Success(response);
                                    }else{
                                        interactor.Failed();
                                    }
                                }
                            }
                        }

                        override fun onFailed(locationFailedEnum: AirLocation.LocationFailedEnum) {
                            onFailedAPI(999,context.getString(R.string.reason_permission_location))
                        }
                    })
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_location))

            }

        }
    }

    override fun onBtnLoginCheck(phone: String, password: String) {
        if (phone.isEmpty()) {
            view.enableLoginBtn(false)
            return
        }
        if (!Helper.isPhoneValid(phone)) {
            view.enableLoginBtn(false)
            return
        }
        if (password.isEmpty()) {
            view.enableLoginBtn(false)
            return
        }
        if (password.length > 5) {
            view.enableLoginBtn(true)
            return
        }
        view.enableLoginBtn(false)
    }

    override fun onLogin(phone: String, password: String) {
        if (phone.isEmpty()) {
            onFailedAPI(999, context.getString(R.string.err_empty_phone))
            return
        }
        if (!Helper.isPhoneValid(phone)) {
            onFailedAPI(999, context.getString(R.string.err_phone_format))
            return
        }
        if (password.isEmpty()) {
            onFailedAPI(999, "Password is required")
            return
        }
        if (password.length < 6) {
            onFailedAPI(999, "Minimum 6 character password")
            return
        }
        this.phone = phone
        this.password = password

        permissionUtil.checkLocationPermission(locationPermission)

    }

    override fun onSuccessLogin(list: List<Login>) {
        view.hideLoadingDialog()
        if (list.isEmpty()) {
            onFailedAPI(999, "User not found")
            return
        }

        val user = list[0]

        interactor.callGetProfileAPI(context,userRestModel,user.key!!)
        interactor.saveSession(user)
        view.showLoginSuccess()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.hideLoadingDialog()
        view.showToast(msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }


}