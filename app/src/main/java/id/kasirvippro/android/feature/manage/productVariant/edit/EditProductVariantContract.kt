package id.kasirvippro.android.feature.manage.productVariant.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel

interface EditProductVariantContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openScanPage()
        fun openImageChooser()
        fun loadPhoto(path:String)
        fun setProductName(value:String)
        fun setStock(value:String)
        fun setAlertstock(value:String)
        fun setMinStock(value:String)
        fun setSellPrice(value:String)
        fun setBuyPrice(value:String)
        fun setDescription(value:String)
        fun setBarcode(value:String)
         fun setGrosir(value:Boolean)
        fun setHaveStock(value:Boolean)
        fun setGrosirPrice(value:String)
        fun setTax(value:String)
        fun onPremiumPage(isPremium:Boolean)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheckScan()
        fun onCheckPhoto()
        fun setImagePhotoPath(path:String?)
        fun onCheck(name:String,unit:String,buy:String,sell:String,stok:String,minstok:String,desc:String,barcode:String,grosir:String,tax:String,alertstock:String)
        fun searchByBarcode(search:String)
        fun setHaveStock(value:Int)
        fun setGrosir(value:Int)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callSearchByBarcodeAPI(context: Context, restModel:ProductRestModel, search:String)
        fun callEditProductAPI(context: Context, model: ProductRestModel, id:String,name:String,unit:String,kode:String,idkategori:String,jual:String,beli:String,stok:String,minstok:String,
                               img:String?,desk:String,online:String,haveSTok:String,hargagrosir:String,tax:String,alertstock:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessByBarcode(list: List<Product>)
        fun onFailedByBarcode(code:Int,msg:String)
    }


}