package id.kasirvippro.android.feature.manageOrder.kitchen.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004\u00a8\u0006\u000f"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/kitchen/main/DataOrderInteractor;", "Lid/kasirvippro/android/feature/manageOrder/kitchen/main/DataOrderContract$Interactor;", "output", "Lid/kasirvippro/android/feature/manageOrder/kitchen/main/DataOrderContract$InteractorOutput;", "(Lid/kasirvippro/android/feature/manageOrder/kitchen/main/DataOrderContract$InteractorOutput;)V", "appSession", "Lid/kasirvippro/android/utils/AppSession;", "disposable", "Lio/reactivex/disposables/CompositeDisposable;", "getOutput", "()Lid/kasirvippro/android/feature/manageOrder/kitchen/main/DataOrderContract$InteractorOutput;", "setOutput", "onDestroy", "", "onRestartDisposable", "app_debug"})
public final class DataOrderInteractor implements id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderContract.Interactor {
    private io.reactivex.disposables.CompositeDisposable disposable;
    private final id.kasirvippro.android.utils.AppSession appSession = null;
    @org.jetbrains.annotations.Nullable()
    private id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderContract.InteractorOutput output;
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onRestartDisposable() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderContract.InteractorOutput getOutput() {
        return null;
    }
    
    public final void setOutput(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderContract.InteractorOutput p0) {
    }
    
    public DataOrderInteractor(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderContract.InteractorOutput output) {
        super();
    }
}