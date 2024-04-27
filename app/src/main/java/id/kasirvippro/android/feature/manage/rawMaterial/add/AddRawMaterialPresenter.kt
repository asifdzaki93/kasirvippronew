package id.kasirvippro.android.feature.manage.rawMaterial.add

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel
import id.kasirvippro.android.utils.PermissionUtil


class AddRawMaterialPresenter(val context: Context, val view: AddRawMaterialContract.View) : BasePresenter<AddRawMaterialContract.View>(),
    AddRawMaterialContract.Presenter, AddRawMaterialContract.InteractorOutput {

    private var interactor = AddRawMaterialInteractor(this)
    private var restModel = RawMaterialRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission:PermissionCallback
    private lateinit var photoPermission:PermissionCallback
    private var premium:Boolean = false

    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        //view.onPremiumPage(premium)

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onCheck(name: String, unit: String, sell: String, stok: String,  desc: String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_product_name))
            return
        }
        if(unit.isBlank() || unit.isEmpty() || "0" == unit){
            view.showMessage(999,context.getString(R.string.err_empty_unit))
            return
        }


        if(sell.isBlank() || sell.isEmpty() || "0" == sell){
            view.showMessage(999,context.getString(R.string.err_empty_sell))
            return
        }

        interactor.callAddProductAPI(context,restModel,name,unit,sell,stok,desc)
    }

    override fun onSuccessAddProduct(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }




}