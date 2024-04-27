package id.kasirvippro.android.feature.manage.divisi.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.divisi.Divisi
import id.kasirvippro.android.models.divisi.DivisiRestModel


interface DivisiListContract {

    interface View : BaseViewImpl {
        fun set(list:List<Divisi>)
        fun setData(list:List<Divisi>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddPage()
        fun openEditPage(data:Divisi)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun load()
        fun delete(id:String,position:Int)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetAPI(context: Context, restModel:DivisiRestModel)
        fun callDeleteAPI(context: Context, restModel:DivisiRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGet(list:List<Divisi>)
        fun onSuccessDelete(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}