package com.nvt.bloodbank.dto

data class Hospitals (
    var hospitalName:String?=null,
    var hospitalAddress:String?=null,
    var hospitalPosition:Position=Position(0.0,0.0),
    var hospitalEmail:String?=null,
    var hospitalPhone:String?=null,
    var hospitalBlood:List<Blood> = listOf()
        )