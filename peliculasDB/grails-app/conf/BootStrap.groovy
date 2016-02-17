import com.ar.ush.Cine
import com.ar.ush.Horario
import com.ar.ush.Pelicula
import com.ar.ush.Role
import com.ar.ush.UserRole
import com.ar.ush.User

class BootStrap {

 def init = { servletContext ->
        crearUsers()

        List<Pelicula> peliculas = crearPeliculas()

        List<Cine> cines = crearCines()

        crearHorarios(peliculas, cines)
    }

    def destroy = {
    }

    void crearUsers () {
        def userRole = new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        def user1 = new User(
                username: 'user1',
                password: 'P4ssw0rd',
                enabled: true).save(failOnError: true)

        def adminUser = new User(
                username: 'admin',
                password: 'admin',
                enabled: true).save(failOnError: true)

        UserRole.create user1, userRole
        UserRole.create adminUser, adminRole
    }

    List<Pelicula> crearPeliculas () {
        String apuestaSynopsis = 'la historia está ambientada antes de que se colapsara el mercado inmobiliario y estallara la crisis económica; un grupo de expertos financieros descubren que los bancos han estado jugando con el dinero y tratan de castigar a los peces gordos...'
        String puenteSynopsis = 'El film cuenta la historia real de James Donovan, un abogado de Brooklyn que se ve súbitamente inmerso en las entrañas de la Guerra Fría cuando la CIA le envía con el encargo casi imposible de negociar la liberación de un piloto de un avión U-2 estadounidense capturado...'
        String renacidoSynopsis = 'Un hombre se sobrepone a una muerte casi segura para vengarse de otro. '
        String marteSynopsis = 'En el transcurso de una misión tripulada a Marte, el astronauta Mark Watney es dado por muerto y abandonado por sus compañeros de tripulación tras una violenta tormenta. '
        String spotSynopsis = 'Un drama sobre abusos sexuales a menores permitidos por la Iglesia Católica.'

        Pelicula pelicula1 = new Pelicula(titulo: 'La Gran Apuesta', duracion: 135, rating: 2, sinopsis: apuestaSynopsis).save(failOnError: true)
        Pelicula pelicula2 = new Pelicula(titulo: 'El puente de los espías', duracion: 110, rating: 3, sinopsis: puenteSynopsis).save(failOnError: true)
        Pelicula pelicula3 = new Pelicula(titulo: 'El renacido', duracion: 120, rating: 2, sinopsis: renacidoSynopsis).save(failOnError: true)
        Pelicula pelicula4 = new Pelicula(titulo: 'Marte', duracion: 90, rating: 1, sinopsis: marteSynopsis).save(failOnError: true)
        Pelicula pelicula5 = new Pelicula(titulo: 'Spotlight', duracion: 115, rating: 4, sinopsis: spotSynopsis).save(failOnError: true)

        [pelicula1,pelicula2,pelicula3,pelicula4,pelicula5]
    }

    List<Cine> crearCines () {
        String susntarDescripcion = 'Tienen muy buenas salas, grandes, con asientos muy cómodos. La mejor ubicación es la de la fila número dos (la de la baranda) ya que es la que tiene más espacio para las piernas y también podés apoyar los pies arriba, en baranda.'
        String PackeDescripcion = 'Tiene suficiente distancia con la pantalla porque hay espacio muy amplio entre la pantalla y las filas de asientos, así que no te preocupes que no vas a estar con el cuello duro, inclinado hacia arriba por 2 horas = )'
        String BlockDescripcion = 'Podés sacarlas en la sala u online si querés evitarte la cola. Si las sacás en la entrada, quizás te encontrás con que tienen alguna promoción que podés usar. Si las sacás online, no tienen promoción.'
        String DisneyDescripcion = 'Después de una linda cena, la salida ideal se completa con un heladito en el primer nivel del complejo Disney que lo podemos entrar a la Sala para disfrutar mirando una linda peli!'

        Cine cine1 = new Cine(nombre: 'Sunstar Ushuaia', codigoPostal: '43210', descripcion: susntarDescripcion, precioTicket: 99.00).save(failOnError: true)
        Cine cine2 = new Cine(nombre: 'Packewaia Ushuaia', codigoPostal: '43240', descripcion: PackeDescripcion, precioTicket: 80.00).save(failOnError: true)
        Cine cine3 = new Cine(nombre: 'Blockbuster Rio Grande', codigoPostal: '43219', descripcion: BlockDescripcion, precioTicket: 90.00).save(failOnError: true)
        Cine cine4 = new Cine(nombre: 'Disney World Tolhuin', codigoPostal: '43209', descripcion: DisneyDescripcion, precioTicket: 50.00).save(failOnError: true)

        [cine1,cine2,cine3,cine4]
    }

