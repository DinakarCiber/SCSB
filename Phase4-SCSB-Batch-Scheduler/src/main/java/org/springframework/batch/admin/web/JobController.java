package org.springframework.batch.admin.web;

import org.springframework.batch.admin.service.JobService;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.batch.operations.NoSuchJobException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by rajeshbabuk on 5/7/17.
 */
@Controller
public class JobController {

    private final JobService jobService;

    private Collection<String> extensions = new HashSet<String>();

    private TimeZone timeZone = TimeZone.getDefault();

    private JobParametersExtractor jobParametersExtractor = new JobParametersExtractor();

    /**
     * A collection of extensions that may be appended to request urls aimed at
     * this controller.
     *
     * @param extensions the extensions (e.g. [rss, xml, atom])
     */
    public void setExtensions(Collection<String> extensions) {
        this.extensions = new LinkedHashSet<String>(extensions);
    }

    /**
     * Sets time zone.
     *
     * @param timeZone the timeZone to set
     */
    @Autowired(required = false)
    @Qualifier("userTimeZone")
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Instantiates a new Job controller.
     *
     * @param jobService the job service
     */
    @Autowired
    public JobController(JobService jobService) {
        super();
        this.jobService = jobService;
        extensions.addAll(Arrays.asList(".html", ".json", ".rss"));
    }

    /**
     * Gets job name.
     *
     * @param request the request
     * @return the job name
     */
    @ModelAttribute("jobName")
    public String getJobName(HttpServletRequest request) {
        String path = request.getPathInfo();
        if(path==null) {
            path=request.getServletPath();
        }
        int index = path.lastIndexOf("jobs/") + 5;
        if (index >= 0) {
            path = path.substring(index);
        }
        if (!path.contains(".")) {
            return path;
        }
        for (String extension : extensions) {
            if (path.endsWith(extension)) {
                path = StringUtils.stripFilenameExtension(path);
                // Only remove one extension so a job can be called job.html and
                // still be addressed
                break;
            }
        }
        return path;
    }

    /**
     * This method starts the job instantly when launch button is clicked from batch admin UI.
     *
     * @param model         the model
     * @param jobName       the job name
     * @param launchRequest the launch request
     * @param errors        the errors
     * @param origin        the origin
     * @return the string
     */
    @RequestMapping(value = "/jobs/{jobName}", method = RequestMethod.POST)
    public String launch(ModelMap model, @ModelAttribute("jobName") String jobName,
                         @ModelAttribute("launchRequest") LaunchRequest launchRequest, Errors errors,
                         @RequestParam(defaultValue = "execution") String origin) {

        launchRequest.setJobName(jobName);
        String params = launchRequest.getJobParameters();
        params = params + ",time=" + System.currentTimeMillis();

        JobParameters jobParameters = jobParametersExtractor.fromString(params);

        try {
            JobExecution jobExecution = jobService.launch(jobName, jobParameters);
            model.addAttribute(new JobExecutionInfo(jobExecution, timeZone));
        }
        catch (NoSuchJobException e) {
            errors.reject("no.such.job", new Object[] { jobName }, "No such job: " + jobName);
        }
        catch (JobExecutionAlreadyRunningException e) {

            errors.reject("job.already.running", "A job with this name and parameters is already running.");
        }
        catch (JobRestartException e) {
            errors.reject("job.could.not.restart", "The job was not able to restart.");
        }
        catch (JobInstanceAlreadyCompleteException e) {
            errors.reject("job.already.complete", "A job with this name and parameters already completed successfully.");
        }
        catch (JobParametersInvalidException e) {
            errors.reject("job.parameters.invalid", "The job parameters are invalid according to the configuration.");
        } catch (org.springframework.batch.core.launch.NoSuchJobException e) {
            e.printStackTrace();
        }

        if (!"job".equals(origin)) {
            // if the origin is not specified we are probably not a UI client
            return "jobs/execution";
        }
        else {
            // In the UI we show the same page again...
            return details(model, jobName, errors, 0, 20);
        }

        // Not a redirect because normally it is requested by an Ajax call so
        // there's less of a pressing need for one (the browser history won't
        // contain the request).

    }

    /**
     * This method gets the details of the job that is clicked from batch admin UI.
     *
     * @param model            the model
     * @param jobName          the job name
     * @param errors           the errors
     * @param startJobInstance the start job instance
     * @param pageSize         the page size
     * @return the string
     */
    @RequestMapping(value = "/jobs/{jobName}", method = RequestMethod.GET)
    public String details(ModelMap model, @ModelAttribute("jobName") String jobName, Errors errors,
                          @RequestParam(defaultValue = "0") int startJobInstance, @RequestParam(defaultValue = "20") int pageSize) {

        boolean launchable = jobService.isLaunchable(jobName);

        try {

            Collection<JobInstance> result = jobService.listJobInstances(jobName, startJobInstance, pageSize);
            Collection<JobInstanceInfo> jobInstances = new ArrayList<JobInstanceInfo>();
            model.addAttribute("jobParameters", jobParametersExtractor.fromJobParameters(jobService.getLastJobParameters(jobName)));

            for (JobInstance jobInstance : result) {
                Collection<JobExecution> jobExecutions = jobService.getJobExecutionsForJobInstance(jobName, jobInstance.getId());
                jobInstances.add(new JobInstanceInfo(jobInstance, jobExecutions, timeZone));
            }

            model.addAttribute("jobInstances", jobInstances);
            int total = jobService.countJobInstances(jobName);
            TableUtils.addPagination(model, total, startJobInstance, pageSize, "JobInstance");
            int count = jobService.countJobExecutionsForJob(jobName);
            model.addAttribute("jobInfo", new JobInfo(jobName, count, launchable, jobService.isIncrementable(jobName)));

        }
        catch (NoSuchJobException e) {
            errors.reject("no.such.job", new Object[] { jobName },
                    "There is no such job (" + HtmlUtils.htmlEscape(jobName) + ")");
        } catch (org.springframework.batch.core.launch.NoSuchJobException e) {
            e.printStackTrace();
        }

        return "jobs/job";

    }

    /**
     * This method gets all the batch jobs to be displayed batch admin UI.
     *
     * @param model    the model
     * @param startJob the start job
     * @param pageSize the page size
     */
    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public void jobs(ModelMap model, @RequestParam(defaultValue = "0") int startJob,
                     @RequestParam(defaultValue = "20") int pageSize) {
        int total = jobService.countJobs();
        TableUtils.addPagination(model, total, startJob, pageSize, "Job");
        Collection<String> names = jobService.listJobs(startJob, pageSize);
        List<JobInfo> jobs = new ArrayList<JobInfo>();
        for (String name : names) {
            int count = 0;
            try {
                count = jobService.countJobExecutionsForJob(name);
            }
            catch (NoSuchJobException e) {
                // shouldn't happen
            } catch (org.springframework.batch.core.launch.NoSuchJobException e) {
                e.printStackTrace();
            }
            boolean launchable = jobService.isLaunchable(name);
            boolean incrementable = jobService.isIncrementable(name);
            jobs.add(new JobInfo(name, count, null, launchable, incrementable));
        }
        model.addAttribute("jobs", jobs);
    }

}