package com.dvlprmatheus.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dvlprmatheus.address.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {
    
}
