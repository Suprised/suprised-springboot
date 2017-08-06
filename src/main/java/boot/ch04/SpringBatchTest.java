package boot.ch04;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBatchTest {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("importUserJob")
	private Job job;

	@RequestMapping("/startBatch")
	@ResponseBody
	public String startBatch() {
		JobExecution result;
		try {
			result = jobLauncher.run(job, (new JobParametersBuilder().addDate(
					"date", new Date())).toJobParameters());
			System.out.println(result.toString());
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		return "success";
	}
}
