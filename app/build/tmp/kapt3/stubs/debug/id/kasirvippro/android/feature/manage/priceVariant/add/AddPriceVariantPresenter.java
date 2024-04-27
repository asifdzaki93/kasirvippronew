package id.kasirvippro.android.feature.manage.priceVariant.add;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\u0018\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\u0012\u0010\u001e\u001a\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006 "}, d2 = {"Lid/kasirvippro/android/feature/manage/priceVariant/add/AddPriceVariantPresenter;", "Lid/kasirvippro/android/base/BasePresenter;", "Lid/kasirvippro/android/feature/manage/priceVariant/add/AddPriceVariantContract$View;", "Lid/kasirvippro/android/feature/manage/priceVariant/add/AddPriceVariantContract$Presenter;", "Lid/kasirvippro/android/feature/manage/priceVariant/add/AddPriceVariantContract$InteractorOutput;", "context", "Landroid/content/Context;", "view", "(Landroid/content/Context;Lid/kasirvippro/android/feature/manage/priceVariant/add/AddPriceVariantContract$View;)V", "getContext", "()Landroid/content/Context;", "interactor", "Lid/kasirvippro/android/feature/manage/priceVariant/add/AddPriceVariantInteractor;", "premium", "", "restModel", "Lid/kasirvippro/android/models/priceVariant/PriceVariantRestModel;", "getView", "()Lid/kasirvippro/android/feature/manage/priceVariant/add/AddPriceVariantContract$View;", "checkProduct", "", "onCheck", "minimal", "", "nominal", "onDestroy", "onFailedAPI", "code", "", "msg", "onSuccessAddProduct", "onViewCreated", "app_debug"})
public final class AddPriceVariantPresenter extends id.kasirvippro.android.base.BasePresenter<id.kasirvippro.android.feature.manage.priceVariant.add.AddPriceVariantContract.View> implements id.kasirvippro.android.feature.manage.priceVariant.add.AddPriceVariantContract.Presenter, id.kasirvippro.android.feature.manage.priceVariant.add.AddPriceVariantContract.InteractorOutput {
    private id.kasirvippro.android.feature.manage.priceVariant.add.AddPriceVariantInteractor interactor;
    private id.kasirvippro.android.models.priceVariant.PriceVariantRestModel restModel;
    private boolean premium = false;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.priceVariant.add.AddPriceVariantContract.View view = null;
    
    @java.lang.Override()
    public void onViewCreated() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onCheck(@org.jetbrains.annotations.NotNull()
    java.lang.String minimal, @org.jetbrains.annotations.NotNull()
    java.lang.String nominal) {
    }
    
    @java.lang.Override()
    public void onSuccessAddProduct(@org.jetbrains.annotations.Nullable()
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
    public final id.kasirvippro.android.feature.manage.priceVariant.add.AddPriceVariantContract.View getView() {
        return null;
    }
    
    public AddPriceVariantPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.feature.manage.priceVariant.add.AddPriceVariantContract.View view) {
        super();
    }
}