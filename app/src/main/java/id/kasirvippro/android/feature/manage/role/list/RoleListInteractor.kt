package id.kasirvippro.android.feature.manage.role.list;

import android.content.Context
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class RoleListInteractor(var output: RoleListContract.InteractorOutput?) : RoleListContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetRoleAPI(context: Context, restModel: RoleRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getRole(key!!).subscribeWith(object : DisposableObserver<List<Role>>() {

            override fun onNext(@NonNull response: List<Role>) {
                output?.onSuccessGetRole(response)
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