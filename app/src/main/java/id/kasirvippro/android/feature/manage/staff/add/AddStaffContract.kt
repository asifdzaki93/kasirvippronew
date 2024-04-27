package id.kasirvippro.android.feature.manage.staff.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.staff.StaffRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel

interface AddStaffContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openImageChooser()
        fun loadPhoto(path:String)
        fun openStores(title:String, list: List<DialogModel>, selected: DialogModel?)
        fun setStoreName(value:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(name:String,email:String,phone:String,address:String,gaji:String,komisi:String,tunjangan:String,potongan:String)
        fun onCheckPhoto()
        fun setImagePhotoPath(path:String?)
        fun setLevel(value:String?)
        fun onCheckStore()
        fun setSelectedStore(data:DialogModel)


    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callAddAPI(context: Context,model:StaffRestModel,name:String,email:String,phone:String,address:String,
                       gaji:String,komisi:String,tunjangan:String,potongan:String,toko:String,posisi:String,img:String?)
        fun callGetStoressAPI(context: Context, restModel:StoreRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAdd(msg: String?)
        fun onSuccessGetStore(list:List<Store>)
        fun onFailedAPI(code:Int,msg:String)
    }


}