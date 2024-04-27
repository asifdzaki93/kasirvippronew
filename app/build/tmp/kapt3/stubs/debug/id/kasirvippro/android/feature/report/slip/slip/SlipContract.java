package id.kasirvippro.android.feature.report.slip.slip;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/slip/SlipContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface SlipContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J@\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\nH&J \u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH&J&\u0010\u0014\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\nH&J \u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\nH&J\u001a\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\nH&\u00a8\u0006\u001f"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "getParentLayout", "Landroidx/core/widget/NestedScrollView;", "onPremiumPage", "", "isPremium", "", "setDetail", "penjualan", "", "gaji", "komisi", "tunjangan", "potongan", "kehadiran", "setInfo", "name", "job", "date", "setStore", "address", "phone", "setTotal", "pendapatan", "pengurang", "total", "showMessage", "code", "", "msg", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void setStore(@org.jetbrains.annotations.Nullable()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.lang.String address, @org.jetbrains.annotations.Nullable()
        java.lang.String phone);
        
        public abstract void setInfo(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String job, @org.jetbrains.annotations.NotNull()
        java.lang.String date);
        
        public abstract void setDetail(@org.jetbrains.annotations.NotNull()
        java.lang.String penjualan, @org.jetbrains.annotations.NotNull()
        java.lang.String gaji, @org.jetbrains.annotations.Nullable()
        java.lang.String komisi, @org.jetbrains.annotations.Nullable()
        java.lang.String tunjangan, @org.jetbrains.annotations.Nullable()
        java.lang.String potongan, @org.jetbrains.annotations.Nullable()
        java.lang.String kehadiran);
        
        public abstract void setTotal(@org.jetbrains.annotations.NotNull()
        java.lang.String pendapatan, @org.jetbrains.annotations.NotNull()
        java.lang.String pengurang, @org.jetbrains.annotations.NotNull()
        java.lang.String total);
        
        @org.jetbrains.annotations.NotNull()
        public abstract androidx.core.widget.NestedScrollView getParentLayout();
        
        public abstract void showMessage(int code, @org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onPremiumPage(boolean isPremium);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0004H&J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0004H&\u00a8\u0006\u000b"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$View;", "onCheckDownload", "", "onCheckShare", "onDestroy", "onViewCreated", "intent", "Landroid/content/Intent;", "setData", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.report.slip.slip.SlipContract.View> {
        
        public abstract void onViewCreated(@org.jetbrains.annotations.NotNull()
        android.content.Intent intent);
        
        public abstract void onDestroy();
        
        public abstract void setData();
        
        public abstract void onCheckShare();
        
        public abstract void onCheckDownload();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&\u00a8\u0006\t"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "getUserPaket", "", "context", "Landroid/content/Context;", "onDestroy", "", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.String getUserPaket(@org.jetbrains.annotations.NotNull()
        android.content.Context context);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}