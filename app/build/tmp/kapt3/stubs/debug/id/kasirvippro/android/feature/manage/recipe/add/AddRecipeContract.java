package id.kasirvippro.android.feature.manage.recipe.add;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/add/AddRecipeContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface AddRecipeContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0006H&J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0006H&J\u0012\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&J\u001a\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H&\u00a8\u0006\u0017"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/add/AddRecipeContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "getDetail", "", "getIdProduct", "onClose", "", "status", "", "onPremiumPage", "isPremium", "", "openChooseRawmaterial", "openEditPage", "product", "Lid/kasirvippro/android/models/recipe/Recipe;", "setProduct", "setRawMaterialName", "data", "Lid/kasirvippro/android/models/rawMaterial/RawMaterial;", "showMessage", "code", "msg", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void showMessage(int code, @org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onClose(int status);
        
        public abstract void openEditPage(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.recipe.Recipe product);
        
        public abstract void onPremiumPage(boolean isPremium);
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getIdProduct();
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getDetail();
        
        public abstract void setProduct();
        
        public abstract void openChooseRawmaterial();
        
        public abstract void setRawMaterialName(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.rawMaterial.RawMaterial data);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0004H&J\b\u0010\b\u001a\u00020\u0004H&J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&\u00a8\u0006\f"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/add/AddRecipeContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manage/recipe/add/AddRecipeContract$View;", "onCheck", "", "stock", "", "onDestroy", "onViewCreated", "updateRawMaterial", "data", "Lid/kasirvippro/android/models/rawMaterial/RawMaterial;", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manage.recipe.add.AddRecipeContract.View> {
        
        public abstract void onViewCreated();
        
        public abstract void onDestroy();
        
        public abstract void onCheck(@org.jetbrains.annotations.NotNull()
        java.lang.String stock);
        
        public abstract void updateRawMaterial(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.rawMaterial.RawMaterial data);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH&J\u0012\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&\u00a8\u0006\u000f"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/add/AddRecipeContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callAddProductAPI", "", "context", "Landroid/content/Context;", "model", "Lid/kasirvippro/android/models/recipe/RecipeRestModel;", "stock", "", "id_raw_material", "id_product", "getUserPaket", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getUserPaket(@org.jetbrains.annotations.NotNull()
        android.content.Context context);
        
        public abstract void callAddProductAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.recipe.RecipeRestModel model, @org.jetbrains.annotations.NotNull()
        java.lang.String stock, @org.jetbrains.annotations.NotNull()
        java.lang.String id_raw_material, @org.jetbrains.annotations.NotNull()
        java.lang.String id_product);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&\u00a8\u0006\t"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/add/AddRecipeContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onSuccessAddProduct", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessAddProduct(@org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}