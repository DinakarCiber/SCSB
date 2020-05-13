package org.main.recap.util;

import org.apache.commons.collections.CollectionUtils;
import org.main.recap.RecapConstants;
import org.main.recap.jpa.JobParamDetailRepository;
import org.main.recap.model.jpa.JobParamDataEntity;
import org.main.recap.model.jpa.JobParamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rajeshbabuk on 29/6/17.
 */
@Service
public class JobDataParameterUtil {

    /**
     * The Job param detail repository.
     */
    @Autowired
    JobParamDetailRepository jobParamDetailRepository;

    /**
     * This method builds the job parameters from the database and builds a map.
     *
     * @param jobName the job name
     * @return the map
     */
    public Map<String, String> buildJobRequestParameterMap(String jobName) {
        Map<String, String> parameterMap = new HashMap<>();
        JobParamEntity jobParamEntity = jobParamDetailRepository.findByJobName(jobName);
        if (CollectionUtils.isNotEmpty(jobParamEntity.getJobParamDataEntities())) {
            for (JobParamDataEntity jobParamDataEntity : jobParamEntity.getJobParamDataEntities()) {
                parameterMap.put(jobParamDataEntity.getParamName(), jobParamDataEntity.getParamValue());
            }
        }
        return parameterMap;
    }

    /**
     * This method returns the date string in format "yyyy-MM-dd HH:mm".
     *
     * @param createdDate the created date
     * @return date format string for export
     */
    public String getDateFormatStringForExport(Date createdDate) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(RecapConstants.EXPORT_DATE_FORMAT);
        return dateFormatter.format(getFromDate(createdDate));
    }

    /**
     * This method builds the from date using the current date whose time would be 00:00:00
     *
     * @param createdDate the created date
     * @return the from date
     */
    public Date getFromDate(Date createdDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(createdDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
}
