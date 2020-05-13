package org.recap.util;

import org.recap.spring.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;

/**
 * Created by sheiks on 28/06/17.
 */
public class HelperUtil {

    /**
     * Gets bean for the given type.
     *
     * @param <T>          the type parameter
     * @param requiredType the required type
     * @return the bean
     */
    public static <T> T getBean(Class<T> requiredType) {
        ApplicationContext applicationContext = ApplicationContextProvider.getInstance().getApplicationContext();
        return applicationContext.getBean(requiredType);
    }
}
