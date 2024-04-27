package id.kasirvippro.android.feature.manage.packages.add;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manage/packages/add/AddPackagesContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface AddPackagesContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0012H&J\u0012\u0010\u0015\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0016H&J\b\u0010\u0017\u001a\u00020\u0003H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0012H&J\u001a\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0012H&J\b\u0010\u001d\u001a\u00020\u0003H&J\u0018\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006 "}, d2 = {"Lid/kasirvippro/android/feature/manage/packages/add/AddPackagesContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "addCart", "", "data", "Lid/kasirvippro/android/models/cart/Cart;", "deleteCart", "position", "", "openChooseProduct", "openChooseStore", "openCountDialog", "selected", "pos", "openHistoryKulakan", "openScanPage", "openSuccessPage", "id", "", "setCartText", "nominal", "setStoreName", "Lid/kasirvippro/android/models/store/Store;", "showContentView", "showErrorView", "err", "showMessage", "code", "msg", "showTunaiView", "updateCart", "cart", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void showMessage(int code, @org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void openScanPage();
        
        public abstract void openChooseProduct();
        
        public abstract void showContentView();
        
        public abstract void showErrorView(@org.jetbrains.annotations.NotNull()
        java.lang.String err);
        
        public abstract void setCartText(@org.jetbrains.annotations.NotNull()
        java.lang.String nominal);
        
        public abstract void addCart(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.cart.Cart data);
        
        public abstract void updateCart(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.cart.Cart cart, int position);
        
        public abstract void deleteCart(int position);
        
        public abstract void showTunaiView();
        
        public abstract void setStoreName(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.store.Store data);
        
        public abstract void openChooseStore();
        
        public abstract void openSuccessPage(@org.jetbrains.annotations.NotNull()
        java.lang.String id);
        
        public abstract void openCountDialog(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.cart.Cart selected, int pos);
        
        public abstract void openHistoryKulakan();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\f\u001a\u00020\u0004H&J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0012\u001a\u00020\u0010H&J\u0018\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0014\u001a\u00020\u0004H&J\b\u0010\u0015\u001a\u00020\u0004H&J\b\u0010\u0016\u001a\u00020\u0004H&J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\tH&J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0012\u0010\u001a\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u001bH&\u00a8\u0006\u001c"}, d2 = {"Lid/kasirvippro/android/feature/manage/packages/add/AddPackagesContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manage/packages/add/AddPackagesContract$View;", "addCart", "", "data", "Lid/kasirvippro/android/models/product/Product;", "checkAdd", "id", "", "price", "checkCart", "countCart", "decreaseCart", "Lid/kasirvippro/android/models/cart/Cart;", "position", "", "deleteCart", "getCartsSize", "increaseCart", "onCheckScan", "onDestroy", "onViewCreated", "searchByBarcode", "search", "updateCart", "updateStore", "Lid/kasirvippro/android/models/store/Store;", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manage.packages.add.AddPackagesContract.View> {
        
        public abstract void onViewCreated();
        
        public abstract void onDestroy();
        
        public abstract void onCheckScan();
        
        public abstract void checkCart(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.Product data);
        
        public abstract void addCart(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.Product data);
        
        public abstract void increaseCart(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.cart.Cart data, int position);
        
        public abstract void decreaseCart(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.cart.Cart data, int position);
        
        public abstract void deleteCart(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.cart.Cart data, int position);
        
        public abstract void updateCart(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.cart.Cart data, int position);
        
        public abstract void countCart();
        
        public abstract void searchByBarcode(@org.jetbrains.annotations.NotNull()
        java.lang.String search);
        
        public abstract void updateStore(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.store.Store data);
        
        public abstract void checkAdd(@org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String price);
        
        public abstract int getCartsSize();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&\u00a8\u0006\u0010"}, d2 = {"Lid/kasirvippro/android/feature/manage/packages/add/AddPackagesContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callOrderAPI", "", "context", "Landroid/content/Context;", "restModel", "Lid/kasirvippro/android/models/transaction/TransactionRestModel;", "req", "Lid/kasirvippro/android/models/transaction/RequestPackages;", "callSearchByBarcodeAPI", "Lid/kasirvippro/android/models/product/ProductRestModel;", "search", "", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        public abstract void callSearchByBarcodeAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.product.ProductRestModel restModel, @org.jetbrains.annotations.NotNull()
        java.lang.String search);
        
        public abstract void callOrderAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.transaction.TransactionRestModel restModel, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.transaction.RequestPackages req);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&\u00a8\u0006\u0010"}, d2 = {"Lid/kasirvippro/android/feature/manage/packages/add/AddPackagesContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onFailedBarcode", "onSuccessByBarcode", "list", "", "Lid/kasirvippro/android/models/product/Product;", "onSuccessOrder", "message", "Lid/kasirvippro/android/models/transaction/Order;", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessByBarcode(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.product.Product> list);
        
        public abstract void onSuccessOrder(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.transaction.Order message);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
        
        public abstract void onFailedBarcode(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}