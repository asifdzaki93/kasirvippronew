package id.kasirvippro.android.feature.manage.main

import android.content.Context
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ManageInteractor(var output: ManageContract.InteractorOutput?) : ManageContract.Interactor {

    private var appSession = AppSession()
    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun getToken(context: Context): String? {
        return appSession.getToken(context)
    }

    override fun getUserLevel(context: Context): String? {
        return appSession.getLevel(context)
    }

    override fun getUserPaket(context: Context): String? {
        return appSession.getPackage(context)
    }

    override fun callGetRoleAPI(context: Context, restModel: RoleRestModel) {
        disposable.add(restModel.getRoleUser(getToken(context)!!).subscribeWith(object : DisposableObserver<List<Role>>() {

            override fun onNext(@NonNull response: List<Role>) {
                output?.onSuccessGetRole(response)
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