package io.wetake.backend.gateways.commentgateway.client;

import io.wetake.backend.gateways.commentgateway.data.GraphqlRequestBody;
import io.wetake.backend.gateways.commentgateway.data.GraphqlSchemaReaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class ConfClient {
  public static final String configClientPort = "9090";
  public static final String URL = "http://localhost:" + configClientPort + "/graphql/";
  private final String url;

  public ConfClient(@Value(URL) String url) {
    this.url = url;
  }

  public String getConf() throws IOException {
    WebClient webClient = WebClient.builder().build();

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("getConfig");
    String variables = GraphqlSchemaReaderUtil.getSchemaFromFileName("variablesGetConfig");

    graphQLRequestBody.setQuery(query);

    graphQLRequestBody.getQuery();

    JSONObject jo =
        new JSONObject(
            Objects.requireNonNull(
                webClient
                    .post()
                    .uri(this.url)
                    .bodyValue(graphQLRequestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block()));

    JSONObject jo1 = new JSONObject(jo.get("data").toString());
    String jo2 = "";
    jo2 = jo1.getString("get");
    return jo2;
  }
}
