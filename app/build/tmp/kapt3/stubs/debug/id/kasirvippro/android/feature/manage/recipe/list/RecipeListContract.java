package id.kasirvippro.android.feature.manage.recipe.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface RecipeListContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0003H&J\u0016\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H&J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003H&J\u0012\u0010\u0015\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H&\u00a8\u0006\u0016"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "getDetail", "", "getIdProduct", "openAddPage", "", "id", "openEditPage", "data", "Lid/kasirvippro/android/models/recipe/Recipe;", "reloadData", "setProduct", "value", "setProducts", "list", "", "showErrorMessage", "code", "", "msg", "showSuccessMessage", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void setProducts(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.recipe.Recipe> list);
        
        public abstract void showErrorMessage(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
        
        public abstract void showSuccessMessage(@org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void reloadData();
        
        public abstract void openEditPage(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.recipe.Recipe data);
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getIdProduct();
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getDetail();
        
        public abstract void openAddPage(@org.jetbrains.annotations.NotNull()
        java.lang.String id);
        
        public abstract void setProduct(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0004H&J\b\u0010\n\u001a\u00020\u0004H&J\b\u0010\u000b\u001a\u00020\u0004H&J\b\u0010\f\u001a\u00020\u0004H&\u00a8\u0006\r"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListContract$View;", "deleteProduct", "", "id", "", "position", "", "loadProducts", "onAddPage", "onDestroy", "onViewCreated", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manage.recipe.list.RecipeListContract.View> {
        
        public abstract void onViewCreated();
        
        public abstract void onDestroy();
        
        public abstract void loadProducts();
        
        public abstract void deleteProduct(@org.jetbrains.annotations.NotNull()
        java.lang.String id, int position);
        
        public abstract void onAddPage();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\tH&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&\u00a8\u0006\u000e"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callDeleteProductAPI", "", "context", "Landroid/content/Context;", "restModel", "Lid/kasirvippro/android/models/recipe/RecipeRestModel;", "id", "", "callGetProductsAPI", "id_product", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        public abstract void callGetProductsAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.recipe.RecipeRestModel restModel, @org.jetbrains.annotations.NotNull()
        java.lang.String id_product);
        
        public abstract void callDeleteProductAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.recipe.RecipeRestModel restModel, @org.jetbrains.annotations.NotNull()
        java.lang.String id);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&\u00a8\u0006\r"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onSuccessDeleteProduct", "onSuccessGetProducts", "list", "", "Lid/kasirvippro/android/models/recipe/Recipe;", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessGetProducts(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.recipe.Recipe> list);
        
        public abstract void onSuccessDeleteProduct(@org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}