    void crearHorarios (List<Pelicula> peliculas, List<Cine> cines) {
        new Horario(pelicula: peliculas[0], cine: cines[0], hora: '6:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[0], cine: cines[0], hora: '9:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[1], cine: cines[0], hora: '4:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[1], cine: cines[0], hora: '6:30', asientosDisponibles: 0, ticketsVendidos: 300).save(failOnError: true)
        new Horario(pelicula: peliculas[1], cine: cines[0], hora: '9:00', asientosDisponibles: 2, ticketsVendidos: 298).save(failOnError: true)
        new Horario(pelicula: peliculas[2], cine: cines[0], hora: '6:00', asientosDisponibles: 1, ticketsVendidos: 299).save(failOnError: true)
        new Horario(pelicula: peliculas[2], cine: cines[0], hora: '8:30', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[3], cine: cines[0], hora: '5:30', asientosDisponibles: 0, ticketsVendidos: 300).save(failOnError: true)
        new Horario(pelicula: peliculas[3], cine: cines[0], hora: '7:30', asientosDisponibles: 0, ticketsVendidos: 300).save(failOnError: true)
        new Horario(pelicula: peliculas[4], cine: cines[0], hora: '9:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[0], cine: cines[1], hora: '6:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[0], cine: cines[1], hora: '8:30', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[0], cine: cines[1], hora: '11:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[1], cine: cines[1], hora: '5:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[1], cine: cines[1], hora: '8:00', asientosDisponibles: 0, ticketsVendidos: 300).save(failOnError: true)
        new Horario(pelicula: peliculas[2], cine: cines[1], hora: '5:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[2], cine: cines[1], hora: '7:30', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[2], cine: cines[1], hora: '10:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[3], cine: cines[1], hora: '4:30', asientosDisponibles: 0, ticketsVendidos: 300).save(failOnError: true)
        new Horario(pelicula: peliculas[3], cine: cines[1], hora: '6:30', asientosDisponibles: 5, ticketsVendidos: 295).save(failOnError: true)
        new Horario(pelicula: peliculas[3], cine: cines[1], hora: '8:30', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[0], cine: cines[2], hora: '6:15', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[0], cine: cines[2], hora: '9:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[1], cine: cines[2], hora: '5:30', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[1], cine: cines[2], hora: '8:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[1], cine: cines[2], hora: '10:30', asientosDisponibles: 0, ticketsVendidos: 300).save(failOnError: true)
        new Horario(pelicula: peliculas[2], cine: cines[2], hora: '7:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[2], cine: cines[2], hora: '10:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[3], cine: cines[2], hora: '5:00', asientosDisponibles: 0, ticketsVendidos: 300).save(failOnError: true)
        new Horario(pelicula: peliculas[3], cine: cines[2], hora: '7:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[3], cine: cines[2], hora: '9:00', asientosDisponibles: 0, ticketsVendidos: 300).save(failOnError: true)
        new Horario(pelicula: peliculas[2], cine: cines[3], hora: '6:45', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[4], cine: cines[3], hora: '6:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
        new Horario(pelicula: peliculas[4], cine: cines[3], hora: '9:00', asientosDisponibles: 300, ticketsVendidos: 0).save(failOnError: true)
    }
}
