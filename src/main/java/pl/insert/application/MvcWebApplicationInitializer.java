//bin validation, validator, konfiguracja na metodach
//https://stackoverflow.com/questions/35258758/getservletconfigclasses-vs-getrootconfigclasses-when-extending-abstractannot

package pl.insert.application;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pl.insert.configuration.WebFlowConfig;
import pl.insert.configuration.WebMvcConfig;
import pl.insert.configuration.WebSecurityConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {



//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[0] ;
//    }



    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebMvcConfig.class, WebFlowConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }



}
