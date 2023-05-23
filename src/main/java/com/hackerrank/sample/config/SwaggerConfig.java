package com.hackerrank.sample.config;

import java.util.Optional;
import java.util.StringJoiner;

import javax.swing.text.html.Option;

import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    final Optional<BuildProperties> build;
    final Optional<GitProperties> git;

    public SwaggerConfig(
            final Optional<BuildProperties> build,
            final Optional<GitProperties> git
            ){
        this.build = build;
        this.git = git;
    }

    @Bean
    public Docket api() {
        String version = "1.0";
        if (build.isPresent() && git.isPresent()) {
            final StringJoiner joiner = new StringJoiner(",");
            joiner.add(build.get().getVersion())
                    .add(git.get().getShortCommitId())
                    .add(git.get().getBranch());
            version =  joiner.toString();
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(version))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .forCodeGeneration(true);
    }

    private ApiInfo apiInfo(final String version)  {
        final ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        return apiInfoBuilder
                .title("API - Person Service")
                .description("Persons Management")
                .version(version)
                .build();
    }
}
