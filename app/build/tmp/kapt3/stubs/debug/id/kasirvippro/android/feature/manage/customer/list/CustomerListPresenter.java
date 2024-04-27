package id.kasirvippro.android.feature.manage.customer.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0012H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0014H\u0016J\u0012\u0010\u001d\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014H\u0016J\u0016\u0010\u001e\u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J\u0010\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0014H\u0016J\u0014\u0010%\u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006&"}, d2 = {"Lid/kasirvippro/android/feature/manage/customer/list/CustomerListPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/manage/customer/list/CustomerListContract$View;", "Lid/kasirvippro/android/feature/manage/customer/list/CustomerListContract$Presenter;", "Lid/kasirvippro/android/feature/manage/customer/list/CustomerListContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/manage/customer/list/CustomerListContract$View;)V", "getContext", "()Landroid/content/Context;", "interactor", "Lid/kasirvippro/android/feature/manage/customer/list/CustomerListInteractor;", "restModel", "Lid/kasirvippro/android/models/customer/CustomerRestModel;", "getView", "()Lid/kasirvippro/android/feature/manage/customer/list/CustomerListContract$View;", "deleteCustomer", "", "id", "", "position", "", "increment", "loadCustomers", "onDestroy", "onFailedAPI", "code", "msg", "onSuccessDeleteCustomer", "onSuccessGetCustomers", "list", "", "Lid/kasirvippro/android/models/customer/Customer;", "onViewCreated", "searchCustomer", "search", "setCustomer", "app_debug"})
public final class CustomerListPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.manage.customer.list.CustomerListContract.View> implements id.kasirvippro.android.feature.manage.customer.list.CustomerListContract.Presenter, id.kasirvippro.android.feature.manage.customer.list.CustomerListContract.InteractorOutput {
    private id.kasirvippro.android.feature.manage.customer.list.CustomerListInteractor interactor;
    private id.kasirvippro.android.models.customer.CustomerRestModel restModel;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.customer.list.CustomerListContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated() {
    }
    
    @java.lang.Override()
    public void loadCustomers() {
    }
    
    @java.lang.Override()
    public void deleteCustomer(@org.jetbrains.annotations.NotNull()
    java.lang.String id, int position, @org.jetbrains.annotations.NotNull()
    java.lang.String increment) {
    }
    
    @java.lang.Override()
    public void searchCustomer(@org.jetbrains.annotations.NotNull()
    java.lang.String search) {
    }
    
    public final void setCustomer(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.customer.Customer> list) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onSuccessGetCustomers(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.customer.Customer> list) {
    }
    
    @java.lang.Override()
    public void onSuccessDeleteCustomer(@org.jetbrains.annotations.Nullable()
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
    public final id.kasirvippro.android.feature.manage.customer.list.CustomerListContract.View getView() {
        return null;
    }
    
    public CustomerListPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manage.customer.list.CustomerListContract.View view) {
        super();
    }
}