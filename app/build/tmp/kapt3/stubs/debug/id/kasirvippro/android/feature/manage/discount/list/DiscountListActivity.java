package id.kasirvippro.android.feature.manage.discount.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\nH\u0016J\b\u0010\u0016\u001a\u00020\u0002H\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\"\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0018H\u0014J\b\u0010&\u001a\u00020\u0018H\u0016J\u0012\u0010\'\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010(\u001a\u00020\u0018H\u0016J\b\u0010)\u001a\u00020\u0018H\u0002J\u0016\u0010*\u001a\u00020\u00182\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000e0,H\u0016J\u0016\u0010-\u001a\u00020\u00182\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000e0,H\u0016J\u0006\u0010.\u001a\u00020\u0018J\b\u0010/\u001a\u00020\u0018H\u0002J\u0018\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u00020\n2\u0006\u00102\u001a\u000203H\u0016J\u0012\u00104\u001a\u00020\u00182\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u0012\u00105\u001a\u00020\u00182\b\u00106\u001a\u0004\u0018\u000107H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lid/kasirvippro/android/feature/manage/discount/list/DiscountListActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manage/discount/list/DiscountListPresenter;", "Lid/kasirvippro/android/feature/manage/discount/list/DiscountListContract$View;", "()V", "adapter", "Lid/kasirvippro/android/feature/manage/discount/list/DiscountListAdapter;", "getAdapter", "()Lid/kasirvippro/android/feature/manage/discount/list/DiscountListAdapter;", "codeOpenAdd", "", "codeOpenEdit", "list2", "Ljava/util/ArrayList;", "Lid/kasirvippro/android/models/discount/Discount;", "getList2", "()Ljava/util/ArrayList;", "setList2", "(Ljava/util/ArrayList;)V", "scrollListener", "Lid/kasirvippro/android/ui/EndlessRecyclerViewScrollListener;", "createLayout", "createPresenter", "hideLoading", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "openAddPage", "openEditPage", "reloadData", "renderView", "setData", "list", "", "setDiscounts", "setList", "setupToolbar", "showErrorMessage", "code", "msg", "", "showSuccessMessage", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class DiscountListActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manage.discount.list.DiscountListPresenter, id.kasirvippro.android.feature.manage.discount.list.DiscountListContract.View> implements id.kasirvippro.android.feature.manage.discount.list.DiscountListContract.View {
    private final int codeOpenAdd = 1001;
    private final int codeOpenEdit = 1002;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.discount.list.DiscountListAdapter adapter = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<id.kasirvippro.android.models.discount.Discount> list2;
    private id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener scrollListener;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manage.discount.list.DiscountListAdapter getAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<id.kasirvippro.android.models.discount.Discount> getList2() {
        return null;
    }
    
    public final void setList2(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<id.kasirvippro.android.models.discount.Discount> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.discount.list.DiscountListPresenter createPresenter() {
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
    
    public final void hideLoading() {
    }
    
    private final void renderView() {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void setupToolbar() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.discount.Discount> list) {
    }
    
    public final void setList() {
    }
    
    @java.lang.Override()
    public void setDiscounts(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.discount.Discount> list) {
    }
    
    @java.lang.Override()
    public void showErrorMessage(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void showSuccessMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void reloadData() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void openAddPage() {
    }
    
    @java.lang.Override()
    public void openEditPage(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.models.discount.Discount data) {
    }
    
    public DiscountListActivity() {
        super();
    }
}