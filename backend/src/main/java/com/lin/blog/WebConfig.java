package com.lin.blog;
import org.springframework.context.annotation.Configuration; import org.springframework.web.servlet.config.annotation.*; import java.nio.file.Paths;
@Configuration public class WebConfig implements WebMvcConfigurer { @Override public void addResourceHandlers(ResourceHandlerRegistry registry){registry.addResourceHandler("/uploads/**").addResourceLocations(Paths.get("uploads").toAbsolutePath().normalize().toUri().toString());} }
