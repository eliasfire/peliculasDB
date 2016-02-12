package com.ar.ush

class CineController {
    static defaultAction = 'listAll'

    def listAll() {
        [theaters:Theater.list(sort:'name')]
    }
}
