package jihedamine.notesapp.repositories;

import jihedamine.notesapp.model.Note;
import jihedamine.notesapp.model.User;
import org.springframework.data.repository.CrudRepository;


/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public interface NoteRepository extends CrudRepository<Note, Long> {

}
