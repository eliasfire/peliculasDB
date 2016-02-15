package com.ar.ush

class Pelicula {

	String titulo
	String sinopsis
	Persona director
	Integer duracion
	
	static hasMany = [horarios: Horario]
	
    static constraints = {
		        sinopsis maxSize: 4000
				titulo nullable: true
	}
	
	List<Cine> retrieveCines () {
		this.horarios.collect{ Horario horario ->
			horario.cine
		}.unique()
	}
}
