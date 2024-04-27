package id.kasirvippro.android.feature.etalase.kelolatoko.edit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lid/kasirvippro/android/feature/etalase/kelolatoko/edit/kelolatokoEditContract;", "", "Interactor", "InteractorOutput", "Presenter", "View", "app_debug"})
public abstract interface kelolatokoEditContract {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\r\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0011\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0012\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u001a\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0017"}, d2 = {"Lid/kasirvippro/android/feature/etalase/kelolatoko/edit/kelolatokoEditContract$View;", "Lid/kasirvippro/android/base/BaseViewImpl;", "loadPhoto", "", "path", "", "onClose", "status", "", "openChooseColor", "openImageChooser", "setAdress", "value", "setColor", "setFb", "setIg", "setJam", "setNowa", "setStoreName", "setSubdomain", "showMessage", "code", "msg", "app_debug"})
    public static abstract interface View extends id.kasirvippro.android.base.BaseViewImpl {
        
        public abstract void showMessage(int code, @org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onClose(int status);
        
        public abstract void openChooseColor();
        
        public abstract void loadPhoto(@org.jetbrains.annotations.NotNull()
        java.lang.String path);
        
        public abstract void setStoreName(@org.jetbrains.annotations.Nullable()
        java.lang.String value);
        
        public abstract void setSubdomain(@org.jetbrains.annotations.Nullable()
        java.lang.String value);
        
        public abstract void setColor(@org.jetbrains.annotations.Nullable()
        java.lang.String value);
        
        public abstract void setAdress(@org.jetbrains.annotations.Nullable()
        java.lang.String value);
        
        public abstract void setIg(@org.jetbrains.annotations.Nullable()
        java.lang.String value);
        
        public abstract void setFb(@org.jetbrains.annotations.Nullable()
        java.lang.String value);
        
        public abstract void setNowa(@org.jetbrains.annotations.Nullable()
        java.lang.String value);
        
        public abstract void setJam(@org.jetbrains.annotations.Nullable()
        java.lang.String value);
        
        public abstract void openImageChooser();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001JH\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H&J\b\u0010\u000e\u001a\u00020\u0004H&J\b\u0010\u000f\u001a\u00020\u0004H&J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0012\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006H&\u00a8\u0006\u0015"}, d2 = {"Lid/kasirvippro/android/feature/etalase/kelolatoko/edit/kelolatokoEditContract$Presenter;", "Lid/kasirvippro/android/base/BasePresenterImpl;", "Lid/kasirvippro/android/feature/etalase/kelolatoko/edit/kelolatokoEditContract$View;", "onCheck", "", "nama_toko", "", "alamat_toko", "warna_toko", "jam_operasional", "linkfb", "linkinstagram", "nowa", "subdomain", "onCheckPhoto", "onDestroy", "onViewCreated", "intent", "Landroid/content/Intent;", "setImagePhotoPath", "path", "app_debug"})
    public static abstract interface Presenter extends id.kasirvippro.android.base.BasePresenterImpl<id.kasirvippro.android.feature.etalase.kelolatoko.edit.kelolatokoEditContract.View> {
        
        public abstract void onCheckPhoto();
        
        public abstract void setImagePhotoPath(@org.jetbrains.annotations.Nullable()
        java.lang.String path);
        
        public abstract void onViewCreated(@org.jetbrains.annotations.NotNull()
        android.content.Intent intent);
        
        public abstract void onDestroy();
        
        public abstract void onCheck(@org.jetbrains.annotations.NotNull()
        java.lang.String nama_toko, @org.jetbrains.annotations.NotNull()
        java.lang.String alamat_toko, @org.jetbrains.annotations.NotNull()
        java.lang.String warna_toko, @org.jetbrains.annotations.NotNull()
        java.lang.String jam_operasional, @org.jetbrains.annotations.NotNull()
        java.lang.String linkfb, @org.jetbrains.annotations.NotNull()
        java.lang.String linkinstagram, @org.jetbrains.annotations.NotNull()
        java.lang.String nowa, @org.jetbrains.annotations.NotNull()
        java.lang.String subdomain);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\bf\u0018\u00002\u00020\u0001Jj\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\tH&J\b\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u0003H&\u00a8\u0006\u0015"}, d2 = {"Lid/kasirvippro/android/feature/etalase/kelolatoko/edit/kelolatokoEditContract$Interactor;", "Lid/kasirvippro/android/base/BaseInteractorImpl;", "callEditAPI", "", "context", "Landroid/content/Context;", "model", "Lid/kasirvippro/android/models/kelolatoko/KelolatokoRestModel;", "id", "", "nama_toko", "alamat_toko", "warna_toko", "jam_operasional", "linkfb", "linkinstagram", "nowa", "subdomain", "img", "onDestroy", "onRestartDisposable", "app_debug"})
    public static abstract interface Interactor extends id.kasirvippro.android.base.BaseInteractorImpl {
        
        public abstract void onDestroy();
        
        public abstract void onRestartDisposable();
        
        public abstract void callEditAPI(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        id.kasirvippro.android.models.kelolatoko.KelolatokoRestModel model, @org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String nama_toko, @org.jetbrains.annotations.NotNull()
        java.lang.String alamat_toko, @org.jetbrains.annotations.NotNull()
        java.lang.String warna_toko, @org.jetbrains.annotations.NotNull()
        java.lang.String jam_operasional, @org.jetbrains.annotations.NotNull()
        java.lang.String linkfb, @org.jetbrains.annotations.NotNull()
        java.lang.String linkinstagram, @org.jetbrains.annotations.NotNull()
        java.lang.String nowa, @org.jetbrains.annotations.NotNull()
        java.lang.String subdomain, @org.jetbrains.annotations.Nullable()
        java.lang.String img);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&\u00a8\u0006\t"}, d2 = {"Lid/kasirvippro/android/feature/etalase/kelolatoko/edit/kelolatokoEditContract$InteractorOutput;", "Lid/kasirvippro/android/base/BaseInteractorOutputImpl;", "onFailedAPI", "", "code", "", "msg", "", "onSuccessEdit", "app_debug"})
    public static abstract interface InteractorOutput extends id.kasirvippro.android.base.BaseInteractorOutputImpl {
        
        public abstract void onSuccessEdit(@org.jetbrains.annotations.Nullable()
        java.lang.String msg);
        
        public abstract void onFailedAPI(int code, @org.jetbrains.annotations.NotNull()
        java.lang.String msg);
    }
}