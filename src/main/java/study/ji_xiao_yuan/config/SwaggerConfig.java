package study.ji_xiao_yuan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Persolute
 * @version 1.0
 * @description Swagger配置类
 * @email 1538520381@qq.com
 * @date 2023/12/6 22:06
 */
@Configuration
@EnableSwagger2 // 开启 swagger2 的自动配置
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment) {
        // 设置swagger开启的环境
        Profiles profiles = Profiles.of("dev", "test");
        boolean flag = environment.acceptsProfiles(profiles);

        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        // 基本信息设置
        ApiInfo apiInfo = new ApiInfoBuilder()
                .contact(new Contact(
                                "计小猿", // 文档发布者所在企业
                                "https://xxx", // 文档发布者的网站地址、企业网站
                                "1538520381@qq.com" // 文档发布者的电子邮箱
                        )
                )
                .title("计小猿开发文档")
                .description("计小猿开发文档")
                .version("1.0")
                .build();
        docket.apiInfo(apiInfo);

        // 配置包扫描路径
        docket.select()
                .apis(RequestHandlerSelectors.basePackage("study.ji_xiao_yuan.cotroller"));

        return docket.enable(flag);
    }
}
