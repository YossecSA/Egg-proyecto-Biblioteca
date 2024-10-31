package com.egg.biblioteca2.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/")
    public String index() {
        return "index.html";   // Acá es que retornamos con el método. 
    }
}
