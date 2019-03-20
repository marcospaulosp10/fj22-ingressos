package br.com.caelum.ingresso.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Compra;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class VendasDao {

    @PersistenceContext
    private EntityManager manager;

    public void save(Compra compra) {
        manager.persist(compra);
    }
    
    public Compra findOne(Integer id) {
        return manager.find(Compra.class, id);
    }
}
