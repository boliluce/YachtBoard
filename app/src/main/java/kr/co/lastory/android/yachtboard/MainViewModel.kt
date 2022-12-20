package kr.co.lastory.android.yachtboard

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.co.lastory.android.yachtboard.common.MyApplication
import kr.co.lastory.android.yachtboard.data.TotalInfo

class MainViewModel(context: Context) : BaseViewModel() {

    private val topList = arrayOf(
        context.resources.getString(R.string.title_description_ones),
        context.resources.getString(R.string.title_description_twos),
        context.resources.getString(R.string.title_description_threes),
        context.resources.getString(R.string.title_description_fours),
        context.resources.getString(R.string.title_description_fives),
        context.resources.getString(R.string.title_description_sixes)
    )

    private val PREF_BONUS = context.resources.getString(R.string.title_description_bonus)
    private val PREF_SUB_TOTAL = context.resources.getString(R.string.title_description_sub_total)
    private val PREF_TOTAL = context.resources.getString(R.string.title_description_total)

    private val totalInfo = TotalInfo()

    private val _totalResult = MutableLiveData<TotalInfo>()
    val totalResult: LiveData<TotalInfo> = _totalResult

    fun initTotal(){
        totalInfo.bonus = MyApplication.prefs.getInt(PREF_BONUS, 0)
        totalInfo.subTotal = MyApplication.prefs.getInt(PREF_SUB_TOTAL, 0)
        totalInfo.total = MyApplication.prefs.getInt(PREF_TOTAL, 0)
    }

    fun savedScore(key : String, value : Int){
        if(value > 0)
            MyApplication.prefs.setInt(key, value)
        else
            MyApplication.prefs.setInt(key, -1)

        if(topList.contains(key)) {
            totalInfo.subTotal += value

            if(totalInfo.bonus == 0 && totalInfo.subTotal >= 63) {
                totalInfo.bonus += 35
                totalInfo.total += totalInfo.bonus
            }else if (totalInfo.bonus != 0 && totalInfo.subTotal < 63) {
                totalInfo.bonus -= 35
                totalInfo.total -= 35
            }
        }
        totalInfo.total += value

        MyApplication.prefs.setInt(PREF_BONUS, totalInfo.bonus)
        MyApplication.prefs.setInt(PREF_SUB_TOTAL, totalInfo.subTotal)
        MyApplication.prefs.setInt(PREF_TOTAL, totalInfo.total)

        _totalResult.postValue(totalInfo)
    }

    fun savedInputDialogType(value : String){
        MyApplication.prefs.setString(MyApplication.PREF_INPUT_DIALOG_TYPE, value)
    }
}