package com.ar.ush

class Usuario {

	String nombre
	String apellido
	Integer edad
	String sexo
	
    static constraints = {
		nombre()
		apellido nullable:true
		edad min:18, max:60
		sexo inList: ['masculino', 'femenino']
    }
}
