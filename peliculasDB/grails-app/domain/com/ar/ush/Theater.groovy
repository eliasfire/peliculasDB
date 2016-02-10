package com.ar.ush

class Theater {
    String name
    String zip
    String description
    BigDecimal ticketPrice

    static hasMany = [horarios: Horario]

    static constraints = {
        description maxSize: 4000
    }

    List<Pelicula> obtenerPeliculas () {
        horarios.collect{it.pelicula}.unique()
    }
}
