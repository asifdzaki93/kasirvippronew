package id.kasirvippro.android.feature.report.slip.chooseStaff;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0018\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0016\u0010\u001b\u001a\u00020\u00152\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u001aH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006#"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/chooseStaff/ChooseStaffPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/report/slip/chooseStaff/ChooseStaffContract$View;", "Lid/kasirvippro/android/feature/report/slip/chooseStaff/ChooseStaffContract$Presenter;", "Lid/kasirvippro/android/feature/report/slip/chooseStaff/ChooseStaffContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/report/slip/chooseStaff/ChooseStaffContract$View;)V", "codeKaryawan", "", "getContext", "()Landroid/content/Context;", "interactor", "Lid/kasirvippro/android/feature/report/slip/chooseStaff/ChooseStaffInteractor;", "restModel", "Lid/kasirvippro/android/models/staff/StaffRestModel;", "getView", "()Lid/kasirvippro/android/feature/report/slip/chooseStaff/ChooseStaffContract$View;", "getCodeKaryawan", "loadData", "", "onDestroy", "onFailedAPI", "code", "msg", "", "onSuccessGetData", "list", "", "Lid/kasirvippro/android/models/staff/Staff;", "onViewCreated", "intent", "Landroid/content/Intent;", "search", "app_debug"})
public final class ChooseStaffPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.report.slip.chooseStaff.ChooseStaffContract.View> implements id.kasirvippro.android.feature.report.slip.chooseStaff.ChooseStaffContract.Presenter, id.kasirvippro.android.feature.report.slip.chooseStaff.ChooseStaffContract.InteractorOutput {
    private id.kasirvippro.android.feature.report.slip.chooseStaff.ChooseStaffInteractor interactor;
    private id.kasirvippro.android.models.staff.StaffRestModel restModel;
    private int codeKaryawan;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.report.slip.chooseStaff.ChooseStaffContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @java.lang.Override()
    public void loadData() {
    }
    
    @java.lang.Override()
    public void search(@org.jetbrains.annotations.NotNull()
    java.lang.String search) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onSuccessGetData(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.staff.Staff> list) {
    }
    
    @java.lang.Override()
    public void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public int getCodeKaryawan() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.report.slip.chooseStaff.ChooseStaffContract.View getView() {
        return null;
    }
    
    public ChooseStaffPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.report.slip.chooseStaff.ChooseStaffContract.View view) {
        super();
    }
}