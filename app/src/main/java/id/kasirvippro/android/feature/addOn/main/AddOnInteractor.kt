package id.kasirvippro.android.feature.addOn.main

import android.content.Context
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class AddOnInteractor(var output: AddOnContract.InteractorOutput?) : AddOnContract.Interactor {

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

    override fun getUserLevel(context: Context): String? {
        return appSession.getLevel(context)
    }

    override fun getUserPaket(context: Context): String? {
        return appSession.getPackage(context)
    }


    override fun callGetProfileAPI(context: Context, restModel: UserRestModel) {
        disposable.add(restModel.getProfile(getToken(context)).subscribeWith(object : DisposableObserver<List<User>>() {

            override fun onNext(@NonNull response: List<User>) {
                output?.onSuccessGetProfile(response)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "Terjadi kesalahan"
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