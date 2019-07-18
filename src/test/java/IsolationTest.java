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
//transaction symhronization manager

    @Test
    public void phantomReads() throws InterruptedException {

        transactionTemplate.execute(status -> {
            return null;
        });

        final int[] size = new int[2];

        Thread thread2 = new Thread(() -> {

            User user = new User();
            user.setName("name");
            user.setSurname("surname");

            userService.addUser(user);
        });

        Thread thread1 = new Thread(() -> {
            size[0] = userService.getUserList().size();

            thread2.start();
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            size[1] = userService.getUserList().size();
        });


        thread1.start();
        thread1.join();

        assertNotEquals(size[0], size[1]);
        assertEquals(size[0] + 1, size[1]);
    }





//
//    @Test
//    public void phantomReadsWithSerializable() throws InterruptedException {
//
//        final int[] size = new int[2];
//
//        Thread thread1 = new Thread(){
//            public void run(){
//                size[0] = userService.getUserListWithSerializable().size();
//
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                size[1] = userService.getUserListWithSerializable().size();
//            }
//        };
//
//
//        Thread thread2 = new Thread(){
//            public void run(){
//
//                User user = new User();
//                user.setName("name");
//                user.setSurname("surname");
//
//                userService.addUser(user);
//            }
//        };
//
//        thread1.start();
//        Thread.sleep(1000);
//        thread2.start();
//        Thread.sleep(3000);
//
//        assertEquals(size[0], size[1]);
//    }

}
