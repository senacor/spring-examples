package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Config server demonstration.
 * The default strategy for locating property sources is to clone a git repository (at spring.cloud.config.server.git.uri)
 * and use it to initialize a mini SpringApplication. The mini-application’s Environment is used to enumerate property
 * sources and publish them via a JSON endpoint. In the demonstration instead of a git repo, the 'configrepo' folder on the
 * classpath is used.
 * Here worth mentioning:
 * configuration is
 * 		1-application name specific(client's spring.application.name property)
 * 	    2-profile specific
 * 	    3-git label specific
 * application.yml/.properties in the git repository(or configrepo directory in this demo) keeps the configuration shared by
 * all services. Service specific configuration can override this or we can make sure that it can not be overridden by client.
 * the configuration of config-client service is published at : http://localhost:8888/config-client/master
 * 'development' profile endpoint at: http://localhost:8888/config-client/development
 */

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}