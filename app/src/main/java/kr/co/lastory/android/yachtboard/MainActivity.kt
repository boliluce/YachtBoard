package kr.co.lastory.android.yachtboard

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import kr.co.lastory.android.yachtboard.common.MyApplication
import kr.co.lastory.android.yachtboard.databinding.ActivityMainBinding
import kr.co.lastory.android.yachtboard.databinding.LayoutScoreItemBinding
import kr.co.lastory.android.yachtboard.databinding.LayoutScoreTotalItemBinding
import kr.co.lastory.android.yachtboard.dialog.Input1Dialog
import kr.co.lastory.android.yachtboard.dialog.SelectDialog
import kr.co.lastory.android.yachtboard.dialog.Input2Dialog


class MainActivity : BaseActivity() {
    override val viewModel by viewModels<MainViewModel>() { ViewModelFactory(this) }
    private lateinit var binding: ActivityMainBinding

    enum class DialogType {
        Input, Select
    }

    private lateinit var inputDialogType : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputDialogType = arrayOf("직접 입력 방식", "계산기 방식")

        supportActionBar?.title = "점수판"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.scoreTwos.ivDice1.setImageResource(R.drawable.ic_dice_two)
        binding.scoreTwos.ivDice2.setImageResource(R.drawable.ic_dice_two)
        binding.scoreTwos.ivDice3.setImageResource(R.drawable.ic_dice_two)
        binding.scoreTwos.ivDice4.setImageResource(R.drawable.ic_dice_two)
        binding.scoreTwos.ivDice5.setImageResource(R.drawable.ic_dice_two)
        binding.scoreTwos.tvDescriptionTitle.text = "Twos"
        binding.scoreTwos.tvDescriptionContents.text = "2가 나온 주사위 눈의 합"

        binding.scoreThrees.ivDice1.setImageResource(R.drawable.ic_dice_three)
        binding.scoreThrees.ivDice2.setImageResource(R.drawable.ic_dice_three)
        binding.scoreThrees.ivDice3.setImageResource(R.drawable.ic_dice_three)
        binding.scoreThrees.ivDice4.setImageResource(R.drawable.ic_dice_three)
        binding.scoreThrees.ivDice5.setImageResource(R.drawable.ic_dice_three)
        binding.scoreThrees.tvDescriptionTitle.text = "Threes"
        binding.scoreThrees.tvDescriptionContents.text = "3이 나온 주사위 눈의 합"

        binding.scoreFours.ivDice1.setImageResource(R.drawable.ic_dice_four)
        binding.scoreFours.ivDice2.setImageResource(R.drawable.ic_dice_four)
        binding.scoreFours.ivDice3.setImageResource(R.drawable.ic_dice_four)
        binding.scoreFours.ivDice4.setImageResource(R.drawable.ic_dice_four)
        binding.scoreFours.ivDice5.setImageResource(R.drawable.ic_dice_four)
        binding.scoreFours.tvDescriptionTitle.text = "Fours"
        binding.scoreFours.tvDescriptionContents.text = "4가 나온 주사위 눈의 합"

        binding.scoreFives.ivDice1.setImageResource(R.drawable.ic_dice_five)
        binding.scoreFives.ivDice2.setImageResource(R.drawable.ic_dice_five)
        binding.scoreFives.ivDice3.setImageResource(R.drawable.ic_dice_five)
        binding.scoreFives.ivDice4.setImageResource(R.drawable.ic_dice_five)
        binding.scoreFives.ivDice5.setImageResource(R.drawable.ic_dice_five)
        binding.scoreFives.tvDescriptionTitle .text = "Fives"
        binding.scoreFives.tvDescriptionContents.text = "5가 나온 주사위 눈의 합"

