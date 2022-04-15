package com.nvt.bloodbank.dto

import com.nvt.bloodbank.bldGrp

data class Users(
    var profile:String?=null,
    var name:String? = null,
    var address:String?=null,
    var dob:String?=null,
    var phone:String?=null,
    var gender:String="male",
    var blood:bldGrp=bldGrp.APos,
    var idNum:String?=null,
    var insrNum:String?=null,
    var isDonor:Boolean=true,
    var position:Position=Position(0.0,0.0)
)