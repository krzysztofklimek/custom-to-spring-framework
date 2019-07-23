package pl.insert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.insert.dao.OwnerDao;
import pl.insert.model.Owner;


@Service
public class OwnerService {

    @Autowired
    OwnerDao ownerDao;


    @Transactional
    public void addOwner(Owner owner){
        ownerDao.persist(owner);
    }

    @Transactional
    public Owner getOwnerById(Long ownerId){
        Owner owner = ownerDao.getOwnerById(ownerId);
        return owner;
    }




}
