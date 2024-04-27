package id.kasirvippro.android.feature.hutangpiutang.piutang

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.base.BaseFragment
import id.kasirvippro.android.R
import id.kasirvippro.android.events.onReloadTransaction
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.models.hutangpiutang.Piutang
import kotlinx.android.synthetic.main.fragment_piutang.view.*
import id.kasirvippro.android.feature.hutangpiutang.lastPiutang.LastPiutangActivity
import id.kasirvippro.android.feature.hutangpiutang.piutangCustomer.PiutangCustomerActivity
import id.kasirvippro.android.feature.transaction.detail.DetailActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ContentFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */

class PiutangFragment : BaseFragment<PiutangPresenter, PiutangContract.View>(),
    PiutangContract.View {

    private val TAG = PiutangFragment::class.java.simpleName

    private val ARGUMENT_PARAM = "ARGUMENT_PARAM"

    private lateinit var _view: View
    val adapter = PiutangAdapter()


    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param intros ArrayList Intro.
         * @return A new instance of fragment ContentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                PiutangFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun createPresenter(): PiutangPresenter {
        return PiutangPresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_piutang, container, false)
    }


    override fun initAction(view: View) {
        EventBus.getDefault().register(this)
        _view = view
        renderView()
        _view.sw_refresh.isRefreshing = true
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        _view.sw_refresh.setOnRefreshListener {
            _view.sw_refresh.isRefreshing = true
            adapter.clearAdapter()
            getPresenter()?.loadHutang()
        }
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        _view.rv_list.layoutManager = layoutManager
        _view.rv_list.adapter = adapter
        adapter.limit = AppConstant.LIMIT_HUTANG_PIUTANG
        adapter.callback = object : PiutangAdapter.ItemClickCallback{
            override fun onClick(data: Piutang.Data) {
                data?.let {
                    openDetailStruk(it.no_invoice!!)
                }
            }
        }

        _view.btn_see_all.setOnClickListener {
            openLastPiutangPage()
        }

        _view.btn_detail_supplier.setOnClickListener {
            openPiutangPage()
        }
    }

    override fun setList(list: List<Piutang.Data>) {
        _view.sw_refresh.isRefreshing = false
        _view.rv_list.visibility = View.VISIBLE
        _view.tv_error.visibility = View.GONE
        adapter.setItems(list)
    }

    override fun showErrorMessage(code: Int, msg: String) {
        _view.sw_refresh.isRefreshing = false
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                _view.rv_list.visibility = View.GONE
                _view.tv_error.visibility = View.VISIBLE
                _view.tv_error.text = msg
            }
        }



    }

    override fun setInfo(sum: String, sumRupiah: String, jatuhTempo: String, belumLunas: String) {
        _view.sw_refresh.isRefreshing = false
        _view.tv_sum.text = sum
        _view.tv_sum_rp.text = sumRupiah
        _view.tv_jatuh_tempo.text = jatuhTempo
        _view.tv_belum_lunas.text = belumLunas
    }

    override fun openLastPiutangPage() {
        val intent = Intent(activity,LastPiutangActivity::class.java)
        startActivity(intent)
    }

    override fun openPiutangPage() {
        val intent = Intent(activity,PiutangCustomerActivity::class.java)
        startActivity(intent)
    }

    override fun onDetach() {
        super.onDetach()
        getPresenter()?.onDestroy()
        EventBus.getDefault().unregister(this)

    }

    override fun openDetailStruk(id: String) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
    }

    @Subscribe
    fun onEvent(event: onReloadTransaction){
        if(event.isReload){
            _view.sw_refresh.isRefreshing = true
            adapter.clearAdapter()
            getPresenter()?.loadHutang()
        }
    }





}
