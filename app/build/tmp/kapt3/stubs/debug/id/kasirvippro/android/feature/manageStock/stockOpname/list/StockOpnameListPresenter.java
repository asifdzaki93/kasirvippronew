package id.kasirvippro.android.feature.manageStock.stockOpname.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u0018H\u0016J\u0018\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0016\u0010 \u001a\u00020\u00182\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0016J\u0016\u0010$\u001a\u00020\u00182\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0016J\b\u0010%\u001a\u00020\u0018H\u0016J\u0010\u0010&\u001a\u00020\u00182\u0006\u0010\'\u001a\u00020\u001fH\u0016J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010\'\u001a\u00020\u001fH\u0016J\u0014\u0010)\u001a\u00020\u00182\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006*"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$View;", "Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$Presenter;", "Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$View;)V", "cameraPermission", "Lid/kasirvippro/android/callback/PermissionCallback;", "getContext", "()Landroid/content/Context;", "interactor", "Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListInteractor;", "permissionUtil", "Lid/kasirvippro/android/utils/PermissionUtil;", "restModel", "Lid/kasirvippro/android/models/product/ProductRestModel;", "sort", "", "getView", "()Lid/kasirvippro/android/feature/manageStock/stockOpname/list/StockOpnameListContract$View;", "loadProducts", "", "onCheckScan", "onDestroy", "onFailedAPI", "code", "", "msg", "", "onSuccessByBarcode", "list", "", "Lid/kasirvippro/android/models/product/Product;", "onSuccessGetProducts", "onViewCreated", "searchByBarcode", "search", "searchProduct", "setProduct", "app_debug"})
public final class StockOpnameListPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.manageStock.stockOpname.list.StockOpnameListContract.View> implements id.kasirvippro.android.feature.manageStock.stockOpname.list.StockOpnameListContract.Presenter, id.kasirvippro.android.feature.manageStock.stockOpname.list.StockOpnameListContract.InteractorOutput {
    private id.kasirvippro.android.feature.manageStock.stockOpname.list.StockOpnameListInteractor interactor;
    private id.kasirvippro.android.models.product.ProductRestModel restModel;
    private id.kasirvippro.android.utils.PermissionUtil permissionUtil;
    private id.kasirvippro.android.callback.PermissionCallback cameraPermission;
    private boolean sort = false;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manageStock.stockOpname.list.StockOpnameListContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated() {
    }
    
    @java.lang.Override()
    public void onCheckScan() {
    }
    
    @java.lang.Override()
    public void loadProducts() {
    }
    
    @java.lang.Override()
    public void searchProduct(@org.jetbrains.annotations.NotNull()
    java.lang.String search) {
    }
    
    @java.lang.Override()
    public void searchByBarcode(@org.jetbrains.annotations.NotNull()
    java.lang.String search) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onSuccessGetProducts(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.product.Product> list) {
    }
    
    public final void setProduct(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.product.Product> list) {
    }
    
    @java.lang.Override()
    public void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void onSuccessByBarcode(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.product.Product> list) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manageStock.stockOpname.list.StockOpnameListContract.View getView() {
        return null;
    }
    
    public StockOpnameListPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manageStock.stockOpname.list.StockOpnameListContract.View view) {
        super();
    }
}