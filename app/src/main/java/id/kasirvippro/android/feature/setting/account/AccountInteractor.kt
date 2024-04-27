package id.kasirvippro.android.feature.setting.account

import android.content.Context
import com.google.gson.Gson
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class AccountInteractor(var output: AccountContract.InteractorOutput?) : AccountContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun loadProfile(context: Context): User? {
        val json = appSession.getSharedPreferenceString(context,AppConstant.USER) ?: return null

        return Gson().fromJson(json,User::class.java)
    }

    override fun callUpdateAPI(context: Context, model: UserRestModel, name:String,email:String,phone:String,address:String,img:String?) {
        val key = appSession.getToken(context)
        disposable.add(model.updateProfile(key!!,name,email,phone,address,img).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessUpdate(response.message)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
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