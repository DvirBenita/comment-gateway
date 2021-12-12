package io.wetake.backend.gateways.commentgateway.service;

import io.wetake.backend.gateways.commentgateway.client.CommentClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentServiceTest {
    @Autowired CommentService commentService;
    @MockBean
    CommentClient commentClient;

    @Test
    void addComment() throws IOException {
        Mockito.when(commentClient.addComment("test ","tesst","test","test","test")).thenReturn(true);
    Assertions.assertTrue(commentService.addComment("test ","tesst","test","test","test"));
    }

    @Test
    void editComment() throws IOException {
        Mockito.when(commentClient.editComment("test ","tesst","test")).thenReturn(true);
        Assertions.assertTrue(commentService.editComment("test ","tesst","test"));
    }

    @Test
    void deleteComment() throws IOException {
        Mockito.when(commentClient.deleteComment("test ","tesst")).thenReturn(true);
        Assertions.assertTrue(commentService.deleteComment("test ","tesst"));
    }

    @Test
    void setVisibility() throws IOException {
        Mockito.when(commentClient.setVisibility("test ","tesst",true)).thenReturn(true);
        Assertions.assertTrue(commentService.setVisibility("test ","tesst",true));
    }
}