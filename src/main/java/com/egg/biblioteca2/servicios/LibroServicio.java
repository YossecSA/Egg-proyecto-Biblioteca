package com.egg.biblioteca2.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.biblioteca2.entidades.Autor;
import com.egg.biblioteca2.entidades.Editorial;
import com.egg.biblioteca2.entidades.Libro;
import com.egg.biblioteca2.repositorios.AutorRepositorio;
import com.egg.biblioteca2.repositorios.EditorialRepositorio;
import com.egg.biblioteca2.repositorios.LibroRepositorio;

import jakarta.transaction.Transactional;

@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    protected EditorialRepositorio editorialRepositorio;

    @Transactional 
    public void crearLibro(Long isbn, String titulo, Integer Ejemplares, String idAutor, String idEditorial){

        Autor autor = autorRepositorio.findById(idEditorial).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(Ejemplares);
        
        LocalDate todayLocalDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        libro.setAlta(sqlDate);

        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros(){

        List<Libro> libros = new ArrayList();
        libros = libroRepositorio.findAll();
        return libros;
    }

    @Transactional
    public void modificarLibro(Long isbn,String titulo, Integer Ejemplares, String idAutor, String idEditorial, Integer numEjemplares ){
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);

        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);

        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }
        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);    
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(numEjemplares);

            libroRepositorio.save(libro);
        }

    }
}
