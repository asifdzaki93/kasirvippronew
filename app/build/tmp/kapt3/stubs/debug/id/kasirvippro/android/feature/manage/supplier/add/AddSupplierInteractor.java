package id.kasirvippro.android.feature.manage.supplier.add;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J8\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\b\u0010\u0018\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004\u00a8\u0006\u0019"}, d2 = {"Lid/kasirvippro/android/feature/manage/supplier/add/AddSupplierInteractor;", "Lid/kasirvippro/android/feature/manage/supplier/add/AddSupplierContract$Interactor;", "output", "Lid/kasirvippro/android/feature/manage/supplier/add/AddSupplierContract$InteractorOutput;", "(Lid/kasirvippro/android/feature/manage/supplier/add/AddSupplierContract$InteractorOutput;)V", "appSession", "Lid/kasirvippro/android/utils/AppSession;", "disposable", "Lio/reactivex/disposables/CompositeDisposable;", "getOutput", "()Lid/kasirvippro/android/feature/manage/supplier/add/AddSupplierContract$InteractorOutput;", "setOutput", "callAddSupplierAPI", "", "context", "Landroid/content/Context;", "model", "Lid/kasirvippro/android/models/supplier/SupplierRestModel;", "name", "", "email", "telpon", "alamat", "onDestroy", "onRestartDisposable", "app_debug"})
public final class AddSupplierInteractor implements id.kasirvippro.android.feature.manage.supplier.add.AddSupplierContract.Interactor {
    private io.reactivex.disposables.CompositeDisposable disposable;
    private final id.kasirvippro.android.utils.AppSession appSession = null;
    @org.jetbrains.annotations.Nullable()
    private id.kasirvippro.android.feature.manage.supplier.add.AddSupplierContract.InteractorOutput output;
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onRestartDisposable() {
    }
    
    @java.lang.Override()
    public void callAddSupplierAPI(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.supplier.SupplierRestModel model, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String telpon, @org.jetbrains.annotations.NotNull()
    java.lang.String alamat) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final id.kasirvippro.android.feature.manage.supplier.add.AddSupplierContract.InteractorOutput getOutput() {
        return null;
    }
    
    public final void setOutput(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.feature.manage.supplier.add.AddSupplierContract.InteractorOutput p0) {
    }
    
    public AddSupplierInteractor(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.feature.manage.supplier.add.AddSupplierContract.InteractorOutput output) {
        super();
    }
}