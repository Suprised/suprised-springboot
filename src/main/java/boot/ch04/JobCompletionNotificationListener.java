package boot.ch04;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			List<CreditBillBean> results = jdbcTemplate.query("SELECT JDOIDX, name, loginName, password, createDate FROM tuser", new RowMapper<CreditBillBean>() {
				@Override
				public CreditBillBean mapRow(ResultSet rs, int row) throws SQLException {
					CreditBillBean bean = new CreditBillBean();
					bean.setCreateDate(rs.getDate(5));
					bean.setJDOIDX(Long.valueOf(rs.getString(1)));
					bean.setLoginName(rs.getString(3));
					bean.setName(rs.getString(2));
					bean.setPassword(rs.getString(4));
					return bean;
				}
			});

			for (CreditBillBean person : results) {
				log.info("Found <" + person + "> in the database.");
			}

		}
	}
}
