package com.nvt.bloodbank.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Position(
    var latitude:Double=0.0,
    var longitude:Double=0.0
):Parcelable