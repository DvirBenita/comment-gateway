package io.wetake.backend.gateways.commentgateway.resolver;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.wetake.backend.gateways.commentgateway.service.CommentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

@GraphQLTest
class MutationResolverIT {

  @MockBean CommentService commentService;
  @Autowired private GraphQLTestTemplate graphQLTestTemplate;

  @Test
  void addComment() throws IOException {
    Mockito.when(commentService.addComment("test", "test", "test", "test", "test"))
        .thenReturn(true);
    GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/addComment.graphql");
    Assertions.assertThat(response.isOk()).isTrue();
    Assertions.assertThat(response.get("$.data.addComment")).isNotNull();
    Assertions.assertThat(response.get("$.data.addComment")).isEqualTo("true");
  }

  @Test
  void editComment() throws IOException {
    Mockito.when(commentService.editComment("test", "test", "test")).thenReturn(true);
    GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/editComment.graphql");
    Assertions.assertThat(response.isOk()).isTrue();
    Assertions.assertThat(response.get("$.data.editComment")).isNotNull();
    Assertions.assertThat(response.get("$.data.editComment")).isEqualTo("true");
  }

  @Test
  void deleteComment() throws IOException {
    Mockito.when(commentService.deleteComment("test", "test")).thenReturn(true);
    GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/deleteComment.graphql");
    Assertions.assertThat(response.isOk()).isTrue();
    Assertions.assertThat(response.get("$.data.deleteComment")).isNotNull();
    Assertions.assertThat(response.get("$.data.deleteComment")).isEqualTo("true");
  }

  @Test
  void setVisibility() throws IOException {
    Mockito.when(commentService.setVisibility("test", "test", false)).thenReturn(true);
    GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/setVisibility.graphql");
    Assertions.assertThat(response.isOk()).isTrue();
    Assertions.assertThat(response.get("$.data.setVisibility")).isNotNull();
    Assertions.assertThat(response.get("$.data.setVisibility")).isEqualTo("true");
  }
}
