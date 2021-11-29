package io.wetake.backend.gateways.commentgateway;

import io.wetake.backend.gateways.commentgateway.resolver.MutationResolver;
import io.wetake.backend.gateways.commentgateway.resolver.QueryResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommentGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(CommentGatewayApplication.class, args);
  }

  @Bean
  public QueryResolver query() {
    return new QueryResolver();
  }

  @Bean
  public MutationResolver mutation() {
    return new MutationResolver();
  }
}
