package com.nvt.bloodbank.dto

import com.nvt.bloodbank.bldGrp

data class Blood(
    var bloodName:bldGrp?=null,
    var need:HashMap<String,Int> = HashMap(),
    var donor:HashMap<String,Int> = HashMap()
)