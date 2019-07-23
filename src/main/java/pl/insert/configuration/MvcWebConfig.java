package pl.insert.configuration;


import org.hibernate.cfg.beanvalidation.BeanValidationIntegrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import pl.insert.dao.UserDao;
import pl.insert.dao.UserDaoImpl;
import pl.insert.service.UserService;

import javax.persistence.ValidationMode;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("pl.insert.controller")
public class MvcWebConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean(name="userService")
    public UserService userService(){
        return new UserService();
    }

    @Bean(name="userDao")
    public UserDao userDao(){
        return new UserDaoImpl();
    }


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


    //TUTAJ KONIEC STARYCH KONFIGURACJI I POCZĄTEK NOWYCH


    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        //to jak coś usunąć
        viewResolver.setApplicationContext(applicationContext);

        viewResolver.setTemplateEngine(templateEngine());
        registry.viewResolver(viewResolver);
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }


}
