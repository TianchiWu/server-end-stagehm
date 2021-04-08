package com.example.Config;

import com.example.web.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //监听器相关
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { DataConfig.class };
    }
    //视图控制器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
