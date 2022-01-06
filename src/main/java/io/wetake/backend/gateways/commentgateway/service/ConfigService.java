package io.wetake.backend.gateways.commentgateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.wetake.backend.gateways.commentgateway.client.ConfClient;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ConfigService {
  @Autowired private ApplicationContext context;

  private String getConfig() throws IOException {
    ConfClient client = (ConfClient) this.context.getBean("confClient");
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    return client.getConf();
  }

  public String getByKey(String key) throws IOException {
    JSONObject j = new JSONObject(this.getConfig());
    return j.getString(key);
  }
}
