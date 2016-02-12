package com.ar.ush

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class PagoController {
    def springSecurityService

    def completePago (OrdenPago ordenPago) {
        Horario showtime = Horario.get(ordenPago.showtimeId)
        showtime.seatsAvailable -= ordenPago.numberOfTickets
        showtime.ticketsSold += ordenPago.numberOfTickets
        showtime.save(failOnError: true, flush: true)

        Pago purchase = new Pago()
        purchase.numberOfTickets = ordenPago.numberOfTickets
        purchase.showtime = showtime
        purchase.user = springSecurityService.currentUser as User
        purchase.save(failOnError: true, flush: true)


        [showtime: showtime, numberOfTickets: purchase.numberOfTickets]
    }

    def startPagoForHorario (Horario showtime) {
        [showtime: showtime]
    }

    def reserveTickets (OrdenPago ordenPago) {
        Horario showtime = Horario.get(ordenPago.showtimeId)
        if (ordenPago.hasErrors()) {
            flash.message = "Sorry, there are only ${showtime.seatsAvailable} tickets available for this showtime."
            redirect action: 'startPagoForHorario', id: ordenPago.showtimeId
        } else {
            [showtime: showtime, numberOfTickets: ordenPago.numberOfTickets]
        }
    }
}
