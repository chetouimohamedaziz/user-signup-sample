package sample.config;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

// XXX Fongo is not intended for production use
@Configuration
public class FongoConfig extends AbstractMongoConfiguration {

  @Override protected String getDatabaseName() {
    return "sample";
  }

  @Bean
  @Override public Mongo mongo() {
    return new Fongo(getDatabaseName()).getMongo();
  }

}
