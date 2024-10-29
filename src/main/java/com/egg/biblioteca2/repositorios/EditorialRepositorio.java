package com.egg.biblioteca2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egg.biblioteca2.entidades.Editorial;

public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
}
