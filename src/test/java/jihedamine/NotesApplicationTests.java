package jihedamine;

import jihedamine.notesapp.NotesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Tests that the webapp loads correctly in the environment where the test are run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NotesApplication.class)
@WebAppConfiguration
public class NotesApplicationTests {

	@Test
	public void contextLoads() {
	}

}
