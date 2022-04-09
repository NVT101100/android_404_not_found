package com.nvt.bloodbank.dto

data class Users(
    var profile:String?=null,
    var name:String? = null,
    var address:String?=null,
    var dob:String?=null,
    var phone:String?=null,
    var gender:String="male",
    var blood:String?=null,
    var idNum:String?=null,
    var insrNum:String?=null,
    var isDonor:Boolean=true
)