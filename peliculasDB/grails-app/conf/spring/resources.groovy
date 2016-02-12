import org.h2.tools.Server

// Place your Spring DSL code here
beans = {
    // This allows TCP connections into the embedded H2 server (from the same host only)
    // using a JDBC url like the following (depending on what grails mode we're running in):
    // jdbc:h2:tcp://localhost:8043/mem:devDB
    // We do this purely so that backups can be generated without having to take the
    // app/DB offline. This approach is superior to using H2's AUTO_SERVER feature as it
    // works for in-memory DBs as well as file-based and has various other benefits.
    h2Server(Server, "-tcp,-tcpAllowOthers,-tcpPort,8043") { bean ->
        bean.factoryMethod = "createTcpServer"
        bean.initMethod = "start"
        bean.destroyMethod = "stop"
    }
}
