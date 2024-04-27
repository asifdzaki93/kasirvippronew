package id.kasirvippro.android.feature.manage.productVariant.edit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0002H\u0016J\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\"\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0006H\u0016J\b\u0010\u0017\u001a\u00020\fH\u0014J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u0019H\u0016J+\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00062\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0 2\u0006\u0010!\u001a\u00020\"H\u0016\u00a2\u0006\u0002\u0010#J\b\u0010$\u001a\u00020\fH\u0014J\b\u0010%\u001a\u00020\fH\u0016J\b\u0010&\u001a\u00020\fH\u0016J\b\u0010\'\u001a\u00020\fH\u0002J\u0010\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u0010*\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u0010+\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u0010,\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u0010-\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u0019H\u0016J\u0010\u0010.\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u0010/\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u0019H\u0016J\u0010\u00100\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u00101\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u00102\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u00103\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u00104\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0016J\b\u00105\u001a\u00020\fH\u0002J\u001a\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\u00062\b\u00108\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u00109\u001a\u00020\f2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u001a\u0010<\u001a\u00020\f*\u00020=2\u0006\u0010>\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006@"}, d2 = {"Lid/kasirvippro/android/feature/manage/productVariant/edit/EditProductVariantActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manage/productVariant/edit/EditProductVariantPresenter;", "Lid/kasirvippro/android/feature/manage/productVariant/edit/EditProductVariantContract$View;", "()V", "CODE_OPEN_SCAN", "", "choosePhotoHelper", "Lcom/aminography/choosephotohelper/ChoosePhotoHelper;", "createLayout", "createPresenter", "hideLoading", "", "loadPhoto", "path", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClose", "status", "onDestroy", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPremiumPage", "isPremium", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "openImageChooser", "openScanPage", "renderView", "setAlertstock", "value", "setBarcode", "setBuyPrice", "setDescription", "setGrosir", "setGrosirPrice", "setHaveStock", "setMinStock", "setProductName", "setSellPrice", "setStock", "setTax", "setupToolbar", "showMessage", "code", "msg", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "inputFilterDecimal", "Landroid/widget/EditText;", "maxDigitsIncludingPoint", "maxDecimalPlaces", "app_debug"})
public final class EditProductVariantActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manage.productVariant.edit.EditProductVariantPresenter, id.kasirvippro.android.feature.manage.productVariant.edit.EditProductVariantContract.View> implements id.kasirvippro.android.feature.manage.productVariant.edit.EditProductVariantContract.View {
    private com.aminography.choosephotohelper.ChoosePhotoHelper choosePhotoHelper;
    private final int CODE_OPEN_SCAN = 1001;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.productVariant.edit.EditProductVariantPresenter createPresenter() {
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
    
    public final void hideLoading() {
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
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void openScanPage() {
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
    public void setProductName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setStock(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setAlertstock(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setMinStock(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setSellPrice(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setBuyPrice(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setBarcode(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setGrosir(boolean value) {
    }
    
    @java.lang.Override()
    public void setHaveStock(boolean value) {
    }
    
    @java.lang.Override()
    public void setGrosirPrice(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setTax(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void onPremiumPage(boolean isPremium) {
    }
    
    public EditProductVariantActivity() {
        super();
    }
}