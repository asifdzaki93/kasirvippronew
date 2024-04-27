package id.kasirvippro.android.feature.manage.category.list

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.CategorySQLDelete
import id.kasirvippro.android.utils.AppSession

class CategoryListPresenter(val context: Context, val view: CategoryListContract.View) : BasePresenter<CategoryListContract.View>(),
    CategoryListContract.Presenter, CategoryListContract.InteractorOutput {

    private var interactor: CategoryListInteractor = CategoryListInteractor(this)
    private var categoryRestModel = CategoryRestModel(context)

    override fun onViewCreated() {
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val dataManager = DataManager (context)
        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {

                    loadCategories()
                    dataManager.clearCategoryAll()
                    return
                }else{
                    var allcategory = dataManager.allCategory()

                    var categoryList = mutableListOf<Category>()
                    for (item in allcategory){
                        var category = Category();
                        category.set(
                            item.id_category,
                            item.name_category,
                            item.increment
                        )
                        categoryList.add(category)
                    }
                    setCategory(categoryList);
                }
            }else{
                var allcategory = dataManager.allCategory()

                var categoryList = mutableListOf<Category>()
                for (item in allcategory){
                    var category = Category();
                    category.set(
                        item.id_category,
                        item.name_category,
                        item.increment
                    )
                    categoryList.add(category)
                }
                setCategory(categoryList);
            }
        }else{
            var allcategory = dataManager.allCategory()

            var categoryList = mutableListOf<Category>()
            for (item in allcategory){
                var category = Category();
                category.set(
                    item.id_category,
                    item.name_category,
                    item.increment
                )
                categoryList.add(category)
            }
            setCategory(categoryList);
        }
    }

    override fun loadCategories() {
        interactor.callGetCategoriesAPI(context,categoryRestModel)
    }

    fun setCategory(list: List<Category>){
        view.setCategorys(list)
    }

    override fun deleteCategory(id: String,position:Int,increment:String) {
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context?.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val dataManager = DataManager (context)
        val appSession = AppSession()
        val key = appSession.getToken(context)

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    interactor.callDeleteCategoryAPI(context,categoryRestModel,id)
                }else{
                    val category = CategorySQLDelete(increment,key!!,id)
                    var result1 = dataManager.addCategoryDelete(category!!)
                    var result = dataManager.clearCategory(increment)

                    Toast.makeText(context,"Delete category Local", Toast.LENGTH_SHORT).show()
                    (context as CategoryListActivity).list2.removeAt(position)
                    (context as CategoryListActivity).setList()
                    (context as CategoryListActivity).hideLoading()
                }
            }else{
                val category = CategorySQLDelete(increment,key!!,id)
                var result1 = dataManager.addCategoryDelete(category)
                var result = dataManager.clearCategory(increment)

                Toast.makeText(context,"Delete category Local", Toast.LENGTH_SHORT).show()
                (context as CategoryListActivity).list2.removeAt(position)
                (context as CategoryListActivity).setList()
                (context as CategoryListActivity).hideLoading()
            }
        }else{
            val category = CategorySQLDelete(increment,key!!,id)
            var result1 = dataManager.addCategoryDelete(category!!)
            var result = dataManager.clearCategory(increment)

            Toast.makeText(context,"Delete category Local", Toast.LENGTH_SHORT).show()
            (context as CategoryListActivity).list2.removeAt(position)
            (context as CategoryListActivity).setList()
            (context as CategoryListActivity).hideLoading()
        }
    }

    override fun searchCategory(search: String) {
        interactor.onRestartDisposable()
        if(search.isNullOrEmpty() || search.isNullOrBlank()){
           interactor.callGetCategoriesAPI(context,categoryRestModel)
        }
        else{

            var connectivity : ConnectivityManager? = null
            var info : NetworkInfo? = null
            connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val dataManager = DataManager (context)
            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        interactor.callSearchCategoryAPI(context,categoryRestModel,search)
                    }else{
                        var searchcategory = dataManager.searchCategory(search)

                        var categoryList = mutableListOf<Category>()
                        for (item in searchcategory){
                            var category = Category();
                            category.set(
                                item.id_category,
                                item.name_category,
                                item.increment
                            )
                            categoryList.add(category)
                        }
                        setCategory(categoryList);
                    }
                }else{
                    var searchcategory = dataManager.searchCategory(search)

                    var categoryList = mutableListOf<Category>()
                    for (item in searchcategory){
                        var category = Category();
                        category.set(
                            item.id_category,
                            item.name_category,
                            item.increment
                        )
                        categoryList.add(category)
                    }
                    setCategory(categoryList);
                }
            }else{
                var searchcategory = dataManager.searchCategory(search)

                var categoryList = mutableListOf<Category>()
                for (item in searchcategory){
                    var category = Category();
                    category.set(
                        item.id_category,
                        item.name_category,
                        item.increment
                    )
                    categoryList.add(category)
                }
                setCategory(categoryList);
            }


        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetCategories(list: List<Category>) {
        view.setData(list)
    }

    override fun onSuccessDeleteCategory(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}