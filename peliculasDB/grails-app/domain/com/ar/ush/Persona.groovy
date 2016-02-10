package com.ar.ush

class Persona {

    String nombre
	Integer edad
    Date fechaCreacion



    def beforeInsert() {
        fechaCreacion = new Date()
    }

    static constraints = {
        nombre nullable : false, unique : true
    }
}
