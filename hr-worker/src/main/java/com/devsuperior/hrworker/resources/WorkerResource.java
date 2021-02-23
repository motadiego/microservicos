package com.devsuperior.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;

//Deveria pegar o valor atualizado da propriedade "test.config" definido no git
//spring.cloud.config.server.git.uri=https://github.com/motadiego/microservicos-configs
//@RefreshScope 
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	
	private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);
	
	
	@Value("${test.config}")
	private String testeConfig;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private WorkerRepository repository;
	
	
	@GetMapping
	private ResponseEntity<List<Worker>>  findAll(){
		List<Worker> list = repository.findAll();
		return ResponseEntity.ok(list);
 	}
	
	@GetMapping(value= "/{id}")
	private ResponseEntity<Worker> findById(@PathVariable Long id){
		Worker worker = repository.findById(id).get();
		return ResponseEntity.ok(worker);
 	}
	
	
	@GetMapping(value = "/configs")
	private ResponseEntity<Void>  getConfigs(){
		logger.info("CONFIG = " + testeConfig);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
