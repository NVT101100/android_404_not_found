package com.nvt.bloodbank.models
enum class Group(val group: Int) {
    Default(0),
    Apositive(1),
    Anegative(2),
    Bpositive(3),
    Bnegative(4),
    Opositive(5),
    Onegative(6),
    ABpositive(7),
    ABnegative(8),
    RhDpositve(9),
    RhDnegative(10)

}
class Blood(bloodGroup:Group,summary:String,goodHeal:Boolean,donated:Int) {
    var bloodGroup : Group = bloodGroup
    var summary : String = summary
    var goodHealth : Boolean = true
    var donated : Int = 0
}