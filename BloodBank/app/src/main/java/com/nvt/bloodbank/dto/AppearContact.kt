package com.nvt.bloodbank.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppearContact (
    var hospId : String? = null,
    var hospName:String? = null
        ):Parcelable