package com.nvt.bloodbank.dto

import android.os.Parcelable
import com.nvt.bloodbank.bldGrp
import kotlinx.parcelize.Parcelize

@Parcelize
data class Blood(
    var bloodName:bldGrp?=null,
    var need:Long = 0,
    var donated:HashMap<String,Long> = HashMap(),
    var ready :List<String> = listOf()
):Parcelable