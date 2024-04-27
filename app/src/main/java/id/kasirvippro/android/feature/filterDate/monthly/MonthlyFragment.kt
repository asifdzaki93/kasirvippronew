package id.kasirvippro.android.feature.filterDate.monthly

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.base.BaseFragment
import id.kasirvippro.android.R
import id.kasirvippro.android.models.FilterDialogDate
import kotlinx.android.synthetic.main.fragment_filter_monthly.view.*
import id.kasirvippro.android.ui.GridItemOffsetDecoration


class MonthlyFragment : BaseFragment<MonthlyPresenter, MonthlyContract.View>(),
    MonthlyContract.View {

    private val adapter = MonthlyAdapter()
    private lateinit var _view: View
    private var listener:Listener?=null

    companion object {

        @JvmStatic
        fun newInstance() =
                MonthlyFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun createPresenter(): MonthlyPresenter {
        return MonthlyPresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_filter_monthly, container, false)
    }


    override fun initAction(view: View) {
        _view = view
        renderView()
        getPresenter()?.onViewCreated(activity!!.intent)
    }

    private fun renderView(){
        val spaceItem = resources.getDimensionPixelSize(R.dimen.standard_margin)
        _view.rv_list.addItemDecoration(GridItemOffsetDecoration(spaceItem))
        val layoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        _view.rv_list.layoutManager = layoutManager
        _view.rv_list.adapter = adapter

        adapter.callback = object : MonthlyAdapter.ItemClickCallback{
            override fun onClick(data: FilterDialogDate) {
                //EventBus.getDefault().post(onMenuClicked(data.id!!))
                listener?.onSelected(data)
            }
        }

        _view.btn_next.setOnClickListener {
            getPresenter()?.onNextYear()
        }

        _view.btn_prev.setOnClickListener {
            getPresenter()?.onPrevYear()
        }
    }

    override fun setYear(year: String) {
        _view.tv_year.text = year
    }

    override fun setList(list: List<FilterDialogDate>, selected: FilterDialogDate) {
        adapter.setItems(list,selected)
    }

    interface Listener {
        fun onSelected(data:FilterDialogDate?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement Listener")
        }
    }
}
