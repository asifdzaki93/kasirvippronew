package id.kasirvippro.android.feature.report.cashFLow.dataChart

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.transaction.FlowCash
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.fragment_report_chart.*


class ListChartActivity : BaseActivity<ListChartPresenter, ListChartContract.View>(),
    ListChartContract.View {

    lateinit var barChart: BarChart
    lateinit var lineChart: LineChart
    lateinit var barData: BarData
    lateinit var lineData: LineData
    lateinit var barDataSet: BarDataSet
    lateinit var lineDataSet: LineDataSet
    lateinit var barEntriesList: ArrayList<BarEntry>
    lateinit var lineEntriesList: ArrayList<Entry>

    override fun createPresenter(): ListChartPresenter {
        return ListChartPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_report_chart
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }


    private fun renderView(){

        barChart = findViewById(R.id.chart)
        getBarChartData()
        barDataSet = BarDataSet(barEntriesList, "Cash Flow Report")
        barData = BarData(barDataSet)
        barChart.data = barData
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f
       // barDataSet.color = resources.getColor(R.color.colorAccent)
        val colors = ArrayList<Int>()
        colors.add(Color.rgb(156, 80, 182))
        colors.add(Color.rgb(255, 0, 0))
        colors.add(Color.rgb(210, 105, 30))
        colors.add(Color.rgb(0, 0, 255))
        barDataSet.setColors(colors)


        chart.animateY(5000)
        barChart.description.isEnabled = false
        val xAxis = barChart.xAxis
        val labels = arrayOf(
            "Selling", "Return", "Purchase", "Spending"
        )
        xAxis.setGranularity(1f)
        xAxis.setGranularityEnabled(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        barChart.setDragEnabled(true)
        barChart.setVisibleXRangeMaximum(5f)




        lineChart = findViewById(R.id.lineChart)
        getLineChartData()
        lineDataSet = LineDataSet(lineEntriesList, "Cash Flow Report")
        lineData = LineData(lineDataSet)
        lineChart.data = lineData
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = 16f
        lineDataSet.color = resources.getColor(R.color.colorAccent)
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.animateXY(100, 500)
        lineChart.description.isEnabled = false


    }

    private fun getBarChartData() {
        val date = getIntent().getStringExtra("date")

       val data = intent.getSerializableExtra(AppConstant.DATA) as FlowCash
       val daye = data.date
       val totalsales = data.totalsales
       val totalreturn = data.totalreturn
       val totalpurchase = data.totalpurchase
       val totalspend = data.totalspend
       val totalincome = data.money

        et_date.text = Helper.getDateFormat(this, daye.toString(), "yyyy-MM-dd", "EEEE, dd MMMM yyyy")


        barEntriesList = ArrayList()
        barEntriesList.add(BarEntry(0f, totalsales!!.toFloat()))
        barEntriesList.add(BarEntry(1f,  totalreturn!!.toFloat()))
        barEntriesList.add(BarEntry(2f, totalpurchase!!.toFloat()))
        barEntriesList.add(BarEntry(3f, totalspend!!.toFloat()))

    }

    private fun getLineChartData() {

       val data = intent.getSerializableExtra(AppConstant.DATA) as FlowCash
       val totalsales = data.totalsales
       val totalreturn = data.totalreturn
       val totalpurchase = data.totalpurchase
       val totalspend = data.totalspend


        lineEntriesList = ArrayList()
        lineEntriesList.add(Entry(0f, totalsales!!.toFloat()))
        lineEntriesList.add(Entry(1f,  totalreturn!!.toFloat()))
        lineEntriesList.add(Entry(2f, totalpurchase!!.toFloat()))
        lineEntriesList.add(Entry(3f, totalspend!!.toFloat()))

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_report)
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }


}
