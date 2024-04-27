package id.kasirvippro.android.feature.manage.product.add;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0092\u0001\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u0013H\u0016J\u0018\u0010\"\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020$H\u0016J \u0010%\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0013H\u0016J\u0012\u0010\'\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010(\u001a\u00020\rH\u0016J\b\u0010)\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004\u00a8\u0006*"}, d2 = {"Lid/kasirvippro/android/feature/manage/product/add/AddProductInteractor;", "Lid/kasirvippro/android/feature/manage/product/add/AddProductContract$Interactor;", "output", "Lid/kasirvippro/android/feature/manage/product/add/AddProductPresenter;", "(Lid/kasirvippro/android/feature/manage/product/add/AddProductPresenter;)V", "appSession", "Lid/kasirvippro/android/utils/AppSession;", "disposable", "Lio/reactivex/disposables/CompositeDisposable;", "getOutput", "()Lid/kasirvippro/android/feature/manage/product/add/AddProductPresenter;", "setOutput", "callAddProductAPI", "", "context", "Landroid/content/Context;", "model", "Lid/kasirvippro/android/models/product/ProductRestModel;", "name", "", "unit", "kode", "idkategori", "jual", "beli", "stok", "minstok", "img", "desk", "online", "haveSTok", "grosir", "tax", "alertstock", "callGetCategoriesAPI", "restModel", "Lid/kasirvippro/android/models/category/CategoryRestModel;", "callSearchByBarcodeAPI", "search", "getUserPaket", "onDestroy", "onRestartDisposable", "app_debug"})
public final class AddProductInteractor implements id.kasirvippro.android.feature.manage.product.add.AddProductContract.Interactor {
    private io.reactivex.disposables.CompositeDisposable disposable;
    private final id.kasirvippro.android.utils.AppSession appSession = null;
    @org.jetbrains.annotations.NotNull()
    private id.kasirvippro.android.feature.manage.product.add.AddProductPresenter output;
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onRestartDisposable() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String getUserPaket(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @java.lang.Override()
    public void callAddProductAPI(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.product.ProductRestModel model, @org.jetbrains.annotations.NotNull()
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
    java.lang.String grosir, @org.jetbrains.annotations.NotNull()
    java.lang.String tax, @org.jetbrains.annotations.NotNull()
    java.lang.String alertstock) {
    }
    
    @java.lang.Override()
    public void callGetCategoriesAPI(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.category.CategoryRestModel restModel) {
    }
    
    @java.lang.Override()
    public void callSearchByBarcodeAPI(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.product.ProductRestModel restModel, @org.jetbrains.annotations.NotNull()
    java.lang.String search) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manage.product.add.AddProductPresenter getOutput() {
        return null;
    }
    
    public final void setOutput(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manage.product.add.AddProductPresenter p0) {
    }
    
    public AddProductInteractor(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manage.product.add.AddProductPresenter output) {
        super();
    }
}