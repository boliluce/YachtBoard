package kr.co.lastory.android.yachtboard

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.co.lastory.android.yachtboard.common.MyApplication
import kr.co.lastory.android.yachtboard.data.TotalInfo

class MainViewModel(context: Context) : BaseViewModel() {

    private val topList = arrayOf( "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes" )
    private val bottomList = arrayOf( "Choice", "4 of a Kind", "Full House", "S.Straight", "L.Straight", "Yacht" )

    private val PREF_BONUS = "보너스"
    private val PREF_SUB_TOTAL = "상단 합계"
    private val PREF_TOTAL = "총합"

    private val totalInfo = TotalInfo()

    private val _totalResult = MutableLiveData<TotalInfo>()
    val totalResult: LiveData<TotalInfo> = _totalResult

    fun initTotal(){
        totalInfo.bonus = MyApplication.prefs.getInt(PREF_BONUS, 0)
        totalInfo.subTotal = MyApplication.prefs.getInt(PREF_SUB_TOTAL, 0)
        totalInfo.total = MyApplication.prefs.getInt(PREF_TOTAL, 0)
    }

    fun savedScore(key : String, value : Int){
        MyApplication.prefs.setInt(key, value)

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
}