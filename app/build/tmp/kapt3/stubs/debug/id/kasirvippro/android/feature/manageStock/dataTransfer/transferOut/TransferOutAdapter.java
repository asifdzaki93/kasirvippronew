package id.kasirvippro.android.feature.manageStock.dataTransfer.transferOut;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001d\u001eB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\u0005H\u0016J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005H\u0016J\u0016\u0010\u001b\u001a\u00020\u00112\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "()V", "DATA", "", "HEADER", "callback", "Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutAdapter$ItemClickCallback;", "getCallback", "()Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutAdapter$ItemClickCallback;", "setCallback", "(Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutAdapter$ItemClickCallback;)V", "listProduct", "", "Lid/kasirvippro/android/models/transaction/Transfer;", "clearAdapter", "", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setItems", "", "ItemClickCallback", "ViewHolder", "app_debug"})
public final class TransferOutAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    private final java.util.List<id.kasirvippro.android.models.transaction.Transfer> listProduct = null;
    private final int HEADER = 1;
    private final int DATA = 2;
    @org.jetbrains.annotations.Nullable()
    private id.kasirvippro.android.feature.manageStock.dataTransfer.transferOut.TransferOutAdapter.ItemClickCallback callback;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    public final void setItems(@org.jetbrains.annotations.Nullable()
    java.util.List<id.kasirvippro.android.models.transaction.Transfer> listProduct) {
    }
    
    public final void clearAdapter() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final id.kasirvippro.android.feature.manageStock.dataTransfer.transferOut.TransferOutAdapter.ItemClickCallback getCallback() {
        return null;
    }
    
    public final void setCallback(@org.jetbrains.annotations.Nullable()
    id.kasirvippro.android.feature.manageStock.dataTransfer.transferOut.TransferOutAdapter.ItemClickCallback p0) {
    }
    
    public TransferOutAdapter() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutAdapter;Landroid/view/View;)V", "headerDateTv", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "headerTotalTv", "idTv", "photoIv", "Landroid/widget/ImageView;", "statusTv", "storeTv", "totalTv", "bindData", "", "data", "Lid/kasirvippro/android/models/transaction/Transfer;", "type", "", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final android.widget.TextView headerDateTv = null;
        private final android.widget.TextView headerTotalTv = null;
        private final android.widget.ImageView photoIv = null;
        private final android.widget.TextView idTv = null;
        private final android.widget.TextView totalTv = null;
        private final android.widget.TextView statusTv = null;
        private final android.widget.TextView storeTv = null;
        
        @android.annotation.SuppressLint(value = {"SetTextI18n"})
        public final void bindData(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.transaction.Transfer data, int type) {
        }
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/manageStock/dataTransfer/transferOut/TransferOutAdapter$ItemClickCallback;", "", "onClick", "", "data", "Lid/kasirvippro/android/models/transaction/Transfer;", "app_debug"})
    public static abstract interface ItemClickCallback {
        
        public abstract void onClick(@org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.transaction.Transfer data);
    }
}