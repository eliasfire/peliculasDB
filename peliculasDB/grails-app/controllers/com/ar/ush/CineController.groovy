package com.ar.ush

class CineController {
    static defaultAction = 'listarTodo'

    def listarTodo() {
        [cines:Cine.list(sort:'nombre')]
    }
}
