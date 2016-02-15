package com.ar.ush

class HorarioController {
    static defaultAction = "show"

    def show(Horario horario) {
        [horario:horario]
    }

    def peliculaHorarios (Pelicula pelicula) {
        Map<Cine,List<Horario>> horariosByCine = pelicula.horarios.groupBy { it.cine }
        [pelicula:pelicula, horariosByCine:horariosByCine]
    }

    def cineHorarios (Cine cine) {
        Map<Pelicula,List<Horario>> horariosByPelicula = cine.horarios.groupBy {it.pelicula}
        [cine:cine, horariosByPelicula:horariosByPelicula]
    }
}
