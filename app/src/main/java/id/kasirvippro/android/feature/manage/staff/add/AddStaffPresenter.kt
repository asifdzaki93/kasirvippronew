package id.kasirvippro.android.feature.manage.staff.add

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.staff.StaffRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.utils.PermissionUtil


class AddStaffPresenter(val context: Context, val view: AddStaffContract.View) : BasePresenter<AddStaffContract.View>(),
    AddStaffContract.Presenter, AddStaffContract.InteractorOutput {

    private var interactor = AddStaffInteractor(this)
    private var restModel = StaffRestModel(context)
    private var photoPath:String? = null
    private var level:String? = ""
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var photoPermission:PermissionCallback
    private var storeRestModel = StoreRestModel(context)
    private var stores:ArrayList<DialogModel> = ArrayList()
    private var store: DialogModel? = null

    override fun onViewCreated() {
        photoPermission = object : PermissionCallback {
            override fun onSuccess() {
                view.openImageChooser()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
    }

    override fun onCheck(name:String,email:String,phone:String,address:String,gaji:String,komisi:String,tunjangan:String,potongan:String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name))
            return
        }

/*
        if(email.isBlank() || email.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_email))
            return
        }

        if(!Helper.isEmailValid(email)){
            view.showMessage(999,context.getString(R.string.err_email_format))
            return
        }
*/

        if(phone.isBlank() || phone.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_phone))
            return
        }

        if(!Helper.isPhoneValid(phone)){
            view.showMessage(999,context.getString(R.string.err_phone_format))
            return
        }

        if(address.isBlank() || address.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_address))
            return
        }

        if(store == null){
            view.showMessage(999,"Store is required")
            return
        }

        interactor.callAddAPI(context,restModel,name,email,phone,address,gaji,komisi,tunjangan,potongan,store?.id!!,level!!,photoPath)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessAdd(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onCheckPhoto() {
        permissionUtil.checkCameraPermission(photoPermission)
    }

    override fun setImagePhotoPath(path: String?) {
        photoPath = path
    }

    override fun setLevel(value: String?) {
        value?.let {
            level = it
        }
    }

    override fun onCheckStore() {
        if(stores.isEmpty()){
            interactor.callGetStoressAPI(context,storeRestModel)
        }
        else{
            view.openStores("Select Store",stores,store)
        }
    }

    override fun setSelectedStore(data: DialogModel) {
        store = data
        view.setStoreName(data.value!!)
    }

    override fun onSuccessGetStore(list: List<Store>) {
        if(list.isEmpty()){
            view.showMessage(999,"There are no stores yet")
            return
        }
        stores = ArrayList()
        for(cat in list){
            val model = DialogModel()
            model.id = cat.id_store
            model.value = cat.name_store
            stores.add(model)
        }
        view.openStores("Select Store",stores,store)
    }
}