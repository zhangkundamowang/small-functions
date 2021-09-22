package com.zk.mybatisplus.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.annotation.PostConstruct;
import java.util.regex.Pattern;

/**
 * swagger 配置
 */
@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean enable;

    /**
     * 不需要过滤的路径集合
     */
    private String exclude = "/swagger-resources,/webjars/*,/doc.html,/v2/*";
    /**
     * 匹配不需要过滤路径的正则表达式
     */
    private Pattern pattern;

    private static SwaggerConfig swagger;

    @Bean
    public Docket createRestApi() {
        // 添加 token 和 accounts 参数验证
        /*ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Authorization").description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();
        pars.add(ticketPar.build());*/

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                ////扫描该包下面的API注解,接口使用@ApiIgnore,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
        //.globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口服务文档")
                .description("接口文档")
                .termsOfServiceUrl("https://home.cnblogs.com/u/wffzk")//这里配置的是服务网站，我写的是我的博客园站点~欢迎关注~
                .version("1.0")
                .build();
    }

    // 获取路径过滤器
    public static Pattern getPattern() {
        return swagger.pattern;
    }

    // 初始化路径过滤器
    @PostConstruct
    public void init() {
        if (pattern == null) {
            pattern = Pattern.compile(getRegStr(exclude));
            log.info("初始化路径过滤器正则表达式：{}", pattern.pattern());
        }
        swagger = this;
    }

    /**
     * 将传递进来的不需要过滤得路径集合的字符串格式化成一系列的正则规则
     *
     * @param str 不需要过滤的路径集合，以分号进行分割
     * @return 正则表达式规则
     */
    private String getRegStr(String str) {
        if (StringUtils.isNotBlank(str)) {
            String[] excludes = str.split(",");
            int length = excludes.length;
            for (int i = 0; i < length; i++) {
                String tmpExclude = excludes[i];
                //对点、反斜杠和星号进行转义
                tmpExclude = tmpExclude.replace("\\", "\\\\").replace(".", "\\.").replace("*", ".*");

                tmpExclude = "^" + tmpExclude + "$";
                excludes[i] = tmpExclude;
            }
            return StringUtils.join(excludes, "|");
        }
        return str;
    }
}
