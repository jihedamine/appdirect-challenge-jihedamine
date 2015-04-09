package jihedamine.notesapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Enable JPA repositories
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@Configuration
@EnableJpaRepositories("jihedamine.notesapp.repositories")
public class RepositoriesConfig {
}
