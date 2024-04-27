package id.kasirvippro.android.feature.manage.productVariant.add;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJX\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u00152\u0006\u0010\'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\u0015H\u0016J\b\u0010)\u001a\u00020\u001eH\u0016J\b\u0010*\u001a\u00020\u001eH\u0016J\b\u0010+\u001a\u00020\u001eH\u0016J\u0018\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u0015H\u0016J\u0018\u0010/\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u0015H\u0016J\u0012\u00100\u001a\u00020\u001e2\b\u0010.\u001a\u0004\u0018\u00010\u0015H\u0016J\u0016\u00101\u001a\u00020\u001e2\f\u00102\u001a\b\u0012\u0004\u0012\u00020403H\u0016J\b\u00105\u001a\u00020\u001eH\u0016J\u0010\u00106\u001a\u00020\u001e2\u0006\u00107\u001a\u00020\u0015H\u0016J\u0010\u00108\u001a\u00020\u001e2\u0006\u00109\u001a\u00020\u000eH\u0016J\u0010\u0010:\u001a\u00020\u001e2\u0006\u00109\u001a\u00020\u000eH\u0016J\u0012\u0010;\u001a\u00020\u001e2\b\u0010<\u001a\u0004\u0018\u00010\u0015H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006="}, d2 = {"Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$View;", "Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$Presenter;", "Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$View;)V", "cameraPermission", "Lid/kasirvippro/android/callback/PermissionCallback;", "getContext", "()Landroid/content/Context;", "grosir", "", "haveStock", "interactor", "Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantInteractor;", "permissionUtil", "Lid/kasirvippro/android/utils/PermissionUtil;", "photoPath", "", "photoPermission", "premium", "", "restModel", "Lid/kasirvippro/android/models/product/ProductRestModel;", "getView", "()Lid/kasirvippro/android/feature/manage/productVariant/add/AddProductVariantContract$View;", "onCheck", "", "name", "buy", "sell", "stok", "minstok", "desc", "barcode", "hargagrosir", "tax", "alertstock", "onCheckPhoto", "onCheckScan", "onDestroy", "onFailedAPI", "code", "msg", "onFailedByBarcode", "onSuccessAddProduct", "onSuccessByBarcode", "list", "", "Lid/kasirvippro/android/models/product/Product;", "onViewCreated", "searchByBarcode", "search", "setGrosir", "value", "setHaveStock", "setImagePhotoPath", "path", "app_debug"})
public final class AddProductVariantPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.manage.productVariant.add.AddProductVariantContract.View> implements id.kasirvippro.android.feature.manage.productVariant.add.AddProductVariantContract.Presenter, id.kasirvippro.android.feature.manage.productVariant.add.AddProductVariantContract.InteractorOutput {
    private id.kasirvippro.android.feature.manage.productVariant.add.AddProductVariantInteractor interactor;
    private id.kasirvippro.android.models.product.ProductRestModel restModel;
    private id.kasirvippro.android.utils.PermissionUtil permissionUtil;
    private id.kasirvippro.android.callback.PermissionCallback cameraPermission;
    private id.kasirvippro.android.callback.PermissionCallback photoPermission;
    private java.lang.String photoPath;
    private int haveStock = 0;
    private int grosir = 0;
    private boolean premium = false;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.productVariant.add.AddProductVariantContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onCheckScan() {
    }
    
    @java.lang.Override()
    public void onCheckPhoto() {
    }
    
    @java.lang.Override()
    public void setImagePhotoPath(@org.jetbrains.annotations.Nullable()
    java.lang.String path) {
    }
    
    @java.lang.Override()
    public void onCheck(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String buy, @org.jetbrains.annotations.NotNull()
    java.lang.String sell, @org.jetbrains.annotations.NotNull()
    java.lang.String stok, @org.jetbrains.annotations.NotNull()
    java.lang.String minstok, @org.jetbrains.annotations.NotNull()
    java.lang.String desc, @org.jetbrains.annotations.NotNull()
    java.lang.String barcode, @org.jetbrains.annotations.NotNull()
    java.lang.String hargagrosir, @org.jetbrains.annotations.NotNull()
    java.lang.String tax, @org.jetbrains.annotations.NotNull()
    java.lang.String alertstock) {
    }
    
    @java.lang.Override()
    public void onSuccessAddProduct(@org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void searchByBarcode(@org.jetbrains.annotations.NotNull()
    java.lang.String search) {
    }
    
    @java.lang.Override()
    public void onSuccessByBarcode(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.product.Product> list) {
    }
    
    @java.lang.Override()
    public void onFailedByBarcode(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void setHaveStock(int value) {
    }
    
    @java.lang.Override()
    public void setGrosir(int value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manage.productVariant.add.AddProductVariantContract.View getView() {
        return null;
    }
    
    public AddProductVariantPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manage.productVariant.add.AddProductVariantContract.View view) {
        super();
    }
}