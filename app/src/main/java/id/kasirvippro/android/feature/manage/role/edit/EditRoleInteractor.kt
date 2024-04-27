package id.kasirvippro.android.feature.manage.role.edit

import android.content.Context
import com.google.gson.Gson
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class EditRoleInteractor(var output: EditRoleContract.InteractorOutput?) : EditRoleContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

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

    override fun callGetProfileAPI(context: Context, restModel: RoleRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getDetailRole(key!!,id).subscribeWith(object : DisposableObserver<List<Role>>() {

            override fun onNext(@NonNull response: List<Role>) {
                output?.onSuccessGetProfile(response)
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

    override fun callMenuOrderAPI(context: Context, restModel: RoleRestModel, value: String, id: String, name: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.updateRole(key!!,value, id, name).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessMenuOrder(response.message)
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

    override fun saveUser(user: Role) {
        appSession.setSharedPreferenceString(AppConstant.USER,user.json())
    }


    override fun loadData(context: Context): Role? {
        val json = appSession.getSharedPreferenceString(context,AppConstant.USER) ?: return null

        return Gson().fromJson(json,Role::class.java)
    }
}