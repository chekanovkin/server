package DataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")

public class UsersDataSet implements Serializable {

    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String password;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(long id, String name, String pass) {
        this.setId(id);
        this.setLogin(name);
        this.setPassword(pass);
    }

    public UsersDataSet(String name, String pass) {
        this.setId(-1);
        this.setLogin(name);
        this.setPassword(pass);
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public void setPassword(String pass){ password = pass; }

    public String getPassword(){ return password; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }

}