        binding.scoreSixes.ivDice1.setImageResource(R.drawable.ic_dice_six)
        binding.scoreSixes.ivDice2.setImageResource(R.drawable.ic_dice_six)
        binding.scoreSixes.ivDice3.setImageResource(R.drawable.ic_dice_six)
        binding.scoreSixes.ivDice4.setImageResource(R.drawable.ic_dice_six)
        binding.scoreSixes.ivDice5.setImageResource(R.drawable.ic_dice_six)
        binding.scoreSixes.tvDescriptionTitle .text = "Sixes"
        binding.scoreSixes.tvDescriptionContents.text = "6이 나온 주사위 눈의 합"

        binding.scoreBonus.tvDescriptionTitle.text = "보너스"
        binding.scoreBonus.tvDescriptionContents.text = "상단 항목의 합계가 63점 이상일 때, 35점"

        binding.scoreSubTotal.tvDescriptionTitle.text = "상단 합계"

        binding.scoreChoice.ivDice1.setImageResource(R.drawable.ic_dice_four)
        binding.scoreChoice.ivDice2.setImageResource(R.drawable.ic_dice_four)
        binding.scoreChoice.ivDice3.setImageResource(R.drawable.ic_dice_four)
        binding.scoreChoice.ivDice4.setImageResource(R.drawable.ic_dice_five)
        binding.scoreChoice.ivDice5.setImageResource(R.drawable.ic_dice_six)
        binding.scoreChoice.tvDescriptionTitle.text = "Choice"
        binding.scoreChoice.tvDescriptionContents.text = "주사위 5개 눈의 총 합"

        binding.score4OfAKind.ivDice1.setImageResource(R.drawable.ic_dice_three)
        binding.score4OfAKind.ivDice2.setImageResource(R.drawable.ic_dice_three)
        binding.score4OfAKind.ivDice3.setImageResource(R.drawable.ic_dice_three)
        binding.score4OfAKind.ivDice4.setImageResource(R.drawable.ic_dice_three)
        binding.score4OfAKind.ivDice5.setImageResource(R.drawable.ic_dice_five)
        binding.score4OfAKind.tvDescriptionTitle.text = "4 of a Kind"
        binding.score4OfAKind.tvDescriptionContents.text = "주사위 4개 눈이 같을 때, 주사위 5개의 합"

        binding.scoreFullHouse.ivDice1.setImageResource(R.drawable.ic_dice_three)
        binding.scoreFullHouse.ivDice2.setImageResource(R.drawable.ic_dice_three)
        binding.scoreFullHouse.ivDice3.setImageResource(R.drawable.ic_dice_three)
        binding.scoreFullHouse.ivDice4.setImageResource(R.drawable.ic_dice_five)
        binding.scoreFullHouse.ivDice5.setImageResource(R.drawable.ic_dice_five)
        binding.scoreFullHouse.tvDescriptionTitle.text = "Full House"
        binding.scoreFullHouse.tvDescriptionContents.text = "동일한 눈의 주사위가 3,2개 일 때, 주사위 5개의 합"

        binding.scoreSmallStraight.ivDice1.setImageResource(R.drawable.ic_dice_one)
        binding.scoreSmallStraight.ivDice2.setImageResource(R.drawable.ic_dice_two)
        binding.scoreSmallStraight.ivDice3.setImageResource(R.drawable.ic_dice_three)
        binding.scoreSmallStraight.ivDice4.setImageResource(R.drawable.ic_dice_four)
        binding.scoreSmallStraight.ivDice5.setImageResource(R.drawable.ic_dice_six)
        binding.scoreSmallStraight.tvDescriptionTitle.text = "S.Straight"
        binding.scoreSmallStraight.tvDescriptionContents.text = "주사위 4개의 눈이 연속할 때, 15점"

