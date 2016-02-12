package com.ar.ush

import grails.transaction.Transactional

@Transactional(readOnly = true)
class PeliculaController {
    static defaultAction = 'listarTodo'

    def listarTodo() {
        [peliculas:Pelicula.list(sort:'titulo')]
    }
}
