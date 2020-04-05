package com.example.the_child_specialist.model_nutrition

class DB_Nutrition {
    var id2: Int = 0
    var name2: String? = null
    var description2: String? = null


    constructor(name: String, phoneNumber: String) {
        this.name2 = name
        this.description2 = phoneNumber
    }

    // OVERLOADD OUR CONSTRUCTOR IF SOME GIVE ID THEN THIS WORK.

    constructor(id: Int, name: String, phoneNumber: String) {
        this.id2 = id
        this.name2 = name
        this.description2 = phoneNumber
    }
    // IF NOTHING GIVE ANY DATA THEN BELOW WORK

    constructor() {}
}