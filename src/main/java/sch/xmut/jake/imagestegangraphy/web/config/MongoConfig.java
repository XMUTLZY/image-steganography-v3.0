package sch.xmut.jake.imagestegangraphy.web.config;

import com.mongodb.MongoClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jake.lin on 2020/1/3
 */
@Configuration
public class MongoConfig {
    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions
                .builder()
                .maxConnectionIdleTime(60000)
                .build();
    }
}
