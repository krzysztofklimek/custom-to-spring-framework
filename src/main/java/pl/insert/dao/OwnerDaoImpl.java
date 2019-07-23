package pl.insert.dao;

import pl.insert.model.Owner;
import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OwnerDaoImpl implements OwnerDao {


    @PersistenceContext
    private EntityManager entityManager;


    public void persist(Owner owner) {
        entityManager.persist(owner);
    }


    @Override
    public Owner getOwnerById(Long ownerId) {
        Owner owner = entityManager.find(Owner.class, Long.valueOf(ownerId));
        return owner;
    }

}
