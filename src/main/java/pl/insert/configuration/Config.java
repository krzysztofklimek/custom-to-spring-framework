package pl.insert.configuration;



import javax.persistence.ValidationMode;


import org.hibernate.cfg.beanvalidation.BeanValidationIntegrator;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import pl.insert.dao.OwnerDao;
import pl.insert.dao.OwnerDaoImpl;
import pl.insert.dao.UserDao;
import pl.insert.dao.UserDaoImpl;
import pl.insert.service.OwnerService;
import pl.insert.service.UserService;

//import java.util.logging.LoggingPermission;


@Configuration
@EnableTransactionManagement
public class Config {

    //Logger logger = LoggerFactory.getLogger(this.getClass().getName());



    @Bean(name="userService")
    public UserService userService(){
        return new UserService();
    }


    @Bean(name="userDao")
    public UserDao userDao(){
        return new UserDaoImpl();
    }

//---------------------------------------------------------------------------
    @Bean(name="ownerService")
    public OwnerService ownerService(){
        return new OwnerService();
    }


    @Bean(name="ownerDao")
    public OwnerDao ownerDao(){
        return new OwnerDaoImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//----------------------------------------------------------------------------
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        //logger.info("entityManagerFactory - initialization started");
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("MOL_SQL");
        entityManagerFactoryBean.getJpaPropertyMap().put(BeanValidationIntegrator.MODE_PROPERTY, ValidationMode.NONE);



        return entityManagerFactoryBean;
    }



    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() {
        //logger.info("transactionManager - initialization started");
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
        transactionManager.setRollbackOnCommitFailure(true);
        return transactionManager;
    }

    // opcjonalnie
    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }


}


