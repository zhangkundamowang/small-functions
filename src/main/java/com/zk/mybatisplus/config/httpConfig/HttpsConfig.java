package com.zk.mybatisplus.config.httpConfig;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpsConfig {

    @Value("${openHttps}")
    private String openHttps;

    @Value("${server.port}")
    private Integer port;

    @Bean
    //配置http某个端口自动跳转https
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                if ("open".equals(openHttps)) {
                    SecurityConstraint securityConstraint = new SecurityConstraint();
                    securityConstraint.setUserConstraint("CONFIDENTIAL");
                    SecurityCollection collection = new SecurityCollection();
                    collection.addPattern("/*");
                    collection.addMethod(DEFAULT_PROTOCOL);
                    securityConstraint.addCollection(collection);
                    context.addConstraint(securityConstraint);
                }
            }
        };
        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //监听的http端口
        connector.setPort(88);
        connector.setSecure(false);
        //跳转的https端口
        connector.setRedirectPort(port);
        return connector;
    }

}
