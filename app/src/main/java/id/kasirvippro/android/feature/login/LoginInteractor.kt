package id.kasirvippro.android.feature.login

import android.content.Context
import android.util.Log
import id.kasirvippro.android.models.user.Login
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.UserSQL
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class LoginInteractor(var output: LoginContract.InteractorOutput?) : LoginContract.Interactor {
    private val appSession = AppSession()
    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun clearSession() {
        appSession.clearSession()
    }

    override fun saveDeviceToken(token: String?) {
        token?.let {
            appSession.setSharedPreferenceString(AppConstant.DEVICE_TOKEN,it)
        }
    }

    override fun saveSession(user: Login) {
        val token = user.key
        val unit = user.currency
        val type = user.typestore
        val decimal = user.decimal
        val idstore = user.id_store
        appSession.setSharedPreferenceString(AppConstant.TOKEN,token)
        appSession.setSharedPreferenceString(AppConstant.CURRENCYES,unit)
        appSession.setSharedPreferenceString(AppConstant.TYPEW,type)
        appSession.setSharedPreferenceString(AppConstant.DECIMALS,decimal)
        appSession.setSharedPreferenceString(AppConstant.IDSTORES,idstore)
        appSession.setSharedPreferenceString(AppConstant.LOGIN,user.json())
    }

    public fun Success(response: List<Login>){
        output?.onSuccessLogin(response)
    }

    public fun Failed(){
        var errorCode = 999
        val errorMessage: String = "Username Atau Password Salah"
        output?.onFailedAPI(errorCode,errorMessage)
    }

    fun callGetProfileAPI(context: Context, restModel: UserRestModel,key : String) {
        disposable.add(restModel.getProfile(key).subscribeWith(object : DisposableObserver<List<User>>() {

            override fun onNext(@NonNull response: List<User>) {
                val dataManager = DataManager (context)
                dataManager.update(response[0].full_name!!)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {
            }
        }))
    }

    override fun callLoginAPI(context: Context, restModel: UserRestModel, phone: String, password: String, latitude:Double, longitude:Double) {
        disposable.add(restModel.login(phone,password,latitude,longitude).subscribeWith(object : DisposableObserver<List<Login>>() {

            override fun onNext(@NonNull response: List<Login>) {
                output?.onSuccessLogin(response)
                val dataManager = DataManager (context)
                dataManager.clear()
                val user = UserSQL(
                    response.get(0).key.toString(),
                    response.get(0).currency.toString(),
                    "",
                    response.get(0).user.toString(),
                    response.get(0).level.toString(),
                    response.get(0).master.toString(),
                    response.get(0).packages.toString(),
                    response.get(0).typestore.toString(),
                    response.get(0).decimal.toString(),
                    response.get(0).id_store.toString(),
                    phone,
                    password,
                    latitude.toString(),
                    longitude.toString()
                )
                var result = dataManager.add(user)
                Log.i("TES",result.toString());
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

}