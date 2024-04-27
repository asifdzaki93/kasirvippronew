package id.kasirvippro.android.feature.report.slip.slip;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\u001bH\u0016J\u0018\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000eH\u0016J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u001bH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006&"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/slip/SlipPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$View;", "Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$Presenter;", "Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$View;)V", "getContext", "()Landroid/content/Context;", "data", "Lid/kasirvippro/android/models/slip/Slip;", "date", "", "downloadPermission", "Lid/kasirvippro/android/callback/PermissionCallback;", "interactor", "Lid/kasirvippro/android/feature/report/slip/slip/SlipInteractor;", "permissionUtil", "Lid/kasirvippro/android/utils/PermissionUtil;", "premium", "", "storagePermission", "getView", "()Lid/kasirvippro/android/feature/report/slip/slip/SlipContract$View;", "onCheckDownload", "", "onCheckShare", "onDestroy", "onFailedAPI", "code", "", "msg", "onViewCreated", "intent", "Landroid/content/Intent;", "setData", "app_debug"})
public final class SlipPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.report.slip.slip.SlipContract.View> implements id.kasirvippro.android.feature.report.slip.slip.SlipContract.Presenter, id.kasirvippro.android.feature.report.slip.slip.SlipContract.InteractorOutput {
    private id.kasirvippro.android.feature.report.slip.slip.SlipInteractor interactor;
    private java.lang.String date;
    private id.kasirvippro.android.models.slip.Slip data;
    private final id.kasirvippro.android.utils.PermissionUtil permissionUtil = null;
    private id.kasirvippro.android.callback.PermissionCallback storagePermission;
    private id.kasirvippro.android.callback.PermissionCallback downloadPermission;
    private boolean premium = false;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.report.slip.slip.SlipContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void setData() {
    }
    
    @java.lang.Override()
    public void onCheckShare() {
    }
    
    @java.lang.Override()
    public void onCheckDownload() {
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
    public final id.kasirvippro.android.feature.report.slip.slip.SlipContract.View getView() {
        return null;
    }
    
    public SlipPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.report.slip.slip.SlipContract.View view) {
        super();
    }
}