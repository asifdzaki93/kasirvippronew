package id.kasirvippro.android.feature.report.slip.chooseMonth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0002H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0014J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0014J2\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u000bH\u0002J\u001e\u0010\u001e\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u001f2\u0006\u0010 \u001a\u00020\u0013H\u0016J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u000bH\u0002J\u001a\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\b2\b\u0010\'\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010(\u001a\u00020\u000b2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthPresenter;", "Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthContract$View;", "()V", "adapter", "Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthAdapter;", "createLayout", "", "createPresenter", "onDestroy", "", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "openAbsentPage", "date", "Lid/kasirvippro/android/models/FilterDialogDate;", "list", "Ljava/util/ArrayList;", "Lid/kasirvippro/android/models/slip/Absent;", "Lkotlin/collections/ArrayList;", "data", "Lid/kasirvippro/android/models/staff/Staff;", "openSlipPage", "slip", "Lid/kasirvippro/android/models/slip/Slip;", "renderView", "setList", "", "selected", "setYear", "year", "", "setupToolbar", "showMessage", "code", "msg", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class MonthActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.report.slip.chooseMonth.MonthPresenter, id.kasirvippro.android.feature.report.slip.chooseMonth.MonthContract.View> implements id.kasirvippro.android.feature.report.slip.chooseMonth.MonthContract.View {
    private final id.kasirvippro.android.feature.report.slip.chooseMonth.MonthAdapter adapter = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.report.slip.chooseMonth.MonthPresenter createPresenter() {
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
    public void setYear(@org.jetbrains.annotations.NotNull()
    java.lang.String year) {
    }
    
    @java.lang.Override()
    public void setList(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.FilterDialogDate> list, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.FilterDialogDate selected) {
    }
    
    @java.lang.Override()
    public void openSlipPage(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.FilterDialogDate date, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.slip.Slip slip) {
    }
    
    @java.lang.Override()
    public void openAbsentPage(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.FilterDialogDate date, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<id.kasirvippro.android.models.slip.Absent> list, @org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.models.staff.Staff data) {
    }
    
    private final void setupToolbar() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void showMessage(int code, @org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    public MonthActivity() {
        super();
    }
}