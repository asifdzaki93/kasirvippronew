package id.kasirvippro.android.feature.manage.productVariant.add;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface AddProductVariantContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0005H&J\b\u0010\u0011\u001a\u00020\u0005H&J\u001a\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H&\u00a8\u0006\u0015"}, d2 = {"Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "getIdProduct", "", "loadPhoto", "", "path", "onClose", "status", "", "onPremiumPage", "isPremium", "", "openEditPage", "product", "Lid/kasirvippro/android/models/product/Product;", "openImageChooser", "openScanPage", "showMessage", "code", "msg", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void showMessage(int code, @org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onClose(int status);
        
        public abstract void openScanPage();
        
        public abstract void openImageChooser();
        
        public abstract void loadPhoto(@org.jetbrains.annotations.NotNull()
        java.lang.String path);
        
        public abstract void openEditPage(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.Product product);
        
        public abstract void onPremiumPage(boolean isPremium);
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getIdProduct();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001JX\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H&J\b\u0010\u0010\u001a\u00020\u0004H&J\b\u0010\u0011\u001a\u00020\u0004H&J\b\u0010\u0012\u001a\u00020\u0004H&J\b\u0010\u0013\u001a\u00020\u0004H&J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0006H&J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0012\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006H&\u00a8\u0006\u001c"}, d2 = {"Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$View;", "onCheck", "", "name", "", "buy", "sell", "stok", "minstok", "desc", "barcode", "grosir", "tax", "alertstock", "onCheckPhoto", "onCheckScan", "onDestroy", "onViewCreated", "searchByBarcode", "search", "setGrosir", "value", "", "setHaveStock", "setImagePhotoPath", "path", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manage.productVariant.add.AddProductVariantContract.View> {
        
        public abstract void onViewCreated();
        
        public abstract void onDestroy();
        
        public abstract void onCheckScan();
        
        public abstract void onCheckPhoto();
        
        public abstract void setImagePhotoPath(@org.jetbrains.annotations.Nullable()
        java.lang.String path);
        
        public abstract void onCheck(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String buy, @org.jetbrains.annotations.NotNull()
        java.lang.String sell, @org.jetbrains.annotations.NotNull()
        java.lang.String stok, @org.jetbrains.annotations.NotNull()
        java.lang.String minstok, @org.jetbrains.annotations.NotNull()
        java.lang.String desc, @org.jetbrains.annotations.NotNull()
        java.lang.String barcode, @org.jetbrains.annotations.NotNull()
        java.lang.String grosir, @org.jetbrains.annotations.NotNull()
        java.lang.String tax, @org.jetbrains.annotations.NotNull()
        java.lang.String alertstock);
        
        public abstract void searchByBarcode(@org.jetbrains.annotations.NotNull()
        java.lang.String search);
        
        public abstract void setHaveStock(int value);
        
        public abstract void setGrosir(int value);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\bf\u0018\u00002\u00020\u0001J\u008a\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH&J \u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\tH&J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u001b\u001a\u00020\u0003H&J\b\u0010\u001c\u001a\u00020\u0003H&\u00a8\u0006\u001d"}, d2 = {"Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callAddProductAPI", "", "context", "Landroid/content/Context;", "model", "Lid/kasirvippro/android/models/product/ProductRestModel;", "name", "", "kode", "idkategori", "jual", "beli", "stok", "minstok", "img", "desk", "online", "haveSTok", "hargagrosir", "tax", "alertstock", "callSearchByBarcodeAPI", "restModel", "search", "getUserPaket", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getUserPaket(@org.jetbrains.annotations.NotNull()
        android.content.Context context);
        
        public abstract void callSearchByBarcodeAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.ProductRestModel restModel, @org.jetbrains.annotations.NotNull()
        java.lang.String search);
        
        public abstract void callAddProductAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.ProductRestModel model, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&\u00a8\u0006\u000e"}, d2 = {"Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onFailedByBarcode", "onSuccessAddProduct", "onSuccessByBarcode", "list", "", "Lid/kasirvippro/android/models/product/Product;", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessAddProduct(@org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
        
        public abstract void onSuccessByBarcode(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.product.Product> list);
        
        public abstract void onFailedByBarcode(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}