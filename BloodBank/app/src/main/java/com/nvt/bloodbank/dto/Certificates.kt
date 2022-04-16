package com.nvt.bloodbank.dto

data class Certificates(
    var times:Long = 0,
    var date:String?=null,
    var time:String?=null,
    var unit:Long = 0,
    var organization:String?=null
)