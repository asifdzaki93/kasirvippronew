package id.kasirvippro.android.feature.manageOrder.kitchen.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B\u0005\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\b\u0010\u0017\u001a\u00020\u0002H\u0016J\u0018\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0015H\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0013H\u0014J0\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0%2\b\u0010&\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0015H\u0016J\b\u0010\'\u001a\u00020\u0013H\u0002J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010)\u001a\u00020\u0013H\u0002J\b\u0010*\u001a\u00020\u0013H\u0002J\u0016\u0010+\u001a\u00020\u00132\f\u0010$\u001a\b\u0012\u0004\u0012\u00020,0%H\u0002J\u0012\u0010-\u001a\u00020\u00132\b\u0010.\u001a\u0004\u0018\u00010/H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/kitchen/main/DataOrderActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manageOrder/kitchen/main/DataOrderPresenter;", "Lid/kasirvippro/android/feature/manageOrder/kitchen/main/DataOrderContract$View;", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionFragment$Listener;", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transactionPreorder/PreorderFragment$Listener;", "Lid/kasirvippro/android/feature/dialog/BottomDialog$Listener;", "()V", "mFragmentManager", "Landroidx/fragment/app/FragmentManager;", "preorderFragment", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transactionPreorder/PreorderFragment;", "spendFragment", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transactionSuccess/TransactionSuccessFragment;", "transactionFragment", "Lid/kasirvippro/android/feature/manageOrder/kitchen/transaction/TransactionFragment;", "viewPagerAdapter", "Lid/kasirvippro/android/ui/ViewPagerAdapter;", "checkTab", "", "position", "", "createLayout", "createPresenter", "onItemClicked", "data", "Lid/kasirvippro/android/models/DialogModel;", "type", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "openFilterByStatusDialog", "title", "", "list", "", "selected", "renderView", "setSelectTab", "setupTab", "setupToolbar", "setupViewPager", "Lid/kasirvippro/android/models/TabModel;", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class DataOrderActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderPresenter, id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderContract.View> implements id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderContract.View, id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionFragment.Listener, id.kasirvippro.android.feature.manageOrder.kitchen.transactionPreorder.PreorderFragment.Listener, id.kasirvippro.android.feature.dialog.BottomDialog.Listener {
    private androidx.fragment.app.FragmentManager mFragmentManager;
    private id.kasirvippro.android.ui.ViewPagerAdapter viewPagerAdapter;
    private final id.kasirvippro.android.feature.manageOrder.kitchen.transaction.TransactionFragment transactionFragment = null;
    private final id.kasirvippro.android.feature.manageOrder.kitchen.transactionPreorder.PreorderFragment preorderFragment = null;
    private final id.kasirvippro.android.feature.manageOrder.kitchen.transactionSuccess.TransactionSuccessFragment spendFragment = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manageOrder.kitchen.main.DataOrderPresenter createPresenter() {
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
    public void setSelectTab(int position) {
    }
    
    @java.lang.Override()
    public void checkTab(int position) {
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
    
    public DataOrderActivity() {
        super();
    }
}