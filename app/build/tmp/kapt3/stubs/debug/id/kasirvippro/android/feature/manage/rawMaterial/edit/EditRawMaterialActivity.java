package id.kasirvippro.android.feature.manage.rawMaterial.edit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00032\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u000e\u001a\u00020\u0007H\u0016J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0006\u0010\u0010\u001a\u00020\u0011J\"\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J\b\u0010\u0019\u001a\u00020\u0011H\u0014J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u001eH\u0016J+\u0010#\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00072\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010\'\u001a\u00020(H\u0016\u00a2\u0006\u0002\u0010)J\b\u0010*\u001a\u00020\u0011H\u0014J\b\u0010+\u001a\u00020\u0011H\u0016J\b\u0010,\u001a\u00020\u0011H\u0002J\u0010\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020&H\u0016J\u0010\u0010/\u001a\u00020\u00112\u0006\u0010.\u001a\u00020&H\u0016J\u0010\u00100\u001a\u00020\u00112\u0006\u0010.\u001a\u00020&H\u0016J\u0010\u00101\u001a\u00020\u00112\u0006\u0010.\u001a\u00020&H\u0016J\u0010\u00102\u001a\u00020\u00112\u0006\u0010.\u001a\u00020&H\u0016J\b\u00103\u001a\u00020\u0011H\u0002J\u001a\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00072\b\u00106\u001a\u0004\u0018\u00010&H\u0016J\u0012\u00107\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u001a\u0010:\u001a\u00020\u0011*\u00020;2\u0006\u0010<\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006>"}, d2 = {"Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialPresenter;", "Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialContract$View;", "Lid/kasirvippro/android/feature/dialog/BottomDialog$Listener;", "()V", "CODE_OPEN_ADD", "", "CODE_OPEN_CHOOSE_UNIT", "CODE_OPEN_SCAN", "categoryDialog", "Lid/kasirvippro/android/feature/dialog/BottomDialog;", "choosePhotoHelper", "Lcom/aminography/choosephotohelper/ChoosePhotoHelper;", "createLayout", "createPresenter", "hideLoading", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClose", "status", "onDestroy", "onItemClicked", "Lid/kasirvippro/android/models/DialogModel;", "type", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPremiumPage", "isPremium", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "openChooseUnit", "renderView", "setDescription", "value", "setProductName", "setSellPrice", "setStock", "setUnitName", "setupToolbar", "showMessage", "code", "msg", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "inputFilterDecimal", "Landroid/widget/EditText;", "maxDigitsIncludingPoint", "maxDecimalPlaces", "app_debug"})
public final class EditRawMaterialActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialPresenter, id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialContract.View> implements id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialContract.View, id.kasirvippro.android.feature.dialog.BottomDialog.Listener {
    private final int CODE_OPEN_ADD = 101;
    private final int CODE_OPEN_CHOOSE_UNIT = 1005;
    private final id.kasirvippro.android.feature.dialog.BottomDialog categoryDialog = null;
    private com.aminography.choosephotohelper.ChoosePhotoHelper choosePhotoHelper;
    private final int CODE_OPEN_SCAN = 1001;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialPresenter createPresenter() {
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
    public void openChooseUnit() {
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
    public void onItemClicked(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.DialogModel data, int type) {
    }
    
    @java.lang.Override()
    public void setUnitName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
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
    public void setSellPrice(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void setDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @java.lang.Override()
    public void onPremiumPage(boolean isPremium) {
    }
    
    public EditRawMaterialActivity() {
        super();
    }
}