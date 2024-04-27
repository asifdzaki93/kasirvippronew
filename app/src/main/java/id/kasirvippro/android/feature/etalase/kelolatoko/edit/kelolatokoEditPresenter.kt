package id.kasirvippro.android.feature.etalase.kelolatoko.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.kelolatoko.Kelolatoko
import id.kasirvippro.android.models.kelolatoko.KelolatokoRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.PermissionUtil

class kelolatokoEditPresenter(val context: Context, val view: kelolatokoEditContract.View) : BasePresenter<kelolatokoEditContract.View>(),
    kelolatokoEditContract.Presenter, kelolatokoEditContract.InteractorOutput {


    private var interactor = kelolatokoEditInteractor(this)
    private var restModel = KelolatokoRestModel(context)
    private var data:Kelolatoko? = null
    private lateinit var photoPermission: PermissionCallback
    private var photoPath:String? = null
    private var permissionUtil: PermissionUtil = PermissionUtil(context)


    override fun onViewCreated(intent: Intent) {
        photoPermission = object : PermissionCallback {
            override fun onSuccess() {
                view.openImageChooser()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
        data = intent.getSerializableExtra(AppConstant.DATA) as Kelolatoko
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }
        checkStore()
    }


    override fun onCheckPhoto() {
        permissionUtil.checkCameraPermission(photoPermission)
    }


    override fun setImagePhotoPath(path: String?) {
        photoPath = path
    }

    override fun onCheck(nama_toko: String, alamat_toko:String, warna_toko:String, jam_operasional:String, linkfb:String, linkinstagram:String, nowa:String, subdomain:String) {
        if(nama_toko.isBlank() || nama_toko.isEmpty()){
            view.showMessage(999,"Online Store Name is required")
            return
        }

        if(alamat_toko.isBlank() || alamat_toko.isEmpty()){
            view.showMessage(999,"Shop address is required")
            return
        }

        if(warna_toko.isBlank() || warna_toko.isEmpty()){
            view.showMessage(999,"Store color must be selected")
            return
        }

        if(jam_operasional.isBlank() || jam_operasional.isEmpty()){
            view.showMessage(999,"Operating Hours is required")
            return
        }

        if(linkfb.isBlank() || linkfb.isEmpty()){
            view.showMessage(999,"Fb link must be filled, put a mark - if not available")
            return
        }


        if(linkinstagram.isBlank() || linkinstagram.isEmpty()){
            view.showMessage(999,"IG link must be filled, mark - if not available tidak")
            return
        }

        if(nowa.isBlank() || nowa.isEmpty()){
            view.showMessage(999,"Whatsapp number must be filled")
            return
        }
        if(subdomain.isBlank() || subdomain.isEmpty()){
            view.showMessage(999,"Link is required")
            return
        }

        interactor.callEditAPI(context,restModel,data?.id_online_store!!,nama_toko,alamat_toko,warna_toko,jam_operasional,linkfb,linkinstagram,nowa,subdomain, photoPath)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessEdit(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    private fun checkStore(){
        data?.let {

            view.loadPhoto(it.img!!)
            view.setStoreName(it.name_store!!)
            view.setSubdomain(it.subdomain!!)
            view.setColor(it.color_store!!)
            view.setAdress(it.address_store!!)
            view.setIg(it.linkinstagram!!)
            view.setFb(it.linkfb!!)
            view.setNowa(it.nowa!!)
            view.setJam(it.operational_hour!!)

        }
    }

}