//bin validation, validator, konfiguracja na metodach

package pl.insert.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AMvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {



    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
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
