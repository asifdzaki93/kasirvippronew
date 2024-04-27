package id.kasirvippro.android.feature.manage.supplier.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0004\u00a8\u0006\r"}, d2 = {"Lid/kasirvippro/android/feature/manage/supplier/detail/DetailInteractor;", "Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$Interactor;", "output", "Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$InteractorOutput;", "(Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$InteractorOutput;)V", "disposable", "Lio/reactivex/disposables/CompositeDisposable;", "getOutput", "()Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$InteractorOutput;", "setOutput", "onDestroy", "", "onRestartDisposable", "app_debug"})
public final class DetailInteractor implements id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.Interactor {
    private io.reactivex.disposables.CompositeDisposable disposable;
    @org.jetbrains.annotations.Nullable()
    private id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.InteractorOutput output;
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onRestartDisposable() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.InteractorOutput getOutput() {
        return null;
    }
    
    public final void setOutput(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.InteractorOutput p0) {
    }
    
    public DetailInteractor(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.InteractorOutput output) {
        super();
    }
}