package com.example.the_child_specialist.model

class Db_Disease {
    var id: Int = 0
    var name: String? = null
    var description: String? = null


    constructor(name: String, phoneNumber: String) {
        this.name = name
        this.description = phoneNumber
    }

    // OVERLOADD OUR CONSTRUCTOR IF SOME GIVE ID THEN THIS WORK.

    constructor(id: Int, name: String, phoneNumber: String) {
        this.id = id
        this.name = name
        this.description = phoneNumber
    }
    // IF NOTHING GIVE ANY DATA THEN BELOW WORK

    constructor() {}
}