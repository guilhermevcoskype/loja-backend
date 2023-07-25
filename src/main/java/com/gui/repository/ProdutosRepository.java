package com.gui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gui.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long>{
    
}
