package org.main.recap.batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by rajeshbabuk on 23/3/17.
 */

@Configuration
@ImportResource({"classpath*:/org/springframework/batch/admin/web/resources/servlet-config.xml",
                 "classpath*:/org/springframework/batch/admin/web/resources/webapp-config.xml",
                 "classpath*:/META-INF/integration-context.xml"})
public class BatchAdminConfiguration {
}
