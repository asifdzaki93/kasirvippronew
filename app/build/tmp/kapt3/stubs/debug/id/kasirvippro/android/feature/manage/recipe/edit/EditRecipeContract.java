package id.kasirvippro.android.feature.manage.recipe.edit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/edit/EditRecipeContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface EditRecipeContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0003H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u001a\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u000eH&\u00a8\u0006\u0016"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/edit/EditRecipeContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "onClose", "", "status", "", "data", "Lid/kasirvippro/android/models/recipe/Recipe;", "onPremiumPage", "isPremium", "", "openChooseRawmaterial", "setProduct", "value", "", "setRawMaterial", "setRawMaterialName", "Lid/kasirvippro/android/models/rawMaterial/RawMaterial;", "setStock", "showMessage", "code", "msg", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void showMessage(int code, @org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onClose(int status, @org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.recipe.Recipe data);
        
        public abstract void setStock(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setRawMaterial(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setProduct(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void onPremiumPage(boolean isPremium);
        
        public abstract void openChooseRawmaterial();
        
        public abstract void setRawMaterialName(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.rawMaterial.RawMaterial data);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0004H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH&J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\rH&\u00a8\u0006\u000e"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/edit/EditRecipeContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manage/recipe/edit/EditRecipeContract$View;", "onCheck", "", "stock", "", "onDestroy", "onViewCreated", "intent", "Landroid/content/Intent;", "updateRawMaterial", "data", "Lid/kasirvippro/android/models/rawMaterial/RawMaterial;", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manage.recipe.edit.EditRecipeContract.View> {
        
        public abstract void onViewCreated(@org.jetbrains.annotations.NotNull()
        android.content.Intent intent);
        
        public abstract void onDestroy();
        
        public abstract void onCheck(@org.jetbrains.annotations.NotNull()
        java.lang.String stock);
        
        public abstract void updateRawMaterial(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.rawMaterial.RawMaterial data);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&\u00a8\u0006\u000e"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/edit/EditRecipeContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callEditProductAPI", "", "context", "Landroid/content/Context;", "model", "Lid/kasirvippro/android/models/recipe/RecipeRestModel;", "id", "", "stock", "getUserPaket", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getUserPaket(@org.jetbrains.annotations.NotNull()
        android.content.Context context);
        
        public abstract void callEditProductAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.recipe.RecipeRestModel model, @org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String stock);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&\u00a8\u0006\t"}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/edit/EditRecipeContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onSuccessEditProduct", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessEditProduct(@org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}