class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		
		"/" {
			controller = "pelicula"
			action = "listarTodo"
		}
		"500"(view: '/error')
		
		
       // "/"(view:"/index")
       // "500"(view:'/error')
	}
}
