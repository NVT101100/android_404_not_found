package com.nvt.bloodbank.models

class Users(fullname:String,age:Int,address:String,gender:String,idNumber:String,imageUri:String,blood: Blood) {
    var fullname : String = fullname
    var age : Int = age
    var address : String= address
    var gender : String = gender
    var idNumber : String = idNumber
    var imageUri : String = imageUri
    var blood : Blood= blood
}