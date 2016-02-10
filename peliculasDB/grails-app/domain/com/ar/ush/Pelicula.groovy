package com.ar.ush

class Pelicula {

	String titulo
	String sinopsis
	Persona director
	Integer duracion
	
    static constraints = {
		        sinopsis maxSize: 4000
				titulo nullable: true
	}
}
