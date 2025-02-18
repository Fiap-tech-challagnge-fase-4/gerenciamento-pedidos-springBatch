package br.com.fiap.springbatch.service.impl;

import org.springframework.stereotype.Service;

import br.com.fiap.springbatch.gateway.JobGateway;
import br.com.fiap.springbatch.model.Carga;
import br.com.fiap.springbatch.service.SalvarCarga;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SalvarCargaImpl implements SalvarCarga {

	private final JobGateway jobGateway;
	
	@Override
	public void salvar(Carga carga) {
		jobGateway.execute(carga);
	}
}
