package id.kasirvippro.android.feature.pulsaPpob.main

import android.content.Context
import com.google.gson.Gson
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class PulsaPpobInteractor(var output: PulsaPpobContract.InteractorOutput?) :
    PulsaPpobContract.Interactor {

    private var appSession = AppSession()
    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }


    override fun getToken(context: Context): String {
        val key = appSession.getToken(context)
        return key!!
    }

    override fun getUserPaket(context: Context): String? {
        return appSession.getPackage(context)
    }

    override fun loadProfile(context: Context): User? {
        val json = appSession.getSharedPreferenceString(context, AppConstant.USER) ?: return null

        return Gson().fromJson(json,User::class.java)
    }

    override fun callGetProfileAPI(context: Context, restModel: UserRestModel) {
        disposable.add(restModel.getProfile(getToken(context)).subscribeWith(object : DisposableObserver<List<User>>() {

            override fun onNext(@NonNull response: List<User>) {
                output?.onSuccessGetProfile(response)

                val key = appSession.getToken(context)
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

    override fun saveUser(user: User) {
        appSession.setSharedPreferenceString(AppConstant.USER,user.json())
    }

}