        binding.scoreLargeStraight.ivDice1.setImageResource(R.drawable.ic_dice_two)
        binding.scoreLargeStraight.ivDice2.setImageResource(R.drawable.ic_dice_three)
        binding.scoreLargeStraight.ivDice3.setImageResource(R.drawable.ic_dice_four)
        binding.scoreLargeStraight.ivDice4.setImageResource(R.drawable.ic_dice_five)
        binding.scoreLargeStraight.ivDice5.setImageResource(R.drawable.ic_dice_six)
        binding.scoreLargeStraight.tvDescriptionTitle.text = "L.Straight"
        binding.scoreLargeStraight.tvDescriptionContents.text = "주사위 4개의 눈이 연속할 때, 30점"

        binding.scoreYacht.ivDice1.setImageResource(R.drawable.ic_dice_four)
        binding.scoreYacht.ivDice2.setImageResource(R.drawable.ic_dice_four)
        binding.scoreYacht.ivDice3.setImageResource(R.drawable.ic_dice_four)
        binding.scoreYacht.ivDice4.setImageResource(R.drawable.ic_dice_four)
        binding.scoreYacht.ivDice5.setImageResource(R.drawable.ic_dice_four)
        binding.scoreYacht.tvDescriptionTitle.text = "Yacht"
        binding.scoreYacht.tvDescriptionContents.text = "주사위 5개 눈이 모두 같을 때, 50점"

        binding.scoreTotal.tvDescriptionTitle.text = "총합"

        setScore(binding.scoreOnes, DialogType.Select,1)
        setScore(binding.scoreTwos, DialogType.Select,2)
        setScore(binding.scoreThrees, DialogType.Select,3)
        setScore(binding.scoreFours, DialogType.Select,4)
        setScore(binding.scoreFives, DialogType.Select,5)
        setScore(binding.scoreSixes, DialogType.Select,6)
        setScore(binding.scoreChoice, DialogType.Input,0)
        setScore(binding.score4OfAKind, DialogType.Input,0)
        setScore(binding.scoreFullHouse, DialogType.Input,0)
        setScore(binding.scoreSmallStraight, DialogType.Input,15)
        setScore(binding.scoreLargeStraight, DialogType.Input,30)
        setScore(binding.scoreYacht, DialogType.Input,50)

        viewModel.totalResult.observe(this){
            binding.scoreBonus.score.text = "${it.bonus}점"
            binding.scoreSubTotal.score.text = "${it.subTotal}점"
            binding.scoreTotal.score.text = "${it.total}점"
        }

