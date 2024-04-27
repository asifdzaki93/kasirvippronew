package id.kasirvippro.android.feature.manage.recipe.add

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.recipe.RecipeRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class AddRecipeInteractor(var output: AddRecipePresenter) : AddRecipeContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun getUserPaket(context: Context): String? {
        return appSession.getPackage(context)
    }

    override fun callAddProductAPI(context: Context, model: RecipeRestModel,stock:String,id_raw_material:String,id_product:String) {
        val key = appSession.getToken(context)
        disposable.add(model.add(key!!,stock,id_raw_material, id_product).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output.onSuccessAddProduct(response.message)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

}