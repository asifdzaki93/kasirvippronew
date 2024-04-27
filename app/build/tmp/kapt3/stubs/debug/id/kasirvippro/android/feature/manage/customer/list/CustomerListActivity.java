package id.kasirvippro.android.feature.manage.customer.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0006H\u0016J\b\u0010\u0016\u001a\u00020\u0002H\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\"\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0018H\u0014J\u0010\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u0018H\u0014J\b\u0010\'\u001a\u00020\u0018H\u0016J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J\b\u0010)\u001a\u00020\u0018H\u0016J\b\u0010*\u001a\u00020\u0018H\u0002J\u0016\u0010+\u001a\u00020\u00182\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0-H\u0016J\u0016\u0010.\u001a\u00020\u00182\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0-H\u0016J\u0006\u0010/\u001a\u00020\u0018J\b\u00100\u001a\u00020\u0018H\u0002J\u0018\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u000204H\u0016J\u0012\u00105\u001a\u00020\u00182\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0012\u00106\u001a\u00020\u00182\b\u00107\u001a\u0004\u0018\u000108H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00069"}, d2 = {"Lid/kasirvippro/android/feature/manage/customer/list/CustomerListActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manage/customer/list/CustomerListPresenter;", "Lid/kasirvippro/android/feature/manage/customer/list/CustomerListContract$View;", "()V", "CODE_OPEN_ADD", "", "CODE_OPEN_EDIT", "adapter", "Lid/kasirvippro/android/feature/manage/customer/list/CustomerListAdapter;", "getAdapter", "()Lid/kasirvippro/android/feature/manage/customer/list/CustomerListAdapter;", "list2", "Ljava/util/ArrayList;", "Lid/kasirvippro/android/models/customer/Customer;", "getList2", "()Ljava/util/ArrayList;", "setList2", "(Ljava/util/ArrayList;)V", "scrollListener", "Lid/kasirvippro/android/ui/EndlessRecyclerViewScrollListener;", "createLayout", "createPresenter", "hideLoading", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "openAddPage", "openEditPage", "reloadData", "renderView", "setCustomers", "list", "", "setData", "setList", "setupToolbar", "showErrorMessage", "code", "msg", "", "showSuccessMessage", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class CustomerListActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manage.customer.list.CustomerListPresenter, id.kasirvippro.android.feature.manage.customer.list.CustomerListContract.View> implements id.kasirvippro.android.feature.manage.customer.list.CustomerListContract.View {
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.customer.list.CustomerListAdapter adapter = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<id.kasirvippro.android.models.customer.Customer> list2;
    private id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener scrollListener;
    private final int CODE_OPEN_ADD = 101;
    private final int CODE_OPEN_EDIT = 102;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manage.customer.list.CustomerListAdapter getAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<id.kasirvippro.android.models.customer.Customer> getList2() {
        return null;
    }
    
    public final void setList2(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<id.kasirvippro.android.models.customer.Customer> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.customer.list.CustomerListPresenter createPresenter() {
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
    public void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.customer.Customer> list) {
    }
    
    public final void setList() {
    }
    
    @java.lang.Override()
    public void setCustomers(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.customer.Customer> list) {
    }
    
    @java.lang.Override()
    protected void onResume() {
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
    public void openEditPage(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.customer.Customer data) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public CustomerListActivity() {
        super();
    }
}