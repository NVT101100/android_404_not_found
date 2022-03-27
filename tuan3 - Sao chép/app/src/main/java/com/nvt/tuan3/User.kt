package com.nvt.tuan3


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(var fullname:String?, var email:String?, var phone:String?):Parcelable