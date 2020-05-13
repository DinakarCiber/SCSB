package org.main.recap.jpa;

import org.main.recap.model.jpa.JobParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rajeshbabuk on 29/6/17.
 */
public interface JobParamDetailRepository extends JpaRepository<JobParamEntity, Integer> {

    /**
     * Finds job param entity by using job name.
     *
     * @param jobName the job name
     * @return the job param entity
     */
    JobParamEntity findByJobName(String jobName);

}
