package boot.ch04;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.ExecutionContextDao;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.dao.StepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	// tag::listener[]

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener(new JdbcTemplate(
				dataSource));
	}

	// end::listener[]

	// tag::jobstep[]
	@Bean
	public Job importUserJob() {
		return jobBuilderFactory.get("importUserJob")
				// .incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<CreditBillBean, CreditBillBean> chunk(10)
				.reader(reader()).processor(processor()).writer(writer())
				.build();
	}
	// end::jobstep[]

	@Bean
    public FlatFileItemReader<CreditBillBean> reader() {
        FlatFileItemReader<CreditBillBean> reader = new FlatFileItemReader<CreditBillBean>();
        reader.setResource(new ClassPathResource("ch04/data/credit-card-bill-201303.csv"));
        reader.setLineMapper(new DefaultLineMapper<CreditBillBean>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
            	/*<bean:value>accountID</bean:value>
                <bean:value>name</bean:value>
                <bean:value>amount</bean:value>
                <bean:value>date</bean:value>
                <bean:value>address</bean:value>*/
                setNames(new String[] { "JDOIDX", "loginName", "password", "createDateStr", "name" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CreditBillBean>() {{
                setTargetType(CreditBillBean.class);
            }});
        }});
        return reader;
    }

    @Bean
    public CreditBillBeanProcess processor() {
        return new CreditBillBeanProcess();
    }

    @Bean
    public JdbcBatchItemWriter<CreditBillBean> writer() {
        JdbcBatchItemWriter<CreditBillBean> writer = new JdbcBatchItemWriter<CreditBillBean>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CreditBillBean>());
        writer.setSql("INSERT INTO tuser (loginName, password, name, createDate) VALUES (:loginName, :password, :name, :createDate)");
        writer.setDataSource(dataSource);
        return writer;
    }
    
    /*@Bean
    public SimpleJobLauncher jobLauncher() {
    	SimpleJobLauncher lanuncher = new SimpleJobLauncher();
    	lanuncher.setJobRepository(newJobRepository());
    	return lanuncher;
    }

    @Bean
	public JobRepository newJobRepository() {
    	return new SimpleJobRepository(newMapJobInstanceDao(), newMapJobExecutionDao(), newMapStepExecutionDao(), newMapExecutionContextDao());
	}

    @Bean
	public JobExecutionDao newMapJobExecutionDao() {
		return new MapJobExecutionDao();
	}

    @Bean
	public ExecutionContextDao newMapExecutionContextDao() {
		return new MapExecutionContextDao();
	}

    @Bean
	public StepExecutionDao newMapStepExecutionDao() {
		return new MapStepExecutionDao();
	}

    @Bean
	public JobInstanceDao newMapJobInstanceDao() {
		return new MapJobInstanceDao();
	}*/
    
    /*@Override
    protected JobLauncher createJobLauncher() throws Exception {
    	return jobLauncher();
    }
    
    @Override
    protected JobRepository createJobRepository() throws Exception {
    	return newJobRepository();
    }*/
}
