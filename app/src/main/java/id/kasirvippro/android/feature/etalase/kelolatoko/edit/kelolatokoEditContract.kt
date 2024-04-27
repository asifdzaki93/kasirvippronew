package id.kasirvippro.android.feature.etalase.kelolatoko.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.kelolatoko.KelolatokoRestModel

interface kelolatokoEditContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openChooseColor()
        fun loadPhoto(path:String)
        fun setStoreName(value: String?)
        fun setSubdomain(value: String?)
        fun setColor(value: String?)
        fun setAdress(value: String?)
        fun setIg(value: String?)
        fun setFb(value: String?)
        fun setNowa(value: String?)
        fun setJam(value: String?)
        fun openImageChooser()

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onCheckPhoto()
        fun setImagePhotoPath(path:String?)
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun onCheck(nama_toko: String,alamat_toko:String,warna_toko:String,jam_operasional:String,linkfb:String,linkinstagram:String,nowa:String,subdomain:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callEditAPI(context: Context, model:KelolatokoRestModel, id:String,nama_toko:String,alamat_toko:String,warna_toko:String,jam_operasional:String,linkfb:String,linkinstagram:String,nowa:String,subdomain:String,img:String?)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEdit(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}