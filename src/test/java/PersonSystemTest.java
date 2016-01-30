import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author apo
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonSystemTest {

    @InjectMocks
    private PersonSystem personSystem;

    @Mock
    private PersonDAO personDAO;

    @Test
    public void testBuildHelloMessage() throws Exception {
        when(personDAO.getPersonNameById(10)).thenReturn("John");

        assertThat(personSystem.buildHelloMessage(10), is("Hello, I'm John"));
    }
}

interface PersonDAO {
    String getPersonNameById(int id);
}

class PersonSystem {

    @Autowired
    private PersonDAO personDAO;

    public String buildHelloMessage(int personId) {
        return "Hello, I'm " + personDAO.getPersonNameById(personId);
    }
}
