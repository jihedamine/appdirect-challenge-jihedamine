package jihedamine.notesapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@Entity
public class CompanyAccount {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String accountIdentifier;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EditionCode editionCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Embedded
    Company company;

    @Embedded
    private Marketplace marketplace;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "company_account_id")
    private Set<User> users;

    // Required by hibernate
    CompanyAccount() {
    }

    public CompanyAccount(String accountIdentifier, EditionCode editionCode, Company company) {
        this.accountIdentifier = accountIdentifier;
        this.editionCode = editionCode;
        this.status = getInitialStatus(editionCode);
        this.company = company;
        this.users = new HashSet<>();
    }

    // Sets the account status based on the edition code
    public static AccountStatus getInitialStatus(EditionCode editionCode) {
        return editionCode.equals(EditionCode.PREMIUM) ? AccountStatus.FREE_TRIAL : AccountStatus.ACTIVE;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public EditionCode getEditionCode() {
        return editionCode;
    }

    public void setEditionCode(EditionCode editionCode) {
        this.editionCode = editionCode;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void assignUser(User user) {
        this.users.add(user);
    }

    public void unassignUser(User user) {
        this.users.remove(user);
    }

}
