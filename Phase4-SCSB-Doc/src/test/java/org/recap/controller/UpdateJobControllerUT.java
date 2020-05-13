package org.recap.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.recap.RecapConstants;
import org.recap.model.jpa.JobEntity;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rajeshbabuk on 20/4/17.
 */
public class UpdateJobControllerUT extends BaseControllerUT {

    @Test
    public void testUpdateJob() throws Exception {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobName(RecapConstants.PURGE_EXCEPTION_REQUESTS);
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult mvcResult = this.mockMvc.perform(post("/updateJobService/updateJob")
                .headers(getHttpHeaders())
                .contentType(contentType)
                .content(objectMapper.writeValueAsString(jobEntity)))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
    }
}
