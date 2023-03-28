package by.bntu.Kuzmenok.Teachcourse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MvsConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String UPLOAD_PATH;
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("util/login");
        registry.addViewController("/").setViewName("startPage");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/coursePage").setViewName("subject/coursePage");
        registry.addViewController("/event").setViewName("user/event");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + UPLOAD_PATH + File.separator);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
