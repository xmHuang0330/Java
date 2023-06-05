package com.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

  //样式加载不进来加一句话
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
  }



  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    //前端主页控制实现，可以加载样式
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/index.html").setViewName("index");
  }
}
