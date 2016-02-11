package com.ar.ush

class Cine {
    String nombre
    String codigoPostal
    String descripcion
    BigDecimal precioTicket

    static hasMany = [horarios: Horario]

    static constraints = {
        descripcion maxSize: 4000
    }

    List<Pelicula> obtenerPeliculas () {
        horarios.collect{it.pelicula}.unique()
    }
}
