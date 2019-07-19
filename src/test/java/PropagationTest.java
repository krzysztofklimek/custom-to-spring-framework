import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pl.insert.configuration.Config;
import pl.insert.model.User;
import pl.insert.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Config.class}, loader = AnnotationConfigContextLoader.class)
public class PropagationTest {


    @Autowired
    private UserService userService;


    @Test
    public void requiredNew() {

        User user = new User();
        user.setName("AspectJ");
        user.setSurname("surname");

        int beforeTestSize = userService.getUserList().size();

        try {
            userService.addUserWithRequiresNew(user);
        } catch (Exception e) {
        }

        int afterTestSize = userService.getUserList().size();

        Assertions.assertNotEquals(beforeTestSize, afterTestSize);
        assertEquals(beforeTestSize + 1, afterTestSize);
    }


}
