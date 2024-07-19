package smile.office.groupware.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public EmpLoginInterceptor empLoginInterceptor(){
        return new EmpLoginInterceptor();
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login", "/admin/error");


        registry.addInterceptor(empLoginInterceptor())
                .addPathPatterns("/emp/attendance/**")
                .addPathPatterns("/message/**")
                .addPathPatterns("/board/**")
                .excludePathPatterns();



    }



}
