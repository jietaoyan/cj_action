package com.bccm.zuulservice;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableZuulProxy
@EnableEurekaClient
@EnableSwaggerButler
@SpringBootApplication
public class ZuulserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulserviceApplication.class, args);
	}



	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 允许cookies跨域
		config.addAllowedOrigin("*");// 允许向该服务器提交请求的URI，*表示全部允许。。这里尽量限制来源域，比如http://xxxx:8080 ,以降低安全风险。。
		config.addAllowedHeader("*");// 允许访问的头信息,*表示全部
		config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
		config.addAllowedMethod("*");// 允许提交请求的方法，*表示全部允许，也可以单独设置GET、PUT等
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);

	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}




//	/**
//	 * 文件上传配置
//	 *
//	 * @return MultipartConfigElement
//	 */
//	@Bean
//	public MultipartConfigElement multipartConfigElement(
//			@Value("${multipart.maxFileSize}") String maxFileSize,
//			@Value("${multipart.maxRequestSize}") String maxRequestSize) {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		// 单个文件最大
//		factory.setMaxFileSize(maxFileSize);
//		// 设置总上传数据总大小
//		factory.setMaxRequestSize(maxRequestSize);
//		return factory.createMultipartConfig();
//	}


}
