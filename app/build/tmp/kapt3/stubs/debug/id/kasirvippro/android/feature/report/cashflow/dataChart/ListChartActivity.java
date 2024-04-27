package id.kasirvippro.android.feature.report.cashFLow.dataChart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020\u0002H\u0016J\b\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u000209H\u0002J\u0010\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0016J\b\u0010?\u001a\u000209H\u0014J\b\u0010@\u001a\u000209H\u0002J\b\u0010A\u001a\u000209H\u0002J\u0012\u0010B\u001a\u0002092\b\u0010C\u001a\u0004\u0018\u00010DH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R*\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0018j\b\u0012\u0004\u0012\u00020\u0019`\u001aX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R*\u00101\u001a\u0012\u0012\u0004\u0012\u0002020\u0018j\b\u0012\u0004\u0012\u000202`\u001aX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001c\"\u0004\b4\u0010\u001e\u00a8\u0006E"}, d2 = {"Lid/kasirvippro/android/feature/report/cashFLow/dataChart/ListChartActivity;", "Lid/kasirvippro/android/base/BaseActivity;", "Lid/kasirvippro/android/feature/report/cashFLow/dataChart/ListChartPresenter;", "Lid/kasirvippro/android/feature/report/cashFLow/dataChart/ListChartContract$View;", "()V", "barChart", "Lcom/github/mikephil/charting/charts/BarChart;", "getBarChart", "()Lcom/github/mikephil/charting/charts/BarChart;", "setBarChart", "(Lcom/github/mikephil/charting/charts/BarChart;)V", "barData", "Lcom/github/mikephil/charting/data/BarData;", "getBarData", "()Lcom/github/mikephil/charting/data/BarData;", "setBarData", "(Lcom/github/mikephil/charting/data/BarData;)V", "barDataSet", "Lcom/github/mikephil/charting/data/BarDataSet;", "getBarDataSet", "()Lcom/github/mikephil/charting/data/BarDataSet;", "setBarDataSet", "(Lcom/github/mikephil/charting/data/BarDataSet;)V", "barEntriesList", "Ljava/util/ArrayList;", "Lcom/github/mikephil/charting/data/BarEntry;", "Lkotlin/collections/ArrayList;", "getBarEntriesList", "()Ljava/util/ArrayList;", "setBarEntriesList", "(Ljava/util/ArrayList;)V", "lineChart", "Lcom/github/mikephil/charting/charts/LineChart;", "getLineChart", "()Lcom/github/mikephil/charting/charts/LineChart;", "setLineChart", "(Lcom/github/mikephil/charting/charts/LineChart;)V", "lineData", "Lcom/github/mikephil/charting/data/LineData;", "getLineData", "()Lcom/github/mikephil/charting/data/LineData;", "setLineData", "(Lcom/github/mikephil/charting/data/LineData;)V", "lineDataSet", "Lcom/github/mikephil/charting/data/LineDataSet;", "getLineDataSet", "()Lcom/github/mikephil/charting/data/LineDataSet;", "setLineDataSet", "(Lcom/github/mikephil/charting/data/LineDataSet;)V", "lineEntriesList", "Lcom/github/mikephil/charting/data/Entry;", "getLineEntriesList", "setLineEntriesList", "createLayout", "", "createPresenter", "getBarChartData", "", "getLineChartData", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "renderView", "setupToolbar", "startingUpActivity", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class ListChartActivity extends id.kasirvippro.android.base.BaseActivity<id.kasirvippro.android.feature.report.cashFLow.dataChart.ListChartPresenter, id.kasirvippro.android.feature.report.cashFLow.dataChart.ListChartContract.View> implements id.kasirvippro.android.feature.report.cashFLow.dataChart.ListChartContract.View {
    public com.github.mikephil.charting.charts.BarChart barChart;
    public com.github.mikephil.charting.charts.LineChart lineChart;
    public com.github.mikephil.charting.data.BarData barData;
    public com.github.mikephil.charting.data.LineData lineData;
    public com.github.mikephil.charting.data.BarDataSet barDataSet;
    public com.github.mikephil.charting.data.LineDataSet lineDataSet;
    public java.util.ArrayList<com.github.mikephil.charting.data.BarEntry> barEntriesList;
    public java.util.ArrayList<com.github.mikephil.charting.data.Entry> lineEntriesList;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.mikephil.charting.charts.BarChart getBarChart() {
        return null;
    }
    
    public final void setBarChart(@org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.charts.BarChart p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.mikephil.charting.charts.LineChart getLineChart() {
        return null;
    }
    
    public final void setLineChart(@org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.charts.LineChart p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.mikephil.charting.data.BarData getBarData() {
        return null;
    }
    
    public final void setBarData(@org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.data.BarData p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.mikephil.charting.data.LineData getLineData() {
        return null;
    }
    
    public final void setLineData(@org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.data.LineData p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.mikephil.charting.data.BarDataSet getBarDataSet() {
        return null;
    }
    
    public final void setBarDataSet(@org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.data.BarDataSet p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.mikephil.charting.data.LineDataSet getLineDataSet() {
        return null;
    }
    
    public final void setLineDataSet(@org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.data.LineDataSet p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.github.mikephil.charting.data.BarEntry> getBarEntriesList() {
        return null;
    }
    
    public final void setBarEntriesList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.github.mikephil.charting.data.BarEntry> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.github.mikephil.charting.data.Entry> getLineEntriesList() {
        return null;
    }
    
    public final void setLineEntriesList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.github.mikephil.charting.data.Entry> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public id.kasirvippro.android.feature.report.cashFLow.dataChart.ListChartPresenter createPresenter() {
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
    
    private final void getBarChartData() {
    }
    
    private final void getLineChartData() {
    }
    
    private final void setupToolbar() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    public ListChartActivity() {
        super();
    }
}