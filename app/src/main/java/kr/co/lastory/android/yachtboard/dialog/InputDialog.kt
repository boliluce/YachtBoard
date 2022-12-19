package kr.co.lastory.android.yachtboard.dialog

import android.app.Dialog
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.co.lastory.android.yachtboard.R
import kr.co.lastory.android.yachtboard.databinding.DialogInputNumberBinding

class InputDialog(private val context : AppCompatActivity) {

    private lateinit var binding : DialogInputNumberBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    private lateinit var listener : MyDialogOKClickedListener

    fun show() {
        binding = DialogInputNumberBinding.inflate(context.layoutInflater)

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

//        binding.content.text = content //부모 액티비티에서 전달 받은 텍스트 세팅

        //ok 버튼 동작
        binding.ok.setOnClickListener {
            try {
                val n = Integer.parseInt(binding.etNumber.text.toString())

                if( n != 0 && ( 5 > n || n > 36 ) ) throw Exception()

                listener.onOKClicked(n)
                dlg.dismiss()
            }catch (e : Exception) {
                binding.etNumber.requestFocus()
                binding.etNumber.selectAll()

                Toast.makeText(context, "0또는 5에서 36사이의 숫자를 입력해주세요.", Toast.LENGTH_LONG).show()
            }
        }

        //cancel 버튼 동작
        binding.cancel.setOnClickListener {
            dlg.dismiss()
        }

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