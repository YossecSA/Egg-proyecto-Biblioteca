package com.egg.biblioteca2.servicios;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.biblioteca2.entidades.Autor;
import com.egg.biblioteca2.repositorios.AutorRepositorio;

import jakarta.transaction.Transactional;

@Service
public class AutorServicio {
    
    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre){
        Autor autor = new Autor();

        autor.setNombre(nombre);
        autorRepositorio.save(autor);
    }

    public java.util.List<Autor> listarAutores(){
        java.util.List<Autor> autores = new ArrayList();

        autores = autorRepositorio.findAll();
        return autores;
    }

    @Transactional
    public void modificarAutor(String nombre, String id){     
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }
}
