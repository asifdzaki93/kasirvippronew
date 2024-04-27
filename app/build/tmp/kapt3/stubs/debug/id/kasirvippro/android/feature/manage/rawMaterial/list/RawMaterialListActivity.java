package id.kasirvippro.android.feature.manage.rawMaterial.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0006H\u0016J\b\u0010\u0017\u001a\u00020\u0002H\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\"\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0019H\u0014J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u0019H\u0014J\b\u0010%\u001a\u00020\u0019H\u0016J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u000fH\u0016J\b\u0010\'\u001a\u00020\u0019H\u0016J\b\u0010(\u001a\u00020\u0019H\u0002J\u0006\u0010)\u001a\u00020\u0019J\u0016\u0010*\u001a\u00020\u00192\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000f0,H\u0016J\b\u0010-\u001a\u00020\u0019H\u0002J\u0018\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u000201H\u0016J\u0012\u00102\u001a\u00020\u00192\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u0012\u00103\u001a\u00020\u00192\b\u00104\u001a\u0004\u0018\u000105H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00066"}, d2 = {"Lid/kasirvippro/android/feature/manage/rawMaterial/list/RawMaterialListActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manage/rawMaterial/list/RawMaterialListPresenter;", "Lid/kasirvippro/android/feature/manage/rawMaterial/list/RawMaterialListContract$View;", "()V", "CODE_OPEN_ADD", "", "CODE_OPEN_EDIT", "CODE_OPEN_SCAN", "adapter", "Lid/kasirvippro/android/feature/manage/rawMaterial/list/RawMaterialListAdapter;", "getAdapter", "()Lid/kasirvippro/android/feature/manage/rawMaterial/list/RawMaterialListAdapter;", "list2", "Ljava/util/ArrayList;", "Lid/kasirvippro/android/models/rawMaterial/RawMaterial;", "getList2", "()Ljava/util/ArrayList;", "setList2", "(Ljava/util/ArrayList;)V", "scrollListener", "Lid/kasirvippro/android/ui/EndlessRecyclerViewScrollListener;", "createLayout", "createPresenter", "hideLoading", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "openAddPage", "openEditPage", "reloadData", "renderView", "setList", "setProducts", "list", "", "setupToolbar", "showErrorMessage", "code", "msg", "", "showSuccessMessage", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class RawMaterialListActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manage.rawMaterial.list.RawMaterialListPresenter, id.kasirvippro.android.feature.manage.rawMaterial.list.RawMaterialListContract.View> implements id.kasirvippro.android.feature.manage.rawMaterial.list.RawMaterialListContract.View {
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.rawMaterial.list.RawMaterialListAdapter adapter = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<id.kasirvippro.android.models.rawMaterial.RawMaterial> list2;
    private id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener scrollListener;
    private final int CODE_OPEN_ADD = 1001;
    private final int CODE_OPEN_EDIT = 1002;
    private final int CODE_OPEN_SCAN = 1003;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manage.rawMaterial.list.RawMaterialListAdapter getAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<id.kasirvippro.android.models.rawMaterial.RawMaterial> getList2() {
        return null;
    }
    
    public final void setList2(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<id.kasirvippro.android.models.rawMaterial.RawMaterial> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.rawMaterial.list.RawMaterialListPresenter createPresenter() {
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
    
    private final void setupToolbar() {
    }
    
    public final void setList() {
    }
    
    @java.lang.Override()
    public void setProducts(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.rawMaterial.RawMaterial> list) {
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
    id.kasirvippro.android.models.rawMaterial.RawMaterial data) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public RawMaterialListActivity() {
        super();
    }
}