package id.kasirvippro.android.feature.manage.packages.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00032\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0002H\u0016J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0007H\u0016J\"\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u001bH\u0014J\u0018\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0007H\u0016J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0011H\u0014J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010\'\u001a\u00020\u0011H\u0014J\b\u0010(\u001a\u00020\u0011H\u0016J\b\u0010)\u001a\u00020\u0011H\u0016J\u0018\u0010*\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0007H\u0016J\b\u0010+\u001a\u00020\u0011H\u0016J\b\u0010,\u001a\u00020\u0011H\u0016J\u0010\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u000bH\u0016J\b\u0010/\u001a\u00020\u0011H\u0002J\u0010\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u000bH\u0016J\u0012\u00102\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u00020\u0011H\u0002J\b\u00105\u001a\u00020\u0011H\u0016J\u0010\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u000bH\u0016J\u001a\u00108\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00072\b\u0010:\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010;\u001a\u00020\u0011H\u0016J\u0012\u0010<\u001a\u00020\u00112\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u0018\u0010?\u001a\u00020\u00112\u0006\u0010@\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0007H\u0016J\u001a\u0010A\u001a\u00020\u0011*\u00020B2\u0006\u0010C\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006E"}, d2 = {"Lid/kasirvippro/android/feature/manage/packages/main/PackagesActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/manage/packages/main/PackagesPresenter;", "Lid/kasirvippro/android/feature/manage/packages/main/PackagesContract$View;", "Lid/kasirvippro/android/feature/dialog/CartCountDialog$Listener;", "()V", "CODE_OPEN_CHOOSE_PRODUCT", "", "CODE_OPEN_CHOOSE_STORE", "CODE_OPEN_SCAN", "TAG", "", "kotlin.jvm.PlatformType", "adapter", "Lid/kasirvippro/android/feature/manage/packages/main/PackagesAdapter;", "codeOpenChooseProduct", "addCart", "", "data", "Lid/kasirvippro/android/models/cart/Cart;", "createLayout", "createPresenter", "deleteCart", "position", "onActivityResult", "requestCode", "resultCode", "Landroid/content/Intent;", "onCountSaved", "selected", "pos", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "openChooseProduct", "openChooseStore", "openCountDialog", "openHistoryKulakan", "openScanPage", "openSuccessPage", "id", "renderView", "setCartText", "nominal", "setStoreName", "Lid/kasirvippro/android/models/store/Store;", "setupToolbar", "showContentView", "showErrorView", "err", "showMessage", "code", "msg", "showTunaiView", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "updateCart", "cart", "inputFilterDecimal", "Landroid/widget/EditText;", "maxDigitsIncludingPoint", "maxDecimalPlaces", "app_debug"})
public final class PackagesActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.manage.packages.main.PackagesPresenter, id.kasirvippro.android.feature.manage.packages.main.PackagesContract.View> implements id.kasirvippro.android.feature.manage.packages.main.PackagesContract.View, id.kasirvippro.android.feature.dialog.CartCountDialog.Listener {
    private final java.lang.String TAG = null;
    private final int codeOpenChooseProduct = 1002;
    private final int CODE_OPEN_SCAN = 1001;
    private final int CODE_OPEN_CHOOSE_PRODUCT = 1002;
    private final int CODE_OPEN_CHOOSE_STORE = 1005;
    private final id.kasirvippro.android.feature.manage.packages.main.PackagesAdapter adapter = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.packages.main.PackagesPresenter createPresenter() {
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
    
    private final void setupToolbar() {
    }
    
    private final void renderView() {
    }
    
    public final void inputFilterDecimal(@org.jetbrains.annotations.NotNull()
    android.widget.EditText $this$inputFilterDecimal, int maxDigitsIncludingPoint, int maxDecimalPlaces) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void showMessage(int code, @org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void openScanPage() {
    }
    
    @java.lang.Override()
    public void openChooseProduct() {
    }
    
    @java.lang.Override()
    public void showContentView() {
    }
    
    @java.lang.Override()
    public void showErrorView(@org.jetbrains.annotations.NotNull()
    java.lang.String err) {
    }
    
    @java.lang.Override()
    public void openSuccessPage(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @java.lang.Override()
    public void setCartText(@org.jetbrains.annotations.NotNull()
    java.lang.String nominal) {
    }
    
    @java.lang.Override()
    public void addCart(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.cart.Cart data) {
    }
    
    @java.lang.Override()
    public void updateCart(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.cart.Cart cart, int position) {
    }
    
    @java.lang.Override()
    public void deleteCart(int position) {
    }
    
    @java.lang.Override()
    public void showTunaiView() {
    }
    
    @java.lang.Override()
    public void setStoreName(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.models.store.Store data) {
    }
    
    @java.lang.Override()
    public void openChooseStore() {
    }
    
    @java.lang.Override()
    public void openCountDialog(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.cart.Cart selected, int pos) {
    }
    
    @java.lang.Override()
    public void onCountSaved(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.models.cart.Cart selected, int pos) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void openHistoryKulakan() {
    }
    
    public PackagesActivity() {
        super();
    }
}