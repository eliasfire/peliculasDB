package com.ar.ush

class Horario {
	String hora
	Integer asientosDisponibles
	Integer ticketsVendidos

	static belongsTo = [pelicula: Pelicula, cine: Cine]

	static constraints = {
	}
}
