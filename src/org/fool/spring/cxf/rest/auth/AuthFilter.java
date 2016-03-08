package org.fool.spring.cxf.rest.auth;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Message message = JAXRSUtils.getCurrentMessage();
		AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
		if (policy == null) {
			login(requestContext);
		} else {
			String username = policy.getUserName();
			String password = policy.getPassword();
			if (!isAuthenticated(username, password)) {
				login(requestContext);
			}
		}
	}

	private void login(ContainerRequestContext requestContext) {
		// ʹ�� Basic ��֤
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
				.header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=Demo").build());
	}

	private boolean isAuthenticated(String username, String password) {
		// ģ���û������֤
		return username.equals("admin") && password.equals("123456");
	}
}