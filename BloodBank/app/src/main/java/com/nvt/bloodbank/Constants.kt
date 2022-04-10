package com.nvt.bloodbank
final enum class LogState {
    logged_new,logged_old,unidentified,failed,not_logged
}
enum class bldGrp(val value:Int) {
    APos(0),
    ANeg(1),
    ABPos(2),
    ABNeg(3),
    BPos(4),
    BNeg(5),
    OPos(6),
    ONeg(7),
    RhPos(8),
    RhNeg(9)
}

object Constants {
    public final var databaseURL: String =
        "https://bloodbank-344203-default-rtdb.asia-southeast1.firebasedatabase.app"
}