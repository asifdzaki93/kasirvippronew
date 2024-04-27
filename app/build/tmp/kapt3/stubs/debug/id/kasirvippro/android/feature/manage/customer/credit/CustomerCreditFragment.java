package id.kasirvippro.android.feature.manage.customer.credit;

import java.lang.System;

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ContentFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 )2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0014J$\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0011H\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0006H\u0016J\b\u0010 \u001a\u00020\u0011H\u0002J\u0016\u0010!\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0016J\u0018\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006*"}, d2 = {"Lid/kasirvippro/android/feature/manage/customer/credit/CustomerCreditFragment;", "Lid/kasirvippro/android/base/BaseFragment;", "Lid/kasirvippro/android/feature/manage/customer/credit/CustomerCreditPresenter;", "Lid/kasirvippro/android/feature/manage/customer/credit/CustomerCreditContract$View;", "()V", "ARGUMENT_PARAM", "", "TAG", "kotlin.jvm.PlatformType", "_view", "Landroid/view/View;", "adapter", "Lid/kasirvippro/android/feature/manage/customer/credit/CustomerCreditAdapter;", "getAdapter", "()Lid/kasirvippro/android/feature/manage/customer/credit/CustomerCreditAdapter;", "createPresenter", "initAction", "", "view", "onCreateLayout", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onEvent", "event", "Lid/kasirvippro/android/events/onReloadTransaction;", "openDetail", "id", "renderView", "setData", "list", "", "Lid/kasirvippro/android/models/transaction/Transaction;", "showErrorMessage", "code", "", "msg", "Companion", "app_debug"})
public final class CustomerCreditFragment extends id.kasirvippro.android.base.BaseFragment<id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditPresenter, id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditContract.View> implements id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditContract.View {
    private final java.lang.String TAG = null;
    private final java.lang.String ARGUMENT_PARAM = "ARGUMENT_PARAM";
    private android.view.View _view;
    @org.jetbrains.annotations.NotNull()
    private final id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditAdapter adapter = null;
    @org.jetbrains.annotations.NotNull()
    public static final id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditAdapter getAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditPresenter createPresenter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected android.view.View onCreateLayout(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    protected void initAction(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void renderView() {
    }
    
    @java.lang.Override()
    public void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<id.kasirvippro.android.models.transaction.Transaction> list) {
    }
    
    @java.lang.Override()
    public void showErrorMessage(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    @java.lang.Override()
    public void openDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @org.greenrobot.eventbus.Subscribe()
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    id.kasirvippro.android.events.onReloadTransaction event) {
    }
    
    public CustomerCreditFragment() {
        super();
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param intros ArrayList Intro.
     * @return A new instance of fragment ContentFragment.
     */
    @org.jetbrains.annotations.NotNull()
    public static final id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditFragment newInstance(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.models.customer.Customer data) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lid/kasirvippro/android/feature/manage/customer/credit/CustomerCreditFragment$Companion;", "", "()V", "newInstance", "Lid/kasirvippro/android/feature/manage/customer/credit/CustomerCreditFragment;", "data", "Lid/kasirvippro/android/models/customer/Customer;", "app_debug"})
    public static final class Companion {
        
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param intros ArrayList Intro.
         * @return A new instance of fragment ContentFragment.
         */
        @org.jetbrains.annotations.NotNull()
        public final id.kasirvippro.android.feature.manage.customer.credit.CustomerCreditFragment newInstance(@org.jetbrains.annotations.Nullable()
        id.kasirvippro.android.models.customer.Customer data) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}