package id.kasirvippro.android.feature.manageStock.dataTransfer.transferOut;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface TransferOutContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\nH&J\b\u0010\u000b\u001a\u00020\u0003H&J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\bH&\u00a8\u0006\u0014"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "onFilterStatusSelected", "", "data", "Lid/kasirvippro/android/models/DialogModel;", "openDetail", "id", "", "openFilter", "Lid/kasirvippro/android/models/FilterDialogDate;", "reloadData", "setList", "list", "", "Lid/kasirvippro/android/models/transaction/Transfer;", "showErrorMessage", "code", "", "msg", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void reloadData();
        
        public abstract void setList(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.transaction.Transfer> list);
        
        public abstract void showErrorMessage(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
        
        public abstract void openDetail(@org.jetbrains.annotations.NotNull()
        java.lang.String id);
        
        public abstract void onFilterStatusSelected(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.DialogModel data);
        
        public abstract void openFilter(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.FilterDialogDate data);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00060\bj\b\u0012\u0004\u0012\u00020\u0006`\tH&J\b\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u000e\u001a\u00020\u000bH&J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u000bH&J\u0012\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004H&\u00a8\u0006\u0015"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutContract$View;", "getFilterDateSelected", "Lid/kasirvippro/android/models/FilterDialogDate;", "getFilterSelected", "Lid/kasirvippro/android/models/DialogModel;", "getFilters", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "loadTransaction", "", "onChangeStatus", "selected", "onDestroy", "onSearch", "id", "", "onViewCreated", "setFilterDateSelected", "data", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manageStock.dataTransfer.transferOut.TransferOutContract.View> {
        
        public abstract void onViewCreated();
        
        public abstract void onDestroy();
        
        public abstract void loadTransaction();
        
        public abstract void onSearch(@org.jetbrains.annotations.NotNull()
        java.lang.String id);
        
        @org.jetbrains.annotations.NotNull()
        public abstract id.kasirvippro.android.models.DialogModel getFilterSelected();
        
        @org.jetbrains.annotations.NotNull()
        public abstract java.util.ArrayList<id.kasirvippro.android.models.DialogModel> getFilters();
        
        public abstract void onChangeStatus(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.DialogModel selected);
        
        public abstract void setFilterDateSelected(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.FilterDialogDate data);
        
        @org.jetbrains.annotations.Nullable()
        public abstract id.kasirvippro.android.models.FilterDialogDate getFilterDateSelected();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH&J \u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\tH&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&\u00a8\u0006\u0010"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callGetHistoryAPI", "", "context", "Landroid/content/Context;", "restModel", "Lid/kasirvippro/android/models/transaction/TransactionRestModel;", "awal", "", "akhir", "status", "callGetSearchAPI", "id", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        public abstract void callGetHistoryAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.transaction.TransactionRestModel restModel, @org.jetbrains.annotations.NotNull()
        java.lang.String awal, @org.jetbrains.annotations.NotNull()
        java.lang.String akhir, @org.jetbrains.annotations.NotNull()
        java.lang.String status);
        
        public abstract void callGetSearchAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.transaction.TransactionRestModel restModel, @org.jetbrains.annotations.NotNull()
        java.lang.String id);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH&J\u0018\u0010\f\u001a\u00020\u00032\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\nH&\u00a8\u0006\u000e"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onSuccessGetHistory", "list", "", "Lid/kasirvippro/android/models/transaction/HistoryTransfer;", "onSuccessGetSearch", "Lid/kasirvippro/android/models/transaction/Transfer;", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessGetHistory(@org.jetbrains.annotations.Nullable()
        java.util.List<id.kasirvippro.android.models.transaction.HistoryTransfer> list);
        
        public abstract void onSuccessGetSearch(@org.jetbrains.annotations.Nullable()
        java.util.List<id.kasirvippro.android.models.transaction.Transfer> list);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}