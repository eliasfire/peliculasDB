package com.ar.ush

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class PagoController {
    def springSecurityService

    def completePago (OrdenPago ordenPago) {
        Horario horario = Horario.get(ordenPago.horarioId)
        horario.asientosDisponibles -= ordenPago.numeroTickets
        horario.ticketsVendidos += ordenPago.numeroTickets
        horario.save(failOnError: true, flush: true)

        Pago pago = new Pago()
        pago.numeroTickets = ordenPago.numeroTickets
        pago.horario = horario
        pago.user = springSecurityService.currentUser as User
        pago.save(failOnError: true, flush: true)


        [horario: horario, numeroTickets: pago.numeroTickets]
    }

    def startPagoForHorario (Horario horario) {
        [horario: horario]
    }

    def reserveTickets (OrdenPago ordenPago) {
        Horario horario = Horario.get(ordenPago.horarioId)
        if (ordenPago.hasErrors()) {
            flash.message = "Sorry, there are only ${horario.asientosDisponibles} tickets available for this horario."
            redirect action: 'startPagoForHorario', id: ordenPago.horarioId
        } else {
            [horario: horario, numeroTickets: ordenPago.numeroTickets]
        }
    }
}
