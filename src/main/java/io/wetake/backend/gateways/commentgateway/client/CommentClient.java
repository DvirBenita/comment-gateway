package io.wetake.backend.gateways.commentgateway.client;

import io.wetake.backend.gateways.commentgateway.data.GraphqlRequestBody;
import io.wetake.backend.gateways.commentgateway.data.GraphqlSchemaReaderUtil;
import io.wetake.backend.gateways.commentgateway.service.ConfigService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Objects;

@Service
public class CommentClient {
  public static String commentServicePort;
  public static String url;
  @Autowired ConfigService configService;

  public Boolean addComment(
      String listId, String id, String ownerId, String ipAddress, String content)
      throws IOException {
    commentServicePort = this.configService.getByKey("commentServicePort");
    url = "http://localhost:" + commentServicePort + "/graphql/";
    WebClient webClient = WebClient.builder().build();

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("addComment");
    String variables = GraphqlSchemaReaderUtil.getSchemaFromFileName("variablesAddComment");

    graphQLRequestBody.setQuery(query);
    variables = variables.replace("listIdTemp", listId);
    variables = variables.replace("idTemp", id);
    variables = variables.replace("ownerIdTemp", ownerId);
    variables = variables.replace("ipAddressTemp", ipAddress);
    graphQLRequestBody.setVariables(variables.replace("contentTemp", content));
    JSONObject j =
        new JSONObject(
            Objects.requireNonNull(
                webClient
                    .post()
                    .uri(url)
                    .bodyValue(graphQLRequestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block()));
    return true;
  }

  public Boolean editComment(String listId, String id, String content) throws IOException {
    commentServicePort = this.configService.getByKey("commentServicePort");
    url = "http://localhost:" + commentServicePort + "/graphql/";
    WebClient webClient = WebClient.builder().build();

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("editComment");
    String variables = GraphqlSchemaReaderUtil.getSchemaFromFileName("variablesEditComment");

    graphQLRequestBody.setQuery(query);
    variables = variables.replace("listIdTemp", listId);
    variables = variables.replace("idTemp", id);
    graphQLRequestBody.setVariables(variables.replace("contentTemp", content));
    JSONObject j =
        new JSONObject(
            Objects.requireNonNull(
                webClient
                    .post()
                    .uri(url)
                    .bodyValue(graphQLRequestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block()));
    return true;
  }

  public Boolean deleteComment(String listId, String id) throws IOException {
    commentServicePort = this.configService.getByKey("commentServicePort");
    url = "http://localhost:" + commentServicePort + "/graphql/";
    WebClient webClient = WebClient.builder().build();

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("deleteComment");
    String variables = GraphqlSchemaReaderUtil.getSchemaFromFileName("variablesDeleteComment");

    graphQLRequestBody.setQuery(query);
    variables = variables.replace("listIdTemp", listId);
    graphQLRequestBody.setVariables(variables.replace("idTemp", id));
    JSONObject j =
        new JSONObject(
            Objects.requireNonNull(
                webClient
                    .post()
                    .uri(url)
                    .bodyValue(graphQLRequestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block()));

    return true;
  }

  public Boolean setVisibility(String listId, String id, Boolean visibility) throws IOException {
    commentServicePort = this.configService.getByKey("commentServicePort");
    url = "http://localhost:" + commentServicePort + "/graphql/";
    WebClient webClient = WebClient.builder().build();

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("setVisibility");
    String variables = GraphqlSchemaReaderUtil.getSchemaFromFileName("variablesSetVisibility");

    graphQLRequestBody.setQuery(query);
    variables = variables.replace("listIdTemp", listId);
    variables = variables.replace("idTemp", id);
    graphQLRequestBody.setVariables(variables.replace("visibilityTemp", visibility.toString()));
    System.out.println(graphQLRequestBody.getVariables());
    JSONObject j =
        new JSONObject(
            Objects.requireNonNull(
                webClient
                    .post()
                    .uri(url)
                    .bodyValue(graphQLRequestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block()));

    return true;
  }
}
