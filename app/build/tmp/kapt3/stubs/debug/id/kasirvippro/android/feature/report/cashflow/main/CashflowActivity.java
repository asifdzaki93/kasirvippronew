package id.kasirvippro.android.feature.report.cashflow.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00032\u00020\u00042\u00020\u0005B\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0011H\u0014J0\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010 \u001a\u00020\u0011H\u0002J\b\u0010!\u001a\u00020\u0011H\u0002J\b\u0010\"\u001a\u00020\u0011H\u0002J\u0016\u0010#\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020$0\u001eH\u0002J\u0012\u0010%\u001a\u00020\u00112\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lid/kasirvippro/android/feature/report/cashflow/main/CashflowActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/report/cashflow/main/CashflowPresenter;", "Lid/kasirvippro/android/feature/report/cashflow/main/CashflowContract$View;", "Lid/kasirvippro/android/feature/report/listCashflow/ListCashflowFragment$Listener;", "Lid/kasirvippro/android/feature/dialog/BottomDialog$Listener;", "()V", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "transactionFragment", "Lid/kasirvippro/android/feature/report/listCashflow/ListCashflowFragment;", "viewPagerAdapter", "Lid/kasirvippro/android/ui/ViewPagerAdapter;", "createLayout", "", "createPresenter", "onItemClicked", "", "data", "Lid/kasirvippro/android/models/DialogModel;", "type", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "openFilterByStatusDialog", "title", "", "list", "", "selected", "renderView", "setupTab", "setupToolbar", "setupViewPager", "Lid/kasirvippro/android/models/TabModel;", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class CashflowActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.report.cashflow.main.CashflowPresenter, id.kasirvippro.android.feature.report.cashflow.main.CashflowContract.View> implements id.kasirvippro.android.feature.report.cashflow.main.CashflowContract.View, id.kasirvippro.android.feature.report.listCashflow.ListCashflowFragment.Listener, id.kasirvippro.android.feature.dialog.BottomDialog.Listener {
    private final androidx.fragment.app.FragmentManager fragmentManager = null;
    private final id.kasirvippro.android.ui.ViewPagerAdapter viewPagerAdapter = null;
    private final id.kasirvippro.android.feature.report.listCashflow.ListCashflowFragment transactionFragment = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.report.cashflow.main.CashflowPresenter createPresenter() {
        return null;
    }
    
    @java.lang.Override()
    public int createLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void startingUpActivity(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void renderView() {
    }
    
    private final void setupTab() {
    }
    
    private final void setupViewPager(java.util.List<id.kasirvippro.android.models.TabModel> list) {
    }
    
    @java.lang.Override()
    public void openFilterByStatusDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.DialogModel> list, @org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.models.DialogModel selected, int type) {
    }
    
    @java.lang.Override()
    public void onItemClicked(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.DialogModel data, int type) {
    }
    
    private final void setupToolbar() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    public CashflowActivity() {
        super();
    }
}