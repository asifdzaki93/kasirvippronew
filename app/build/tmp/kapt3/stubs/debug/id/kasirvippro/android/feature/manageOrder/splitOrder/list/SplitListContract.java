package id.kasirvippro.android.feature.manageOrder.splitOrder.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/splitOrder/list/SplitListContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface SplitListContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\u001a\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0010"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/splitOrder/list/SplitListContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "openEditTablePage", "", "id", "", "reloadData", "setData", "list", "", "Lid/kasirvippro/android/models/transaction/Transaction;", "showErrorMessage", "code", "", "msg", "showSuccessMessage", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void setData(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.transaction.Transaction> list);
        
        public abstract void showErrorMessage(int code, @org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void showSuccessMessage(@org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void reloadData();
        
        public abstract void openEditTablePage(@org.jetbrains.annotations.NotNull()
        java.lang.String id);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0004H&\u00a8\u0006\u0007"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/splitOrder/list/SplitListContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/manageOrder/splitOrder/list/SplitListContract$View;", "loadCategories", "", "onDestroy", "onViewCreated", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.manageOrder.splitOrder.list.SplitListContract.View> {
        
        public abstract void onViewCreated();
        
        public abstract void onDestroy();
        
        public abstract void loadCategories();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&\u00a8\u0006\n"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/splitOrder/list/SplitListContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callGetTransactionsAPI", "", "context", "Landroid/content/Context;", "restModel", "Lid/kasirvippro/android/models/transaction/TransactionRestModel;", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        public abstract void callGetTransactionsAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.transaction.TransactionRestModel restModel);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&\u00a8\u0006\f"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/splitOrder/list/SplitListContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onSuccessGetTransactions", "list", "", "Lid/kasirvippro/android/models/transaction/Transaction;", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessGetTransactions(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.transaction.Transaction> list);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}