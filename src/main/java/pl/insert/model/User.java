package pl.insert.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USERS")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="SURNAME")
    private String surname;




    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString(){
        return this.id + " | " + this.name + " | " + this.surname;
    }
}
