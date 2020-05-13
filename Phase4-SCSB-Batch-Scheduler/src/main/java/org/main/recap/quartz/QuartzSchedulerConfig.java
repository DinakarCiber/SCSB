package org.main.recap.quartz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by rajeshbabuk on 7/4/17.
 */
@Configuration
public class QuartzSchedulerConfig {

    /**
     * This method is a annotated bean definition for scheduler object.
     *
     * @return the scheduler factory bean
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        return new SchedulerFactoryBean();
    }
}
