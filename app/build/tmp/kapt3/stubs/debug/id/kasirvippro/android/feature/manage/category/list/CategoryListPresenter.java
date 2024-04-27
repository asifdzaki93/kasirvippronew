package id.kasirvippro.android.feature.manage.category.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0012H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0014H\u0016J\u0012\u0010\u001d\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014H\u0016J\u0016\u0010\u001e\u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J\u0010\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0014H\u0016J\u0014\u0010%\u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006&"}, d2 = {"Lid/kasirvippro/android/feature/manage/category/list/CategoryListPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/manage/category/list/CategoryListContract$View;", "Lid/kasirvippro/android/feature/manage/category/list/CategoryListContract$Presenter;", "Lid/kasirvippro/android/feature/manage/category/list/CategoryListContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/manage/category/list/CategoryListContract$View;)V", "categoryRestModel", "Lid/kasirvippro/android/models/category/CategoryRestModel;", "getContext", "()Landroid/content/Context;", "interactor", "Lid/kasirvippro/android/feature/manage/category/list/CategoryListInteractor;", "getView", "()Lid/kasirvippro/android/feature/manage/category/list/CategoryListContract$View;", "deleteCategory", "", "id", "", "position", "", "increment", "loadCategories", "onDestroy", "onFailedAPI", "code", "msg", "onSuccessDeleteCategory", "onSuccessGetCategories", "list", "", "Lid/kasirvippro/android/models/category/Category;", "onViewCreated", "searchCategory", "search", "setCategory", "app_debug"})
public final class CategoryListPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.manage.category.list.CategoryListContract.View> implements id.kasirvippro.android.feature.manage.category.list.CategoryListContract.Presenter, id.kasirvippro.android.feature.manage.category.list.CategoryListContract.InteractorOutput {
    private id.kasirvippro.android.feature.manage.category.list.CategoryListInteractor interactor;
    private id.kasirvippro.android.models.category.CategoryRestModel categoryRestModel;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.category.list.CategoryListContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated() {
    }
    
    @java.lang.Override()
    public void loadCategories() {
    }
    
    public final void setCategory(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.category.Category> list) {
    }
    
    @java.lang.Override()
    public void deleteCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String id, int position, @org.jetbrains.annotations.NotNull()
    java.lang.String increment) {
    }
    
    @java.lang.Override()
    public void searchCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String search) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onSuccessGetCategories(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.category.Category> list) {
    }
    
    @java.lang.Override()
    public void onSuccessDeleteCategory(@org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
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
    public final id.kasirvippro.android.feature.manage.category.list.CategoryListContract.View getView() {
        return null;
    }
    
    public CategoryListPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manage.category.list.CategoryListContract.View view) {
        super();
    }
}