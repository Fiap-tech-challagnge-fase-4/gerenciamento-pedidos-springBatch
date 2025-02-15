package br.com.fiap.springBatch.gateway.springbatch;

import br.com.fiap.springBatch.exception.ErroAoProcessarJobException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.fiap.springBatch.gateway.JobGateway;
import br.com.fiap.springBatch.model.Carga;

@Component
public class SpringBatchGateway implements JobGateway{

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
			System.out.println(e.getMessage());
			throw new ErroAoProcessarJobException("Erro ao processar o job: " + e.getMessage());

		}

	}

}