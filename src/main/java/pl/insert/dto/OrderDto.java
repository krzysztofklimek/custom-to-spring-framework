package pl.insert.dto;

import org.hibernate.validator.constraints.*;

import java.io.Serializable;

public class OrderDto implements Serializable {


    @NotEmpty(message = "Username cannot be empty")
    private String firstName;

    @NotEmpty(message = "Username cannot be empty")
    private String secondName;


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

    public String toString(){
        return "first name: " + this.firstName + ", second name: " + this.secondName;
    }


}
