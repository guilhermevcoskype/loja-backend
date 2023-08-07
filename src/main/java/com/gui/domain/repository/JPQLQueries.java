package com.gui.domain.repository;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gui.domain.model.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class JPQLQueries {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Produto> findUltimosLancamentos() {
		return entityManager.createQuery("SELECT p FROM Produto p ORDER BY p.dataInsercao desc",
				Produto.class).setMaxResults(5).getResultList();
	}
}