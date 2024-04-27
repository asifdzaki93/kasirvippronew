package id.kasirvippro.android.feature.etalase.kelolatoko.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0014J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0011H\u0014J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0011H\u0016J\b\u0010\"\u001a\u00020\u0011H\u0002J\u0016\u0010#\u001a\u00020\u00112\f\u0010$\u001a\b\u0012\u0004\u0012\u00020 0%H\u0016J\b\u0010&\u001a\u00020\u0011H\u0002J\u001a\u0010\'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u00062\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0012\u0010+\u001a\u00020\u00112\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0012\u0010,\u001a\u00020\u00112\b\u0010-\u001a\u0004\u0018\u00010.H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lid/kasirvippro/android/feature/etalase/kelolatoko/list/KelolatokoListActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/etalase/kelolatoko/list/KelolatokoListPresenter;", "Lid/kasirvippro/android/feature/etalase/kelolatoko/list/KelolatokoListContract$View;", "()V", "CODE_OPEN_ADD", "", "CODE_OPEN_EDIT", "adapter", "Lid/kasirvippro/android/feature/etalase/kelolatoko/list/KelolatokoListAdapter;", "getAdapter", "()Lid/kasirvippro/android/feature/etalase/kelolatoko/list/KelolatokoListAdapter;", "scrollListener", "Lid/kasirvippro/android/ui/EndlessRecyclerViewScrollListener;", "createLayout", "createPresenter", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "openEditPage", "Lid/kasirvippro/android/models/kelolatoko/Kelolatoko;", "reloadData", "renderView", "setData", "list", "", "setupToolbar", "showErrorMessage", "code", "msg", "", "showSuccessMessage", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class KelolatokoListActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.etalase.kelolatoko.list.KelolatokoListPresenter, id.kasirvippro.android.feature.etalase.kelolatoko.list.KelolatokoListContract.View> implements id.kasirvippro.android.feature.etalase.kelolatoko.list.KelolatokoListContract.View {
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.etalase.kelolatoko.list.KelolatokoListAdapter adapter = null;
    private id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener scrollListener;
    private final int CODE_OPEN_ADD = 101;
    private final int CODE_OPEN_EDIT = 102;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.etalase.kelolatoko.list.KelolatokoListAdapter getAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.etalase.kelolatoko.list.KelolatokoListPresenter createPresenter() {
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
    java.util.List<id.kasirvippro.android.models.kelolatoko.Kelolatoko> list) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void showErrorMessage(int code, @org.jetbrains.annotations.Nullable()
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
    public void openEditPage(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.kelolatoko.Kelolatoko data) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public KelolatokoListActivity() {
        super();
    }
}