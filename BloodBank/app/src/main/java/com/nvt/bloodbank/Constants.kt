package com.nvt.bloodbank
final enum class LogState {
    logged_new,logged_old,unidentified,failed,not_logged
}

object Constants {
    public final var databaseURL: String =
        "https://bloodbank-344203-default-rtdb.asia-southeast1.firebasedatabase.app"
}