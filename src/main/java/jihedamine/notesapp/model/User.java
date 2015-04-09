package jihedamine.notesapp.model;

import com.google.inject.internal.Objects;

import javax.persistence.*;
import java.util.List;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String openId;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    private CompanyAccount companyAccount;

    private Boolean appAdmin;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Note> notes;

    User() {}

    public User(String openId) {
        this.openId = openId;
    }

    public String getOpenId() {
        return openId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isAppAdmin() {
        return appAdmin;
    }

    public void setAppAdmin(Boolean appAdmin) {
        this.appAdmin = appAdmin;
    }

    public CompanyAccount getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(CompanyAccount companyAccount) {
        this.companyAccount = companyAccount;
    }

    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) return false;
        if (that == this) return true;
        if (!(that instanceof User)) return false;
        User thatUser = (User) that;
        return this.id.equals(thatUser.id) && this.openId.equals(thatUser.openId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, openId);
    }
}
