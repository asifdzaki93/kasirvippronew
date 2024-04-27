package id.kasirvippro.android.feature.manage.supplier.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\"\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u000fH\u0016J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\u0019\u001a\u00020\u000fH\u0014J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u000fH\u0016J\b\u0010\u001e\u001a\u00020\u000fH\u0002J0\u0010\u001f\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010#\u001a\u0004\u0018\u00010!2\b\u0010$\u001a\u0004\u0018\u00010!H\u0016J\b\u0010%\u001a\u00020\u000fH\u0002J\b\u0010&\u001a\u00020\u000fH\u0002J\u0016\u0010\'\u001a\u00020\u000f2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002J\u0012\u0010+\u001a\u00020\u000f2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lid/kasirvippro/android/feature/manage/supplier/detail/DetailActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manage/supplier/detail/DetailPresenter;", "Lid/kasirvippro/android/feature/manage/supplier/detail/DetailContract$View;", "()V", "CODE_OPEN_EDIT", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "status", "viewPagerAdapter", "Lid/kasirvippro/android/ui/ViewPagerAdapter;", "createLayout", "createPresenter", "hideShowToolbar", "", "isShow", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onClose", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "openEditPage", "renderView", "setCustomer", "name", "", "email", "phone", "address", "setupTab", "setupToolbar", "setupViewPager", "list", "", "Lid/kasirvippro/android/models/TabModel;", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class DetailActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manage.supplier.detail.DetailPresenter, id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.View> implements id.kasirvippro.android.feature.manage.supplier.detail.DetailContract.View {
    private final androidx.fragment.app.FragmentManager fragmentManager = null;
    private final id.kasirvippro.android.ui.ViewPagerAdapter viewPagerAdapter = null;
    private final int CODE_OPEN_EDIT = 102;
    private int status = android.app.Activity.RESULT_CANCELED;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.supplier.detail.DetailPresenter createPresenter() {
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
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void setupToolbar() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void hideShowToolbar(boolean isShow) {
    }
    
    private final void setupViewPager(java.util.List<id.kasirvippro.android.models.TabModel> list) {
    }
    
    @java.lang.Override()
    public void setCustomer(@org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String email, @org.jetbrains.annotations.Nullable()
    java.lang.String phone, @org.jetbrains.annotations.Nullable()
    java.lang.String address) {
    }
    
    @java.lang.Override()
    public void onClose(int status) {
    }
    
    @java.lang.Override()
    public void openEditPage() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    public DetailActivity() {
        super();
    }
}