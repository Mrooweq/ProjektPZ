package com.malinki.pz.lib.entity;

public class UserDTO {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;

    public UserDTO(){}

    private UserDTO(UserDTOBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static class UserDTOBuilder {
        private String username;
        private String password;
        private String firstname;
        private String lastname;
        private String email;

        public UserDTOBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDTOBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserDTOBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
