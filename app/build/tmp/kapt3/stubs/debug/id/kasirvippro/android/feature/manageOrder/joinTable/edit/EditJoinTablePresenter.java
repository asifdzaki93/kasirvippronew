package id.kasirvippro.android.feature.manageOrder.joinTable.edit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\u0018\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\u0012\u0010\u001e\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0016J\u0012\u0010\"\u001a\u00020\u00152\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006#"}, d2 = {"Lid/kasirvippro/android/feature/manageOrder/joinTable/edit/EditJoinTablePresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/manageOrder/joinTable/edit/EditJoinTableContract$View;", "Lid/kasirvippro/android/feature/manageOrder/joinTable/edit/EditJoinTableContract$Presenter;", "Lid/kasirvippro/android/feature/manageOrder/joinTable/edit/EditJoinTableContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/manageOrder/joinTable/edit/EditJoinTableContract$View;)V", "getContext", "()Landroid/content/Context;", "data", "Lid/kasirvippro/android/models/table/Table;", "interactor", "Lid/kasirvippro/android/feature/manageOrder/joinTable/edit/EditJoinTableInteractor;", "table", "tableRestModel", "Lid/kasirvippro/android/models/table/TableRestModel;", "getView", "()Lid/kasirvippro/android/feature/manageOrder/joinTable/edit/EditJoinTableContract$View;", "onCheck", "", "name", "", "id_table", "onDestroy", "onFailedEditTable", "code", "", "msg", "onSuccessJoinTable", "onViewCreated", "intent", "Landroid/content/Intent;", "updateTable", "app_debug"})
public final class EditJoinTablePresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.manageOrder.joinTable.edit.EditJoinTableContract.View> implements id.kasirvippro.android.feature.manageOrder.joinTable.edit.EditJoinTableContract.Presenter, id.kasirvippro.android.feature.manageOrder.joinTable.edit.EditJoinTableContract.InteractorOutput {
    private id.kasirvippro.android.feature.manageOrder.joinTable.edit.EditJoinTableInteractor interactor;
    private id.kasirvippro.android.models.table.TableRestModel tableRestModel;
    private id.kasirvippro.android.models.table.Table table;
    private id.kasirvippro.android.models.table.Table data;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manageOrder.joinTable.edit.EditJoinTableContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @java.lang.Override()
    public void onCheck(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String id_table) {
    }
    
    @java.lang.Override()
    public void updateTable(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.models.table.Table data) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onSuccessJoinTable(@org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void onFailedEditTable(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manageOrder.joinTable.edit.EditJoinTableContract.View getView() {
        return null;
    }
    
    public EditJoinTablePresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manageOrder.joinTable.edit.EditJoinTableContract.View view) {
        super();
    }
}