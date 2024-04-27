package id.kasirvippro.android.feature.manage.product.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel

interface AddProductContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openScanPage()
        fun openImageChooser()
        fun openAddCategory()
        fun openChooseUnit()
        fun loadPhoto(path:String)
        fun openCategories(title:String, list: List<DialogModel>, selected: DialogModel?)
        fun setCategoryName(name:String)
        fun openEditPage(product:Product)
        fun onPremiumPage(isPremium:Boolean)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
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

    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callGetCategoriesAPI(context: Context, restModel:CategoryRestModel)
        fun callSearchByBarcodeAPI(context: Context, restModel:ProductRestModel, search:String)
        fun callAddProductAPI(context: Context, model: ProductRestModel, name:String,unit:String,kode:String,idkategori:String,jual:String,beli:String,stok:String,minstok:String,
                              img:String?,desk:String,online:String,haveSTok:String,hargagrosir:String,tax:String,alertstock:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAddProduct(msg: String?)
        fun onSuccessGetCategories(list:List<Category>)
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessByBarcode(list: List<Product>)
        fun onFailedByBarcode(code:Int,msg:String)
    }


}