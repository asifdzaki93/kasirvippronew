package id.kasirvippro.android.feature.manage.product.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel

interface EditProductContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openScanPage()
        fun openImageChooser()
        fun loadPhoto(path:String)
        fun openCategories(title:String, list: List<DialogModel>, selected: DialogModel?)
        fun setProductName(value:String)
        fun setCategoryName(value:String)
        fun setUnitName(value:String)
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
        fun openVariantPage(id: String)
        fun openPriceVariantPage(id: String, detail: String)
        fun openRecipePage(id: String, detail: String)
        fun openChooseUnit()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheckScan()
        fun onCheckPhoto()
        fun setImagePhotoPath(path:String?)
        fun onCheckCategory()
        fun onCheck(name:String,unit:String,buy:String,sell:String,stok:String,minstok:String,desc:String,barcode:String,grosir:String,tax:String,alertstock:String)
        fun setSelectedCategory(data:DialogModel)
        fun searchByBarcode(search:String)
        fun setHaveStock(value:Int)
        fun setGrosir(value:Int)
        fun onVariant()
        fun onPriceVariant()
        fun onRecipe()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callGetCategoriesAPI(context: Context, restModel:CategoryRestModel)
        fun callSearchByBarcodeAPI(context: Context, restModel:ProductRestModel, search:String)
        fun callEditProductAPI(context: Context, model: ProductRestModel, id:String,name:String,unit:String,kode:String,idkategori:String,jual:String,beli:String,stok:String,minstok:String,
                               img:String?,desk:String,online:String,haveSTok:String,hargagrosir:String,tax:String,alertstock:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditProduct(msg: String?)
        fun onSuccessGetCategories(list:List<Category>)
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessByBarcode(list: List<Product>)
        fun onFailedByBarcode(code:Int,msg:String)
    }


}