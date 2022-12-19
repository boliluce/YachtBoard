package kr.co.lastory.android.yachtboard.dialog

import android.app.Dialog
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import kr.co.lastory.android.yachtboard.R
import kr.co.lastory.android.yachtboard.databinding.DialogSelectNumberBinding
import kotlin.properties.Delegates

class SelectDialog(private val context : AppCompatActivity) {

    private lateinit var binding : DialogSelectNumberBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    private lateinit var listener : MyDialogOKClickedListener

    private var num by Delegates.notNull<Int>()
    private var selectedNumber by Delegates.notNull<Int>()

    fun show(num : Int) {
        binding = DialogSelectNumberBinding.inflate(context.layoutInflater)

        this.num = num
        selectedNumber = 0

        binding.btnNumber0.text = "${num * 0}점"
        binding.btnNumber1.text = "${num * 1}점"
        binding.btnNumber2.text = "${num * 2}점"
        binding.btnNumber3.text = "${num * 3}점"
        binding.btnNumber4.text = "${num * 4}점"
        binding.btnNumber5.text = "${num * 5}점"

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

//        binding.content.text = content //부모 액티비티에서 전달 받은 텍스트 세팅

        //ok 버튼 동작
        binding.ok.setOnClickListener {
            listener.onOKClicked(selectedNumber)
            dlg.dismiss()
        }

        //cancel 버튼 동작
        binding.cancel.setOnClickListener {
            dlg.dismiss()
        }

        binding.btnNumber0.setOnClickListener { selectedNumber = 0 }
        binding.btnNumber1.setOnClickListener { selectedNumber = num * 1 }
        binding.btnNumber2.setOnClickListener { selectedNumber = num * 2 }
        binding.btnNumber3.setOnClickListener { selectedNumber = num * 3 }
        binding.btnNumber4.setOnClickListener { selectedNumber = num * 4 }
        binding.btnNumber5.setOnClickListener { selectedNumber = num * 5 }

        dlg.show()
    }



    fun setOnOKClickedListener(listener: (Int) -> Unit) {
        this.listener = object: MyDialogOKClickedListener {
            override fun onOKClicked(content: Int) {
                listener(content)
            }
        }
    }

    interface MyDialogOKClickedListener {
        fun onOKClicked(content : Int)
    }
}