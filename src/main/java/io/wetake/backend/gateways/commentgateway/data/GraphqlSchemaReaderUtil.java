package io.wetake.backend.gateways.commentgateway.data;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public final class GraphqlSchemaReaderUtil {

  public static String getSchemaFromFileName(final String filename) throws IOException {
    System.out.println(filename);
    return new String(
        GraphqlSchemaReaderUtil.class
            .getClassLoader()
            .getResourceAsStream("graphql/" + filename + ".graphql")
            .readAllBytes());
  }
}
