package cn.karent.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wan on 2017/3/16.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean filterRegister() {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new LoginFilter());
        frb.addUrlPatterns("/admin/*");
        return frb;
    }

}
