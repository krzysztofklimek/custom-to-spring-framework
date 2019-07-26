package pl.insert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.insert.dao.OwnerDao;
import pl.insert.dto.OwnerDto;
import pl.insert.exception.EmailExistsException;
import pl.insert.exception.NotMatchingPassword;
import pl.insert.model.Owner;


@Service
public class OwnerService {

    @Autowired
    OwnerDao ownerDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public void addOwner(Owner owner){
        ownerDao.persist(owner);
    }

    @Transactional
    public Owner getOwnerById(Long ownerId){
        Owner owner = ownerDao.getOwnerById(ownerId);
        return owner;
    }


    public Owner createValidatedOwner(OwnerDto ownerDto) throws EmailExistsException, NotMatchingPassword {

        if(emailExists(ownerDto.getEmail()))
            throw new EmailExistsException("Email exists");

        if(!ownerDto.getPassword().equals(ownerDto.getConfirmPassword()))
            throw new NotMatchingPassword("Not matching password");

        Owner owner = new Owner();
        owner.setName(ownerDto.getName());
        owner.setSurname(ownerDto.getSurname());
        owner.setEmail(ownerDto.getEmail());
        owner.setPassword(passwordEncoder.encode(ownerDto.getPassword()));
        owner.setEnabled(true);

        return owner;
    }

    @Transactional
    public boolean emailExists(String email){
        Owner owner = ownerDao.findOwnerByEmail(email);
        System.out.println(owner);
        return owner != null ? true : false;
    }

    @Transactional
    public Owner findOwnerByEmail(String email){
        return ownerDao.findOwnerByEmail(email);
    }

}
