package io.wetake.backend.gateways.commentgateway.service;

import io.wetake.backend.gateways.commentgateway.client.CommentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CommentService {
  @Autowired private ApplicationContext context;

  public boolean addComment(
      String listId, String id, String ownerId, String ipAddress, String content) {
    CommentClient client = (CommentClient) this.context.getBean("commentClient");

    Boolean ans = false;
    try {
      ans = client.addComment(listId, id, ownerId, ipAddress, content);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ans;
  }

  public boolean editComment(String listId, String id, String content) {
    CommentClient client = (CommentClient) this.context.getBean("commentClient");

    Boolean ans = false;
    try {
      ans = client.editComment(listId, id, content);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ans;
  }

  public boolean deleteComment(String listId, String id) {
    CommentClient client = (CommentClient) this.context.getBean("commentClient");

    Boolean ans = false;
    try {
      ans = client.deleteComment(listId, id);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ans;
  }

  public boolean setVisibility(String listId, String id, Boolean visibility) {
    CommentClient client = (CommentClient) this.context.getBean("commentClient");

    Boolean ans = false;
    try {
      ans = client.setVisibility(listId, id, visibility);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ans;
  }
}
