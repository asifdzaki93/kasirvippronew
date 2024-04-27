package id.kasirvippro.android.feature.addOn.linkStore

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel


class LinkStorePresenter(val context: Context, val view: LinkStoreContract.View) : BasePresenter<LinkStoreContract.View>(),
    LinkStoreContract.Presenter, LinkStoreContract.InteractorOutput {

    private var interactor = LinkStoreInteractor(this)
    private var restModel = UserRestModel(context)
    private var user:User?=null
    private var level:String? = "kasir"

    override fun onViewCreated() {

        user = interactor.loadProfile(context)
        user?.let {
            view.setInfo(it)
        }

        level = interactor.getUserLevel(context)
        when(level){
            "master" -> view.onMasterPage(true)
            "admin" -> view.onAdminPage()
            else -> view.onSalesPage()
        }

        //loadProfile()
    }

    override fun loadProfile() {
        interactor.callGetProfileAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onSuccessGetProfile(list: List<User>) {
        if(list.isEmpty()){
            onFailedAPI(999,"Akun tidak ditemukan")
            return
        }

        val user = list[0]
        interactor.saveUser(user)
        view.setProfile(user.subdomain,user.signup,user.screen)
    }

    override fun onCopy() {
        user?.let { data ->
            if(!data.subdomain.isNullOrEmpty() && !data.subdomain.isNullOrBlank()){
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Online Store", data.subdomain)
                clipboard.setPrimaryClip(clip)
                view.showToast("The online store link has been copied to the clipboard")
            }
        }
    }

    override fun onCopy2() {
        user?.let { data ->
            if(!data.signup.isNullOrEmpty() && !data.signup.isNullOrBlank()){
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Registration Link", data.signup)
                clipboard.setPrimaryClip(clip)
                view.showToast("The Registration Link has been copied to the clipboard")
            }
        }
    }


    override fun onCopy3() {
        user?.let { data ->
            if(!data.screen.isNullOrEmpty() && !data.screen.isNullOrBlank()){
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Order screen link", data.screen)
                clipboard.setPrimaryClip(clip)
                view.showToast("The Order screen link has been copied to the clipboard")
            }
        }
    }

    override fun onShare() {
        user?.let { data ->
            if(!data.subdomain.isNullOrEmpty() && !data.subdomain.isNullOrBlank()){
                view.openShare(data.subdomain!!)
                Log.d("share",data.subdomain!!)
            }
        }
    }

    override fun onShare2() {
        user?.let { data ->
            if(!data.signup.isNullOrEmpty() && !data.signup.isNullOrBlank()){
                view.openShare2(data.signup!!)
            }
        }
    }

    override fun onShare3() {
        user?.let { data ->
            if(!data.screen.isNullOrEmpty() && !data.screen.isNullOrBlank()){
                view.openShare2(data.screen!!)
            }
        }
    }
}