package com.learning.oauth.client;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@SpringBootApplication
public class SpringCloudSecurityOauthPasswordFlowClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSecurityOauthPasswordFlowClientApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		ResourceOwnerPasswordResourceDetails dtls = new ResourceOwnerPasswordResourceDetails();
		dtls.setAccessTokenUri("http://localhost:8084/services/oauth/token");
		dtls.setClientAuthenticationScheme(AuthenticationScheme.header);
		dtls.setClientId("test-client-id");
		dtls.setClientSecret("test-client-secret");
		dtls.setScope(Arrays.asList("toll_read"));
		dtls.setUsername("user");
		dtls.setPassword("password");
		
		OAuth2RestTemplate t = new OAuth2RestTemplate(dtls);
		String s = t.getForObject("http://localhost:8083/services/reports", String.class);
		System.out.println("Res: " + s);
	}
}
