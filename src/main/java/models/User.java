package models;

public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;

    public String getName() {return name;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getPhone() {return phone;}

    public User withName(String name) {
        this.name = name;
        return this;
    }
    public User withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public User withEmail(String email) {
        this.email = email;
        return this;
    }
    public User withPassword(String password) {
        this.password = password;
        return this;
    }
    public User withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
