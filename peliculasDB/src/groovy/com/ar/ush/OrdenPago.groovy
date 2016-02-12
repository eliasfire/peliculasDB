package com.ar.ush

import grails.validation.Validateable

@Validateable
class OrdenPago {
    Integer numberOfTickets
    Long showtimeId

    static constraints = {
        numberOfTickets nullable: true, validator: { value, command ->
            Integer availableSeats = Showtime.get(command.showtimeId).seatsAvailable
            if (value > availableSeats) {
                return false
            }
            return true
        }
    }
}
