package com.ar.ush



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        if (springSecurityService.loggedIn) {
            redirect([controller: 'cuenta', view: 'index'])
        } else {
            respond new User(params)
        }
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'create'
            return
        }

        userInstance.save flush: true

        def userRole = Role.findByAuthority('ROLE_USER')
        UserRole.create userInstance, userRole

        springSecurityService.reauthenticate(userInstance.username)

        redirect([controller: 'cuenta', view: 'index'])
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'edit'
            return
        }

        userInstance.save flush: true

        springSecurityService.reauthenticate(userInstance.username)

        redirect([controller: 'cuenta', view: 'index'])
    }
}
