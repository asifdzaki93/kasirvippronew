package id.kasirvippro.android.feature.report.slip.chooseMonth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface MonthContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH&J\u001e\u0010\u000f\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00102\u0006\u0010\u0011\u001a\u00020\u0005H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H&J\u001a\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0014H&\u00a8\u0006\u0019"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "openAbsentPage", "", "date", "Lid/kasirvippro/android/models/FilterDialogDate;", "list", "Ljava/util/ArrayList;", "Lid/kasirvippro/android/models/slip/Absent;", "Lkotlin/collections/ArrayList;", "data", "Lid/kasirvippro/android/models/staff/Staff;", "openSlipPage", "slip", "Lid/kasirvippro/android/models/slip/Slip;", "setList", "", "selected", "setYear", "year", "", "showMessage", "code", "", "msg", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void showMessage(int code, @org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void setYear(@org.jetbrains.annotations.NotNull()
        java.lang.String year);
        
        public abstract void setList(@org.jetbrains.annotations.NotNull()
        java.util.List<id.kasirvippro.android.models.FilterDialogDate> list, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.FilterDialogDate selected);
        
        public abstract void openSlipPage(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.FilterDialogDate date, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.slip.Slip slip);
        
        public abstract void openAbsentPage(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.FilterDialogDate date, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<id.kasirvippro.android.models.slip.Absent> list, @org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.staff.Staff data);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&J\n\u0010\b\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\nH&J\b\u0010\f\u001a\u00020\nH&J\b\u0010\r\u001a\u00020\nH&J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\nH&J\u0012\u0010\u0012\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0014"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthContract$View;", "getDates", "", "Lid/kasirvippro/android/models/FilterDialogDate;", "year", "", "getSelectedDate", "onCheck", "", "onDestroy", "onNextYear", "onPrevYear", "onViewCreated", "intent", "Landroid/content/Intent;", "setDate", "setSelectedDate", "date", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.report.slip.chooseMonth.MonthContract.View> {
        
        public abstract void onViewCreated(@org.jetbrains.annotations.NotNull()
        android.content.Intent intent);
        
        public abstract void onDestroy();
        
        @org.jetbrains.annotations.Nullable()
        public abstract id.kasirvippro.android.models.FilterDialogDate getSelectedDate();
        
        public abstract void setSelectedDate(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.FilterDialogDate date);
        
        @org.jetbrains.annotations.NotNull()
        public abstract java.util.List<id.kasirvippro.android.models.FilterDialogDate> getDates(int year);
        
        public abstract void onNextYear();
        
        public abstract void onPrevYear();
        
        public abstract void setDate();
        
        public abstract void onCheck();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH&J*\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&\u00a8\u0006\u000f"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callGetAbsenAPI", "", "context", "Landroid/content/Context;", "restModel", "Lid/kasirvippro/android/models/slip/SlipRestModel;", "id", "", "date", "Lid/kasirvippro/android/models/FilterDialogDate;", "callGetSlipAPI", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        public abstract void callGetSlipAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.slip.SlipRestModel restModel, @org.jetbrains.annotations.Nullable()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.FilterDialogDate date);
        
        public abstract void callGetAbsenAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.slip.SlipRestModel restModel, @org.jetbrains.annotations.Nullable()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.FilterDialogDate date);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH&J\u0018\u0010\f\u001a\u00020\u00032\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\nH&\u00a8\u0006\u000e"}, d2 = {"Lid/kasirvippro/android/feature/report/slip/chooseMonth/MonthContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onSuccessAbsent", "list", "", "Lid/kasirvippro/android/models/slip/Absent;", "onSuccessPaySlip", "Lid/kasirvippro/android/models/slip/Slip;", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessPaySlip(@org.jetbrains.annotations.Nullable()
        java.util.List<id.kasirvippro.android.models.slip.Slip> list);
        
        public abstract void onSuccessAbsent(@org.jetbrains.annotations.Nullable()
        java.util.List<id.kasirvippro.android.models.slip.Absent> list);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}