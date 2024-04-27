package id.kasirvippro.android.feature.manageStock.stockOpname.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface StockOpnameListContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&\u00a8\u0006\u0011"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "openEditPage", "", "data", "Lid/kasirvippro/android/models/product/Product;", "openScanPage", "reloadData", "setProducts", "list", "", "showErrorMessage", "code", "", "msg", "", "showSuccessMessage", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void setProducts(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.product.Product> list);
        
        public abstract void showErrorMessage(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
        
        public abstract void showSuccessMessage(@org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void reloadData();
        
        public abstract void openEditPage(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.Product data);
        
        public abstract void openScanPage();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0004H&J\b\u0010\u0007\u001a\u00020\u0004H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$View;", "loadProducts", "", "onCheckScan", "onDestroy", "onViewCreated", "searchByBarcode", "search", "", "searchProduct", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manageStock.stockOpname.list.StockOpnameListContract.View> {
        
        public abstract void onViewCreated();
        
        public abstract void onDestroy();
        
        public abstract void loadProducts();
        
        public abstract void searchProduct(@org.jetbrains.annotations.NotNull()
        java.lang.String search);
        
        public abstract void searchByBarcode(@org.jetbrains.annotations.NotNull()
        java.lang.String search);
        
        public abstract void onCheckScan();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&\u00a8\u0006\u000e"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callGetProductsAPI", "", "context", "Landroid/content/Context;", "restModel", "Lid/kasirvippro/android/models/product/ProductRestModel;", "callSearchByBarcodeAPI", "search", "", "callSearchProductAPI", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        public abstract void callGetProductsAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.ProductRestModel restModel);
        
        public abstract void callSearchProductAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.ProductRestModel restModel, @org.jetbrains.annotations.NotNull()
        java.lang.String search);
        
        public abstract void callSearchByBarcodeAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.ProductRestModel restModel, @org.jetbrains.annotations.NotNull()
        java.lang.String search);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&\u00a8\u0006\r"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onSuccessByBarcode", "list", "", "Lid/kasirvippro/android/models/product/Product;", "onSuccessGetProducts", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessGetProducts(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.product.Product> list);
        
        public abstract void onSuccessByBarcode(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.product.Product> list);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}