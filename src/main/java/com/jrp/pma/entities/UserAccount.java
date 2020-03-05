package com.jrp.pma.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_accounts") // to understand to which table we referring
public class UserAccount {

    @Id
    @Column(name = "user_id") // to associate userId with user_id in the DB
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq")
    @SequenceGenerator(name="user_accounts_seq",sequenceName="user_accounts_seq", allocationSize=1,initialValue=1)
    private long userId;

    /*
     since we have a 'Camel-Case' here (userName), Spring will not find
     the field: user_name in the DB, hence, we need to define the association.
     we define that userName associated with username in the DB
    */
    @Column(name = "username")
    private String userName;

    private String email;

    private String password;

    private boolean enabled = true;

    public UserAccount() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
