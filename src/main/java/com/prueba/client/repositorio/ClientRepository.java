package com.prueba.client.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.client.entidad.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

}
