package id.kasirvippro.android.feature.manage.product.edit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manage/product/edit/EditProductContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface EditProductContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001c\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J(\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H&J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H&J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H&J\u0018\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H&J\b\u0010\u0018\u001a\u00020\u0003H&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010 \u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u000bH&J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u000bH&J\u0010\u0010#\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010$\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010%\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010&\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010\'\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010(\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u001a\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\b2\b\u0010+\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006,"}, d2 = {"Lid/kasirvippro/android/feature/manage/product/edit/EditProductContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "loadPhoto", "", "path", "", "onClose", "status", "", "onPremiumPage", "isPremium", "", "openCategories", "title", "list", "", "Lid/kasirvippro/android/models/DialogModel;", "selected", "openChooseUnit", "openImageChooser", "openPriceVariantPage", "id", "detail", "openRecipePage", "openScanPage", "openVariantPage", "setAlertstock", "value", "setBarcode", "setBuyPrice", "setCategoryName", "setDescription", "setGrosir", "setGrosirPrice", "setHaveStock", "setMinStock", "setProductName", "setSellPrice", "setStock", "setTax", "setUnitName", "showMessage", "code", "msg", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void showMessage(int code, @org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onClose(int status);
        
        public abstract void openScanPage();
        
        public abstract void openImageChooser();
        
        public abstract void loadPhoto(@org.jetbrains.annotations.NotNull()
        java.lang.String path);
        
        public abstract void openCategories(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.DialogModel> list, @org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.DialogModel selected);
        
        public abstract void setProductName(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setCategoryName(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setUnitName(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setStock(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setAlertstock(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setMinStock(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setSellPrice(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setBuyPrice(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setDescription(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setBarcode(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setGrosir(boolean value);
        
        public abstract void setHaveStock(boolean value);
        
        public abstract void setGrosirPrice(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void setTax(@org.jetbrains.annotations.NotNull()
        java.lang.String value);
        
        public abstract void onPremiumPage(boolean isPremium);
        
        public abstract void openVariantPage(@org.jetbrains.annotations.NotNull()
        java.lang.String id);
        
        public abstract void openPriceVariantPage(@org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String detail);
        
        public abstract void openRecipePage(@org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String detail);
        
        public abstract void openChooseUnit();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J`\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H&J\b\u0010\u0011\u001a\u00020\u0004H&J\b\u0010\u0012\u001a\u00020\u0004H&J\b\u0010\u0013\u001a\u00020\u0004H&J\b\u0010\u0014\u001a\u00020\u0004H&J\b\u0010\u0015\u001a\u00020\u0004H&J\b\u0010\u0016\u001a\u00020\u0004H&J\b\u0010\u0017\u001a\u00020\u0004H&J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001aH&J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0006H&J\u0010\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001fH&J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001fH&J\u0012\u0010!\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010\u0006H&J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%H&\u00a8\u0006&"}, d2 = {"Lid/kasirvippro/android/feature/manage/product/edit/EditProductContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manage/product/edit/EditProductContract$View;", "onCheck", "", "name", "", "unit", "buy", "sell", "stok", "minstok", "desc", "barcode", "grosir", "tax", "alertstock", "onCheckCategory", "onCheckPhoto", "onCheckScan", "onDestroy", "onPriceVariant", "onRecipe", "onVariant", "onViewCreated", "intent", "Landroid/content/Intent;", "searchByBarcode", "search", "setGrosir", "value", "", "setHaveStock", "setImagePhotoPath", "path", "setSelectedCategory", "data", "Lid/kasirvippro/android/models/DialogModel;", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manage.product.edit.EditProductContract.View> {
        
        public abstract void onViewCreated(@org.jetbrains.annotations.NotNull()
        android.content.Intent intent);
        
        public abstract void onDestroy();
        
        public abstract void onCheckScan();
        
        public abstract void onCheckPhoto();
        
        public abstract void setImagePhotoPath(@org.jetbrains.annotations.Nullable()
        java.lang.String path);
        
        public abstract void onCheckCategory();
        
        public abstract void onCheck(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String unit, @org.jetbrains.annotations.NotNull()
        java.lang.String buy, @org.jetbrains.annotations.NotNull()
        java.lang.String sell, @org.jetbrains.annotations.NotNull()
        java.lang.String stok, @org.jetbrains.annotations.NotNull()
        java.lang.String minstok, @org.jetbrains.annotations.NotNull()
        java.lang.String desc, @org.jetbrains.annotations.NotNull()
        java.lang.String barcode, @org.jetbrains.annotations.NotNull()
        java.lang.String grosir, @org.jetbrains.annotations.NotNull()
        java.lang.String tax, @org.jetbrains.annotations.NotNull()
        java.lang.String alertstock);
        
        public abstract void setSelectedCategory(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.DialogModel data);
        
        public abstract void searchByBarcode(@org.jetbrains.annotations.NotNull()
        java.lang.String search);
        
        public abstract void setHaveStock(int value);
        
        public abstract void setGrosir(int value);
        
        public abstract void onVariant();
        
        public abstract void onPriceVariant();
        
        public abstract void onRecipe();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u009a\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH&J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH&J \u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\tH&J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u001f\u001a\u00020\u0003H&J\b\u0010 \u001a\u00020\u0003H&\u00a8\u0006!"}, d2 = {"Lid/kasirvippro/android/feature/manage/product/edit/EditProductContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callEditProductAPI", "", "context", "Landroid/content/Context;", "model", "Lid/kasirvippro/android/models/product/ProductRestModel;", "id", "", "name", "unit", "kode", "idkategori", "jual", "beli", "stok", "minstok", "img", "desk", "online", "haveSTok", "hargagrosir", "tax", "alertstock", "callGetCategoriesAPI", "restModel", "Lid/kasirvippro/android/models/category/CategoryRestModel;", "callSearchByBarcodeAPI", "search", "getUserPaket", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getUserPaket(@org.jetbrains.annotations.NotNull()
        android.content.Context context);
        
        public abstract void callGetCategoriesAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.category.CategoryRestModel restModel);
        
        public abstract void callSearchByBarcodeAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.ProductRestModel restModel, @org.jetbrains.annotations.NotNull()
        java.lang.String search);
        
        public abstract void callEditProductAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.ProductRestModel model, @org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String unit, @org.jetbrains.annotations.NotNull()
        java.lang.String kode, @org.jetbrains.annotations.NotNull()
        java.lang.String idkategori, @org.jetbrains.annotations.NotNull()
        java.lang.String jual, @org.jetbrains.annotations.NotNull()
        java.lang.String beli, @org.jetbrains.annotations.NotNull()
        java.lang.String stok, @org.jetbrains.annotations.NotNull()
        java.lang.String minstok, @org.jetbrains.annotations.Nullable()
        java.lang.String img, @org.jetbrains.annotations.NotNull()
        java.lang.String desk, @org.jetbrains.annotations.NotNull()
        java.lang.String online, @org.jetbrains.annotations.NotNull()
        java.lang.String haveSTok, @org.jetbrains.annotations.NotNull()
        java.lang.String hargagrosir, @org.jetbrains.annotations.NotNull()
        java.lang.String tax, @org.jetbrains.annotations.NotNull()
        java.lang.String alertstock);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&J\u0012\u0010\r\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0016\u0010\u000e\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000bH&\u00a8\u0006\u0010"}, d2 = {"Lid/kasirvippro/android/feature/manage/product/edit/EditProductContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onFailedByBarcode", "onSuccessByBarcode", "list", "", "Lid/kasirvippro/android/models/product/Product;", "onSuccessEditProduct", "onSuccessGetCategories", "Lid/kasirvippro/android/models/category/Category;", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessEditProduct(@org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onSuccessGetCategories(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.category.Category> list);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
        
        public abstract void onSuccessByBarcode(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.product.Product> list);
        
        public abstract void onFailedByBarcode(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}