package id.kasirvippro.android.feature.manageOrder.kitchen.transaction;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 22\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003:\u000223B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0014J\"\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J$\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u0011H\u0016J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020%H\u0007J\u0010\u0010&\u001a\u00020\u00112\u0006\u0010\'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\u0011H\u0016J\b\u0010*\u001a\u00020\u0011H\u0002J\u0016\u0010+\u001a\u00020\u00112\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-H\u0016J\u0018\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u000e2\u0006\u00101\u001a\u00020(H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u00064"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionFragment;", "Lid/kasirvippro/android/base/BaseFragment;", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionPresenter;", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionContract$View;", "()V", "_view", "Landroid/view/View;", "adapter", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionAdapter;", "getAdapter", "()Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionAdapter;", "listener", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionFragment$Listener;", "openFilter", "", "createPresenter", "initAction", "", "view", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAttach", "context", "Landroid/content/Context;", "onCreateLayout", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onEvent", "event", "Lid/kasirvippro/android/events/onReloadTransaction;", "openDetail", "id", "", "reloadData", "renderView", "setList", "list", "", "Lid/kasirvippro/android/models/transaction/Transaction;", "showErrorMessage", "code", "msg", "Companion", "Listener", "app_debug"})
public final class TransactionFragment extends id.kasirvippro.android.base.BaseFragment<id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionPresenter, id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionContract.View> implements id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionContract.View {
    private android.view.View _view;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionAdapter adapter = null;
    private id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionFragment.Listener listener;
    private final int openFilter = 1100;
    @org.jetbrains.annotations.NotNull()
    public static final id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionAdapter getAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionPresenter createPresenter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected android.view.View onCreateLayout(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    protected void initAction(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void renderView() {
    }
    
    @java.lang.Override()
    public void reloadData() {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    @java.lang.Override()
    public void setList(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.transaction.Transaction> list) {
    }
    
    @java.lang.Override()
    public void showErrorMessage(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void openDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @org.greenrobot.eventbus.Subscribe()
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.events.onReloadTransaction event) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public TransactionFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionFragment newInstance() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000bH&\u00a8\u0006\f"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionFragment$Listener;", "", "openFilterByStatusDialog", "", "title", "", "list", "", "Lid/kasirvippro/android/models/DialogModel;", "selected", "type", "", "app_debug"})
    public static abstract interface Listener {
        
        public abstract void openFilterByStatusDialog(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.DialogModel> list, @org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.DialogModel selected, int type);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007\u00a8\u0006\u0005"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionFragment$Companion;", "", "()V", "newInstance", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionFragment;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionFragment newInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}