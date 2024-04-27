package id.kasirvippro.android.feature.manageOrder.table.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/table/detail/TableDetailContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface TableDetailContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&\u00a8\u0006\n"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/table/detail/TableDetailContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "onClose", "", "status", "", "openEditPage", "setTable", "name", "", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void onClose(int status);
        
        public abstract void setTable(@org.jetbrains.annotations.Nullable()
        java.lang.String name);
        
        public abstract void openEditPage();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0004H&\u00a8\u0006\u000e"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/table/detail/TableDetailContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manageOrder/table/detail/TableDetailContract$View;", "getTableData", "Lid/kasirvippro/android/models/table/Table;", "getTitleName", "", "onDestroy", "", "onViewCreated", "intent", "Landroid/content/Intent;", "setTableData", "data", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manageOrder.table.detail.TableDetailContract.View> {
        
        public abstract void onDestroy();
        
        public abstract void onViewCreated(@org.jetbrains.annotations.NotNull()
        android.content.Intent intent);
        
        @org.jetbrains.annotations.NotNull()
        public abstract java.lang.String getTitleName();
        
        public abstract void setTableData(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.table.Table data);
        
        @org.jetbrains.annotations.Nullable()
        public abstract id.kasirvippro.android.models.table.Table getTableData();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&\u00a8\u0006\u0005"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/table/detail/TableDetailContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "onDestroy", "", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001\u00a8\u0006\u0002"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/table/detail/TableDetailContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
    }
}