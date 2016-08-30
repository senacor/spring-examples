package demo.intern.properties.e01_basics;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fsubasi on 13.01.2016
 * Here we see how a bean of class Person is instantiated with data from external configuration
 * using @ConfigurationProperties
 */
@ConfigurationProperties("webmaster")
public class Person {
    private String name;
    private String email;
    private int tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString(){
        return "Name: " + this.name + "\nEmail: " + this.email + "\nTel: " + this.getTel();
    }
}
