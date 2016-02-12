package com.ar.ush

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class CuentaController {
    def index (User usuario) {
        List<Pago> pagos = Pago.findAllByUser(usuario)

        [usuario: usuario, pagos: pagos]
    }
}
