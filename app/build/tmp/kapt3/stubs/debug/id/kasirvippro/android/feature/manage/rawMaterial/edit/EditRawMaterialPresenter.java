package id.kasirvippro.android.feature.manage.rawMaterial.edit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0002J0\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001eH\u0016J\b\u0010#\u001a\u00020\u001bH\u0016J\u0018\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u001eH\u0016J\u0012\u0010(\u001a\u00020\u001b2\b\u0010\'\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010)\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020+H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006,"}, d2 = {"Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialContract$View;", "Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialContract$Presenter;", "Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialContract$View;)V", "cameraPermission", "Lid/kasirvippro/android/callback/PermissionCallback;", "getContext", "()Landroid/content/Context;", "data", "Lid/kasirvippro/android/models/rawMaterial/RawMaterial;", "interactor", "Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialInteractor;", "permissionUtil", "Lid/kasirvippro/android/utils/PermissionUtil;", "photoPermission", "premium", "", "restModel", "Lid/kasirvippro/android/models/rawMaterial/RawMaterialRestModel;", "getView", "()Lid/kasirvippro/android/feature/manage/rawMaterial/edit/EditRawMaterialContract$View;", "checkProduct", "", "onCheck", "name", "", "unit", "sell", "stok", "desc", "onDestroy", "onFailedAPI", "code", "", "msg", "onSuccessEditProduct", "onViewCreated", "intent", "Landroid/content/Intent;", "app_debug"})
public final class EditRawMaterialPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialContract.View> implements id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialContract.Presenter, id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialContract.InteractorOutput {
    private id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialInteractor interactor;
    private id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel restModel;
    private id.kasirvippro.android.utils.PermissionUtil permissionUtil;
    private id.kasirvippro.android.callback.PermissionCallback cameraPermission;
    private id.kasirvippro.android.callback.PermissionCallback photoPermission;
    private id.kasirvippro.android.models.rawMaterial.RawMaterial data;
    private boolean premium = false;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onCheck(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String unit, @org.jetbrains.annotations.NotNull()
    java.lang.String sell, @org.jetbrains.annotations.NotNull()
    java.lang.String stok, @org.jetbrains.annotations.NotNull()
    java.lang.String desc) {
    }
    
    @java.lang.Override()
    public void onSuccessEditProduct(@org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    private final void checkProduct() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialContract.View getView() {
        return null;
    }
    
    public EditRawMaterialPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialContract.View view) {
        super();
    }
}