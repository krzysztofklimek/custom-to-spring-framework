import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.support.TransactionTemplate;
import pl.insert.configuration.Config;
import pl.insert.model.User;
import pl.insert.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Config.class}, loader = AnnotationConfigContextLoader.class)
public class IsolationTest {

    //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    //final UserService userService = applicationContext.getBean("userService", UserService.class);

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private UserService userService;



    @Test
    public void phantomReadsTest() throws InterruptedException {

        final int[] result = new int[1];


        Thread thread1 = new Thread(){
            public void run(){

                try {
                    result[0] = userService.readUserTwice();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread thread2 = new Thread(){
            public void run(){
                User user = new User();
                user.setName("test");
                user.setSurname("test");

                userService.addUser(user);
            }
        };
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        Thread.sleep(4000);

        assertEquals(result[0], 1);
    }


    @Test
    public void dirtyReadsTest() throws InterruptedException {


        final int[] result = new int[2];

        int size = userService.getUserList().size();


        Thread thread1 = new Thread(){
            public void run(){

                try {
                    result[0] = userService.addUserTwice();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread(){
            public void run(){
                result[1] = userService.getUserList().size();
            }
        };


        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        Thread.sleep(4000);


        assertEquals(size + 1, result[1]);



    }




}
