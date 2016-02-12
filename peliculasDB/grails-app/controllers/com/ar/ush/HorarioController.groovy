package com.ar.ush

class HorarioController {
    static defaultAction = "show"

    def show(Showtime showtime) {
        [showtime:showtime]
    }

    def movieShowtimes (Movie movie) {
        Map<Theater,List<Showtime>> showtimesByTheater = movie.showtimes.groupBy { it.theater }
        [movie:movie, showtimesByTheater:showtimesByTheater]
    }

    def theaterShowtimes (Theater theater) {
        Map<Movie,List<Showtime>> showtimesByMovie = theater.showtimes.groupBy {it.movie}
        [theater:theater, showtimesByMovie:showtimesByMovie]
    }
}
