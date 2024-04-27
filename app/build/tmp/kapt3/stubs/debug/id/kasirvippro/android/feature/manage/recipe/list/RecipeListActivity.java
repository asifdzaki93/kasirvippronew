package id.kasirvippro.android.feature.manage.recipe.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0006H\u0016J\b\u0010\u0016\u001a\u00020\u0002H\u0016J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016J\u0006\u0010\u001a\u001a\u00020\u001bJ\"\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u001bH\u0014J\u0010\u0010&\u001a\u00020\"2\u0006\u0010\'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\u001bH\u0014J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u0018H\u0016J\u0010\u0010,\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u000eH\u0016J\b\u0010-\u001a\u00020\u001bH\u0016J\b\u0010.\u001a\u00020\u001bH\u0002J\u0006\u0010/\u001a\u00020\u001bJ\u0010\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u0018H\u0017J\u0016\u00102\u001a\u00020\u001b2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000e04H\u0016J\b\u00105\u001a\u00020\u001bH\u0002J\u0018\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u0018H\u0016J\u0012\u00109\u001a\u00020\u001b2\b\u00108\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010:\u001a\u00020\u001b2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListPresenter;", "Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListContract$View;", "()V", "CODE_OPEN_ADD", "", "CODE_OPEN_EDIT", "adapter", "Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListAdapter;", "getAdapter", "()Lid/kasirvippro/android/feature/manage/recipe/list/RecipeListAdapter;", "list2", "Ljava/util/ArrayList;", "Lid/kasirvippro/android/models/recipe/Recipe;", "getList2", "()Ljava/util/ArrayList;", "setList2", "(Ljava/util/ArrayList;)V", "scrollListener", "Lid/kasirvippro/android/ui/EndlessRecyclerViewScrollListener;", "createLayout", "createPresenter", "getDetail", "", "getIdProduct", "hideLoading", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "openAddPage", "id", "openEditPage", "reloadData", "renderView", "setList", "setProduct", "value", "setProducts", "list", "", "setupToolbar", "showErrorMessage", "code", "msg", "showSuccessMessage", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class RecipeListActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manage.recipe.list.RecipeListPresenter, id.kasirvippro.android.feature.manage.recipe.list.RecipeListContract.View> implements id.kasirvippro.android.feature.manage.recipe.list.RecipeListContract.View {
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.recipe.list.RecipeListAdapter adapter = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<id.kasirvippro.android.models.recipe.Recipe> list2;
    private id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener scrollListener;
    private final int CODE_OPEN_ADD = 1001;
    private final int CODE_OPEN_EDIT = 1002;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manage.recipe.list.RecipeListAdapter getAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<id.kasirvippro.android.models.recipe.Recipe> getList2() {
        return null;
    }
    
    public final void setList2(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<id.kasirvippro.android.models.recipe.Recipe> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.recipe.list.RecipeListPresenter createPresenter() {
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
    
    public final void setList() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String getIdProduct() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String getDetail() {
        return null;
    }
    
    @java.lang.Override()
    public void setProducts(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.recipe.Recipe> list) {
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
    public void openAddPage(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override()
    public void setProduct(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void openEditPage(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.recipe.Recipe data) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public RecipeListActivity() {
        super();
    }
}