package id.kasirvippro.android.feature.afiliate.detailAfiliate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel


class DetailAfiliatePresenter(val context: Context, val view: DetailAfiliateContract.View) : BasePresenter<DetailAfiliateContract.View>(),
    DetailAfiliateContract.Presenter, DetailAfiliateContract.InteractorOutput {

    private var interactor = DetailAfiliateInteractor(this)
    private var restModel = UserRestModel(context)
    private var user:User?=null

    override fun onViewCreated() {

        user = interactor.loadProfile(context)
        user?.let {
            view.setInfo(it)
        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onCopy() {
        user?.let { data ->
            if(!data.afiliasi.isNullOrEmpty() && !data.afiliasi.isNullOrBlank()){
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Referal", data.afiliasi)
                clipboard.setPrimaryClip(clip)
                view.showToast("The code has been copied to the clipboard")
            }
        }
    }

    override fun onShare() {
        user?.let { data ->
            if(!data.afiliasi.isNullOrEmpty() && !data.afiliasi.isNullOrBlank()){
                view.openShare(data.afiliasi!!)
            }
        }
    }
}