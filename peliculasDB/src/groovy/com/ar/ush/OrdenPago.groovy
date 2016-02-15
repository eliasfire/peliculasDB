package com.ar.ush

import grails.validation.Validateable

@Validateable
class OrdenPago {
    Integer numeroTickets
    Long horarioId

    static constraints = {
        numeroTickets nullable: true, validator: { value, command ->
            Integer availableSeats = Horario.get(command.horarioId).asientosDisponibles
            if (value > availableSeats) {
                return false
            }
            return true
        }
    }
}
