package id.kasirvippro.android.feature.manage.packages.edit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\u000bH\u0014J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J+\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\b2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016\u00a2\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u000bH\u0014J\b\u0010!\u001a\u00020\u000bH\u0016J\b\u0010\"\u001a\u00020\u000bH\u0002J\u001c\u0010#\u001a\u00020\u000b2\b\u0010$\u001a\u0004\u0018\u00010\r2\b\u0010%\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010&\u001a\u00020\u000bH\u0002J\u001a\u0010\'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\b2\b\u0010)\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010*\u001a\u00020\u000b2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\u001a\u0010-\u001a\u00020\u000b*\u00020.2\u0006\u0010/\u001a\u00020\b2\u0006\u00100\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lid/kasirvippro/android/feature/manage/packages/edit/EditPackagesActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manage/packages/edit/EditPackagesPresenter;", "Lid/kasirvippro/android/feature/manage/packages/edit/EditPackagesContract$View;", "()V", "choosePhotoHelper", "Lcom/aminography/choosephotohelper/ChoosePhotoHelper;", "createLayout", "", "createPresenter", "loadPhoto", "", "path", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClose", "status", "onDestroy", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "openImageChooser", "renderView", "setPackagesName", "name", "price", "setupToolbar", "showMessage", "code", "msg", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "inputFilterDecimal", "Landroid/widget/EditText;", "maxDigitsIncludingPoint", "maxDecimalPlaces", "app_debug"})
public final class EditPackagesActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manage.packages.edit.EditPackagesPresenter, id.kasirvippro.android.feature.manage.packages.edit.EditPackagesContract.View> implements id.kasirvippro.android.feature.manage.packages.edit.EditPackagesContract.View {
    private com.aminography.choosephotohelper.ChoosePhotoHelper choosePhotoHelper;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.packages.edit.EditPackagesPresenter createPresenter() {
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
    
    public final void inputFilterDecimal(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$inputFilterDecimal, int maxDigitsIncludingPoint, int maxDecimalPlaces) {
    }
    
    private final void setupToolbar() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @java.lang.Override()
    public void openImageChooser() {
    }
    
    @java.lang.Override()
    public void loadPhoto(@org.jetbrains.annotations.NotNull()
    java.lang.String path) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public void showMessage(int code, @org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void onClose(int status) {
    }
    
    @java.lang.Override()
    public void setPackagesName(@org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String price) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public EditPackagesActivity() {
        super();
    }
}