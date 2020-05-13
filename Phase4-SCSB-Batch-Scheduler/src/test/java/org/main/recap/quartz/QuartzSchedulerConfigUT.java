package org.main.recap.quartz;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 20/4/17.
 */
public class QuartzSchedulerConfigUT extends BaseTestCase {

    @Autowired
    QuartzSchedulerConfig quartzSchedulerConfig;

    @Test
    public void testSchedulerFactoryBean() throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = quartzSchedulerConfig.schedulerFactoryBean();
        assertNotNull(schedulerFactoryBean);
    }
}
