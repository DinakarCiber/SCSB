package org.main.recap.batch.flow;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by rajeshbabuk on 13/7/17.
 */
public class SubmitCollectionExecutionDecider implements JobExecutionDecider {

    @Value("${include.submit.collection.in.sequence.job}")
    private boolean includeSubmitCollectionInSequenceJob;

    /**
     * This method decides if the submit collection should be included in the job sequence process.
     *
     * @param jobExecution
     * @param stepExecution
     * @return FlowExecutionStatus
     */
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (includeSubmitCollectionInSequenceJob) {
            return FlowExecutionStatus.COMPLETED;
        } else {
            return FlowExecutionStatus.FAILED;
        }
    }
}
