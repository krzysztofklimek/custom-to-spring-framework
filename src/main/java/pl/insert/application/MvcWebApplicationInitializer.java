//bin validation, validator, konfiguracja na metodach

package pl.insert.application;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pl.insert.configuration.MvcWebConfig;
import pl.insert.configuration.WebFlowConfig;
import pl.insert.configuration.WebSecurityConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {



//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[0] ;
//    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebSecurityConfig.class, WebFlowConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {MvcWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }



}
