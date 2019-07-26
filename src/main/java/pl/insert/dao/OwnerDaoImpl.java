package pl.insert.dao;

import pl.insert.model.Owner;
import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    @Override
    public Owner findOwnerByEmail(String email){
        Query query = entityManager.createQuery("from Owner where email = :email");
        query.setParameter("email", email);
        List<Owner> list = (List<Owner>) query.getResultList();

        if(list.size() > 0)
            return list.get(0);
        else
            return null;
    }
}