        loadValue()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_reset -> {
                resetAll()

                return true
            }
            R.id.menu_option -> {
                AlertDialog.Builder(this).apply {
                    setItems(inputDialogType) { _, i ->
                        viewModel.savedInputDialogType(inputDialogType[i])
                    }
                }.show()

                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun resetAll(){
        MyApplication.prefs.setInt(binding.scoreOnes.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreTwos.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreThrees.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreFours.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreFives.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreSixes.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreChoice.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.score4OfAKind.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreFullHouse.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreSmallStraight.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreLargeStraight.tvDescriptionTitle.text.toString(), -1)
        MyApplication.prefs.setInt(binding.scoreYacht.tvDescriptionTitle.text.toString(), -1)

        MyApplication.prefs.setInt(binding.scoreBonus.tvDescriptionTitle.text.toString(), 0)
        MyApplication.prefs.setInt(binding.scoreSubTotal.tvDescriptionTitle.text.toString(), 0)
        MyApplication.prefs.setInt(binding.scoreTotal.tvDescriptionTitle.text.toString(), 0)

        loadValue()

        Toast.makeText(this, "초기화 되었습니다", Toast.LENGTH_LONG).show()
    }

    private fun setScore(item : LayoutScoreItemBinding, type : DialogType, num : Int) {
        item.score.apply {
            setOnClickListener {
                if (text == "등록") {
                    if(type == DialogType.Select){
                        val dlg = SelectDialog(this@MainActivity)
                        dlg.setOnClickedListener{ content ->
                            background = resources.getDrawable(R.drawable.bg_round_rec_blue)
                            text = "${content}점"
                            viewModel.savedScore(item.tvDescriptionTitle.text.toString(), content)
                        }
                        dlg.show(num)
                    }else if(type == DialogType.Input && num == 0) {
                        if(MyApplication.prefs.getString(MyApplication.PREF_INPUT_DIALOG_TYPE,"직접 입력 방식") == "계산기 방식"){
                            val dlg = Input2Dialog(this@MainActivity)
                            dlg.setOnClickedListener{ content ->
                                background = resources.getDrawable(R.drawable.bg_round_rec_blue)
                                text = "${content}점"
                                viewModel.savedScore(item.tvDescriptionTitle.text.toString(), content)
                            }
                            dlg.show()
                        }else {
                            val dlg = Input1Dialog(this@MainActivity)
                            dlg.setOnClickedListener{ content ->
                                background = resources.getDrawable(R.drawable.bg_round_rec_blue)
                                text = "${content}점"
                                viewModel.savedScore(item.tvDescriptionTitle.text.toString(), content)
                            }
                            dlg.show()
                        }
                    }else{
                        androidx.appcompat.app.AlertDialog.Builder(this@MainActivity)
                            .setMessage("점수를 등록합니다.")
                            .setPositiveButton("등록") { _, _ ->
                                background = resources.getDrawable(R.drawable.bg_round_rec_blue)
                                text = "${num}점"
                                viewModel.savedScore(item.tvDescriptionTitle.text.toString(), num)
                            }
                            .setNegativeButton("0점") { _, _ ->
                                background = resources.getDrawable(R.drawable.bg_round_rec_blue)
                                text = "0점"
                                viewModel.savedScore(item.tvDescriptionTitle.text.toString(), 0)
                            }
                            .create().show()
                    }
                } else {
                    androidx.appcompat.app.AlertDialog.Builder(this@MainActivity)
                        .setMessage("점수를 취소하시겠습니까?")
                        .setPositiveButton("확인") { _, _ ->
                            var n = 0
                            try {
                                n = Integer.parseInt(text.toString().replace("점" ,""))
                            }catch (e : Exception){}
                            viewModel.savedScore(item.tvDescriptionTitle.text.toString(), n*-1)

                            background = resources.getDrawable(R.drawable.bg_round_rec_orange)
                            text = "등록"
                        }
                        .setNegativeButton("취소") { dialogInterface, _ ->
                            dialogInterface.dismiss()
                        }
                        .create().show()
                }
            }
        }

    }

    private fun loadValue(){
        viewModel.initTotal()

        setScoreValue(binding.scoreOnes)
        setScoreValue(binding.scoreTwos)
        setScoreValue(binding.scoreThrees)
        setScoreValue(binding.scoreFours)
        setScoreValue(binding.scoreFives)
        setScoreValue(binding.scoreSixes)
        setScoreValue(binding.scoreChoice)
        setScoreValue(binding.score4OfAKind)
        setScoreValue(binding.scoreFullHouse)
        setScoreValue(binding.scoreSmallStraight)
        setScoreValue(binding.scoreLargeStraight)
        setScoreValue(binding.scoreYacht)

        setScoreValue(binding.scoreBonus)
        setScoreValue(binding.scoreSubTotal)
        setScoreValue(binding.scoreTotal)
    }

    private fun setScoreValue(item : LayoutScoreItemBinding){
        item.apply {
            val num = MyApplication.prefs.getInt(tvDescriptionTitle.text.toString(),-1)
            if(num != -1){
                score.background = resources.getDrawable(R.drawable.bg_round_rec_blue)
                score.text = "${num}점"
            }else{
                score.background = resources.getDrawable(R.drawable.bg_round_rec_orange)
                score.text = "등록"
            }
        }
    }

    private fun setScoreValue(item : LayoutScoreTotalItemBinding){
        item.apply {
            val num = MyApplication.prefs.getInt(tvDescriptionTitle.text.toString(),-1)
            if(num != -1){
                score.text = "${num}점"
            }else{
                score.text = "0점"
            }
        }
    }

}