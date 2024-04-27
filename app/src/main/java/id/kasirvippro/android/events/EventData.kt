package id.kasirvippro.android.events

import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.models.DialogModel

data class onMenuClicked(val id: Int)
data class onCode(val id: String)
data class onReloadTransaction(val isReload:Boolean)
data class onReloadSpendTransaction(val isReload:Boolean)
data class onHistoryChangedDate(val firstDate: CalendarDay?,val lastDate: CalendarDay?)
data class onHistoryChangedStatus(val selected: DialogModel?)