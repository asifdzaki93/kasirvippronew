package id.kasirvippro.android.feature.manage.supplier.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ\n\u0010\u0013\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\fH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001c"}, d2 = {"Lid/kasirvippro/android/feature/manage/supplier/detail/DetailPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$View;", "Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$Presenter;", "Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$View;)V", "getContext", "()Landroid/content/Context;", "data", "Lid/kasirvippro/android/models/supplier/Supplier;", "interactor", "Lid/kasirvippro/android/feature/manage/supplier/detail/DetailInteractor;", "title", "", "getView", "()Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$View;", "getSupplierData", "getTitleName", "onDestroy", "", "onViewCreated", "intent", "Landroid/content/Intent;", "setSupplierData", "dt", "app_debug"})
public final class DetailPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.View> implements id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.Presenter, id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.InteractorOutput {
    private id.kasirvippro.android.feature.manage.supplier.detail.DetailInteractor interactor;
    private java.lang.String title = "";
    private id.kasirvippro.android.models.supplier.Supplier data;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getTitleName() {
        return null;
    }
    
    @java.lang.Override()
    public void setSupplierData(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.supplier.Supplier dt) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public id.kasirvippro.android.models.supplier.Supplier getSupplierData() {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.View getView() {
        return null;
    }
    
    public DetailPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.View view) {
        super();
    }
}