package pl.insert.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class OrderDto implements Serializable {


    @NotEmpty
    private String firstName;

    @NotEmpty
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
}
