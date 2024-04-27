package id.kasirvippro.android.feature.manage.packages.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.packages.PackagesRestModel

interface EditPackagesContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setPackagesName(name: String?, price: String?)
        fun openImageChooser()
        fun loadPhoto(path:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun onCheck(name:String, price:String)
        fun onCheckPhoto()
        fun setImagePhotoPath(path:String?)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callEditPackagesAPI(context: Context, model:PackagesRestModel, id:String, name:String, price: String,img:String?)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditCategory(msg: String?)
        fun onFailedEditCategory(code:Int,msg:String)
    }


}