package id.kasirvippro.android.feature.report.absent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.slip.Absent
import kotlinx.android.synthetic.main.activity_absent.*


class AbsentActivity : BaseActivity<AbsentPresenter, AbsentContract.View>(),
    AbsentContract.View {

    val adapter = AbsentAdapter()

    override fun createPresenter(): AbsentPresenter {
        return AbsentPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_absent
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        adapter.callback = object : AbsentAdapter.ItemClickCallback{
            override fun onClick(data: Absent) {
                onSelected(data)
            }
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Attendance"
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun setInfo(name:String?,date:String) {
        var text = date
        name?.let {
            text = "$it - $date"
        }

        tv_info.text = text
    }

    override fun setList(list: List<Absent>) {
        adapter.setItems(list)
    }

    override fun onSelected(data: Absent) {
        val uri = "http://maps.google.com/maps?daddr=${data.location}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }

}
