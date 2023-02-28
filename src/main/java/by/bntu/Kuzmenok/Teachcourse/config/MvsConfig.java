package by.bntu.Kuzmenok.Teachcourse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvsConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("util/login");
        registry.addViewController("/").setViewName("startPage");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/mathPage").setViewName("subject/mathPage");
        registry.addViewController("/event").setViewName("user/event");
    }
}
