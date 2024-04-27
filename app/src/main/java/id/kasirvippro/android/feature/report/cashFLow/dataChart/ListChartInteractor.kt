package id.kasirvippro.android.feature.report.cashFLow.dataChart

import android.content.Context
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ListChartInteractor(var output: ListChartContract.InteractorOutput?) :
    ListChartContract.Interactor {

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

    override fun getUserPaket(context: Context): String? {
        return appSession.getPackage(context)
    }

    override fun getUserLevel(context: Context): String? {
        return appSession.getLevel(context)
    }

}