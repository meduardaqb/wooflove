package com.meduardaqb.wooflove.model

class User {

    var name: String? = null
    var email: String = ""
    var breed: String? = null
    var city: String? = null
    var password: String = ""
    var message: String? = null

    constructor() {}

    constructor(name: String?, email: String, breed: String?, city: String?, password: String, message: String?) {
        this.name = name
        this.email = email
        this.breed = breed
        this.city = city
        this.password = password
        this.message = message
    }

}


