package com.ar.ush



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PeliculaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Pelicula.list(params), model:[peliculaInstanceCount: Pelicula.count()]
    }

    def show(Pelicula peliculaInstance) {
        respond peliculaInstance
    }

    def create() {
        respond new Pelicula(params)
    }

    @Transactional
    def save(Pelicula peliculaInstance) {
        if (peliculaInstance == null) {
            notFound()
            return
        }

        if (peliculaInstance.hasErrors()) {
            respond peliculaInstance.errors, view:'create'
            return
        }

        peliculaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pelicula.label', default: 'Pelicula'), peliculaInstance.id])
                redirect peliculaInstance
            }
            '*' { respond peliculaInstance, [status: CREATED] }
        }
    }

    def edit(Pelicula peliculaInstance) {
        respond peliculaInstance
    }

    @Transactional
    def update(Pelicula peliculaInstance) {
        if (peliculaInstance == null) {
            notFound()
            return
        }

        if (peliculaInstance.hasErrors()) {
            respond peliculaInstance.errors, view:'edit'
            return
        }

        peliculaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Pelicula.label', default: 'Pelicula'), peliculaInstance.id])
                redirect peliculaInstance
            }
            '*'{ respond peliculaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Pelicula peliculaInstance) {

        if (peliculaInstance == null) {
            notFound()
            return
        }

        peliculaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Pelicula.label', default: 'Pelicula'), peliculaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pelicula.label', default: 'Pelicula'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
