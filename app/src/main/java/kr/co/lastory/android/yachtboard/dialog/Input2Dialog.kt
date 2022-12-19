package kr.co.lastory.android.yachtboard.dialog

import android.app.Dialog
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.co.lastory.android.yachtboard.databinding.DialogInput2NumberBinding

class Input2Dialog(private val context : AppCompatActivity) {

    private lateinit var binding : DialogInput2NumberBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    private lateinit var listener : MyDialogClickedListener

    private lateinit var btnArray : Array<EditText>

    fun show() {
        binding = DialogInput2NumberBinding.inflate(context.layoutInflater)

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        btnArray = arrayOf(binding.etNumber1, binding.etNumber2, binding.etNumber3, binding.etNumber4, binding.etNumber5)


//        binding.content.text = content //부모 액티비티에서 전달 받은 텍스트 세팅

        //ok 버튼 동작
        binding.ok.setOnClickListener {
            var edit : EditText = btnArray[0]
            try {
                var sum = 0
                for(btn in btnArray){
                    edit = btn

                    val n = Integer.parseInt(edit.text.toString())
                    if( 1 > n || n > 6 ) throw Exception()
                    sum += n
                }
                listener.onOKClicked(sum)
                dlg.dismiss()
            }catch (e : Exception) {
                edit.requestFocus()
                edit.selectAll()

                Toast.makeText(context, "1에서 6사이의 숫자를 입력해주세요.", Toast.LENGTH_LONG).show()
            }
        }

        binding.cancel.setOnClickListener {
            listener.onCancelClicked()
            dlg.dismiss()
        }

        dlg.setCanceledOnTouchOutside(true)
        dlg.show()
    }

    fun setOnClickedListener(listener: (Int) -> Unit) {
        this.listener = object: MyDialogClickedListener {
            override fun onOKClicked(content: Int) {
                listener(content)
            }
            override fun onCancelClicked() {
                listener(0)
            }
        }
    }

    interface MyDialogClickedListener {
        fun onOKClicked(content : Int)
        fun onCancelClicked()
    }

}