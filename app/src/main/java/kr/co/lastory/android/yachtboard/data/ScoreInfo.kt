package kr.co.lastory.android.yachtboard.data

data class ScoreInfo(
    var ones : Int = 0,
    var twos : Int = 0,
    var threes : Int = 0,
    var fours : Int = 0,
    var fives : Int = 0,
    var sixes : Int = 0,
    var choice : Int = 0,
    var fourOfAKind : Int = 0,
    var fullHouse : Int = 0,
    var smallStraight : Int = 0,
    var largeStraight : Int = 0,
    var yacht : Int = 0,
    var totalInfo : TotalInfo = TotalInfo(),
    )
