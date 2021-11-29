package io.wetake.backend.gateways.commentgateway.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.wetake.backend.gateways.commentgateway.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class MutationResolver implements GraphQLMutationResolver {
  @Autowired CommentService commentService;

  public boolean addComment(
      String listId, String id, String ownerId, String ipAddress, String content) {
    try {
      return commentService.addComment(listId, id, ownerId, ipAddress, content);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean editComment(String listId, String id, String content) {
    try {
      return commentService.editComment(listId, id, content);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean deleteComment(String listId, String id) {
    try {
      return commentService.deleteComment(listId, id);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean setVisibility(String listId, String id, Boolean visibility) {
    try {
      return commentService.setVisibility(listId, id, visibility);
    } catch (Exception e) {
      return false;
    }
  }
}
