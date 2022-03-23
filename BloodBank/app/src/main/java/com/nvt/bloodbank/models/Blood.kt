package com.nvt.bloodbank.models
enum class Group(val group: Int) {
    default(0),
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
class Blood {
    var bloodGroup : Group? = Group.default
    var summary : String? = null
    var goodHealth : Boolean = true
    var donated : Int = 0
}