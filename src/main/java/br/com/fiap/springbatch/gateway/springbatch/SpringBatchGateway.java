package br.com.fiap.springbatch.gateway.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.fiap.springbatch.exception.ErroAoProcessarJobException;
import br.com.fiap.springbatch.gateway.JobGateway;
import br.com.fiap.springbatch.model.Carga;

@Component
public class SpringBatchGateway implements JobGateway{
	
	private static final Logger logger = LoggerFactory.getLogger(SpringBatchGateway.class);
	private JobLauncher jobLauncher;
	private Job job;

	public SpringBatchGateway(
			JobLauncher jobLauncher,
			@Qualifier("processarProdutos") Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}


	@Override
	public void execute(Carga carga) throws ErroAoProcessarJobException {
		try {
    		JobParameters jobParameters = new JobParameters();
    		jobLauncher.run(job, jobParameters);

		} catch (Exception e) {
			logger.error("Erro ao executar o job. Carga: {}, Job: {}, Erro: {}", carga, job.getName(), e.getMessage(), e);
		}
	}

}