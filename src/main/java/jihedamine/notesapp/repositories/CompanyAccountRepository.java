package jihedamine.notesapp.repositories;

import jihedamine.notesapp.model.CompanyAccount;
import org.springframework.data.repository.CrudRepository;


/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public interface CompanyAccountRepository extends CrudRepository<CompanyAccount, Long> {

    CompanyAccount findByAccountIdentifier(String accountIdentifier);

}
