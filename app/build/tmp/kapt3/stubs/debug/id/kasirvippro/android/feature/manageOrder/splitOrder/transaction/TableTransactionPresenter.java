package id.kasirvippro.android.feature.manageOrder.splitOrder.transaction;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0018\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0016\u0010\u001b\u001a\u00020\u00142\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0016J\u0012\u0010\u001f\u001a\u00020\u00142\b\u0010 \u001a\u0004\u0018\u00010\fH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006!"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/splitOrder/transaction/TableTransactionPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/manageOrder/splitOrder/transaction/TableTransactionContract$View;", "Lid/kasirvippro/android/feature/manageOrder/splitOrder/transaction/TableTransactionContract$Presenter;", "Lid/kasirvippro/android/feature/manageOrder/splitOrder/transaction/TableTransactionContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/manageOrder/splitOrder/transaction/TableTransactionContract$View;)V", "getContext", "()Landroid/content/Context;", "data", "Lid/kasirvippro/android/models/table/Table;", "interactor", "Lid/kasirvippro/android/feature/manageOrder/splitOrder/transaction/TableTransactionInteractor;", "restModel", "Lid/kasirvippro/android/models/transaction/TransactionRestModel;", "getView", "()Lid/kasirvippro/android/feature/manageOrder/splitOrder/transaction/TableTransactionContract$View;", "loadTransactions", "", "onDestroy", "onFailedAPI", "code", "", "msg", "", "onSuccessGetTransactions", "list", "", "Lid/kasirvippro/android/models/transaction/Transaction;", "onViewCreated", "table", "app_debug"})
public final class TableTransactionPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.manageOrder.splitOrder.transaction.TableTransactionContract.View> implements id.kasirvippro.android.feature.manageOrder.splitOrder.transaction.TableTransactionContract.Presenter, id.kasirvippro.android.feature.manageOrder.splitOrder.transaction.TableTransactionContract.InteractorOutput {
    private id.kasirvippro.android.feature.manageOrder.splitOrder.transaction.TableTransactionInteractor interactor;
    private id.kasirvippro.android.models.transaction.TransactionRestModel restModel;
    private id.kasirvippro.android.models.table.Table data;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manageOrder.splitOrder.transaction.TableTransactionContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.models.table.Table table) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void loadTransactions() {
    }
    
    @java.lang.Override()
    public void onSuccessGetTransactions(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.transaction.Transaction> list) {
    }
    
    @java.lang.Override()
    public void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manageOrder.splitOrder.transaction.TableTransactionContract.View getView() {
        return null;
    }
    
    public TableTransactionPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manageOrder.splitOrder.transaction.TableTransactionContract.View view) {
        super();
    }
}