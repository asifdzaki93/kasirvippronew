package id.kasirvippro.android.feature.manageStock.stockRawMaterial.edit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004\u00a8\u0006\u0018"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/stockRawMaterial/edit/EditStockRawMaterialInteractor;", "Lid/kasirvippro/android/feature/manageStock/stockRawMaterial/edit/EditStockRawMaterialContract$Interactor;", "output", "Lid/kasirvippro/android/feature/manageStock/stockRawMaterial/edit/EditStockRawMaterialContract$InteractorOutput;", "(Lid/kasirvippro/android/feature/manageStock/stockRawMaterial/edit/EditStockRawMaterialContract$InteractorOutput;)V", "appSession", "Lid/kasirvippro/android/utils/AppSession;", "disposable", "Lio/reactivex/disposables/CompositeDisposable;", "getOutput", "()Lid/kasirvippro/android/feature/manageStock/stockRawMaterial/edit/EditStockRawMaterialContract$InteractorOutput;", "setOutput", "callEditProductAPI", "", "context", "Landroid/content/Context;", "model", "Lid/kasirvippro/android/models/rawMaterial/RawMaterialRestModel;", "id", "", "stok", "getUserPaket", "onDestroy", "onRestartDisposable", "app_debug"})
public final class EditStockRawMaterialInteractor implements id.kasirvippro.android.feature.manageStock.stockRawMaterial.edit.EditStockRawMaterialContract.Interactor {
    private io.reactivex.disposables.CompositeDisposable disposable;
    private final id.kasirvippro.android.utils.AppSession appSession = null;
    @org.jetbrains.annotations.Nullable()
    private id.kasirvippro.android.feature.manageStock.stockRawMaterial.edit.EditStockRawMaterialContract.InteractorOutput output;
    
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
    public void callEditProductAPI(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel model, @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String stok) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final id.kasirvippro.android.feature.manageStock.stockRawMaterial.edit.EditStockRawMaterialContract.InteractorOutput getOutput() {
        return null;
    }
    
    public final void setOutput(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.feature.manageStock.stockRawMaterial.edit.EditStockRawMaterialContract.InteractorOutput p0) {
    }
    
    public EditStockRawMaterialInteractor(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.feature.manageStock.stockRawMaterial.edit.EditStockRawMaterialContract.InteractorOutput output) {
        super();
    }
}