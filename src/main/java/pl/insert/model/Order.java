package pl.insert.model;


import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Order {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name= "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
