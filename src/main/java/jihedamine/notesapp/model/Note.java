package jihedamine.notesapp.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
@Entity
public class Note {

    public Note() {
        this.creationDate = new Date();
    }

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
