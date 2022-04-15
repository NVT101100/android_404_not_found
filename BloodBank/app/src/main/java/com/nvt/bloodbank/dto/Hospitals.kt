package com.nvt.bloodbank.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hospitals (
    var hospitalName:String?=null,
    var hospitalAddress:String?=null,
    var hospitalPosition:Position=Position(0.0,0.0),
    var hospitalEmail:String?=null,
    var hospitalPhone:String?=null,
    var hospitalBlood:List<Blood> = listOf()
        ):Parcelable