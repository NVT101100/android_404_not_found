package com.nvt.bloodbank.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Events (
    var eventName:String?=null,
    var eventAddress:String?=null,
    var eventPosition:Position=Position(0.0,0.0),
    var eventDay:String?=null,
    var eventTime:String?=null,
    var eventTarget:Long?=null,
    var eventRegistered:List<String> = listOf()
        ):Parcelable