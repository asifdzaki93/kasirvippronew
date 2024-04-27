package id.kasirvippro.android.feature.manage.rawMaterial.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.PermissionUtil


class EditRawMaterialPresenter(val context: Context, val view: EditRawMaterialContract.View) : BasePresenter<EditRawMaterialContract.View>(),
    EditRawMaterialContract.Presenter, EditRawMaterialContract.InteractorOutput {

    private var interactor = EditRawMaterialInteractor(this)
    private var restModel = RawMaterialRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission:PermissionCallback
    private lateinit var photoPermission:PermissionCallback
    private var data : RawMaterial ?= null
    private var premium:Boolean = false


    override fun onViewCreated(intent:Intent) {


        data = intent.getSerializableExtra(AppConstant.DATA) as RawMaterial
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        checkProduct()
        premium = "1" == interactor.getUserPaket(context)
        //view.onPremiumPage(premium)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onCheck(name: String, unit: String,sell: String, stok: String,desc: String) {
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
        interactor.callEditProductAPI(context,restModel,data?.id_raw_material!!,name,unit,sell,stok,desc)
    }

    override fun onSuccessEditProduct(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    private fun checkProduct(){
        data?.let {
            view.setProductName(it.name!!)
            view.setUnitName(it.unit!!)
            view.setSellPrice(it.price!!)
            view.setStock(it.stock!!)
            view.setDescription(it.description!!)
        }
    }


}