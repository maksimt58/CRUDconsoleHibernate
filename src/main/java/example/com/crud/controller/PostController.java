package example.com.crud.controller;

import example.com.crud.model.Label;
import example.com.crud.model.Post;
import example.com.crud.model.PostStatus;
import example.com.crud.model.Writer;
import example.com.crud.service.PostService;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostController {
    private final PostService postService;
    private final LabelController labelController;
    private final WriterController writerController;

    public PostController() {
        this.postService = new PostService();
        this.labelController = new LabelController();
        this.writerController = new WriterController();
    }

    public List<Post> onShowAll() {
        return postService.getAll();
    }

    public void onCreate(String content, PostStatus postStatus, List<Label> labelList, Writer writer) {

        Post post = new Post(null, content, postStatus, labelList, writer);

        postService.save(post);
    }

    public Post getById(Long id) throws SQLException {
        return postService.getById(id);
    }

    public Post onUpdate(Long id, String content, PostStatus postStatus, Writer writer) {
        Post post = new Post(id, content, postStatus, writer);
        return postService.update(post);
    }

    public boolean onDelete(Long id) {
        return postService.delete(id);
    }

    public List<Label> getAllLabels() {
        return labelController.onShowAll();
    }

    public List<Writer> getAllWriters() {
        return writerController.onShowAll();
    }
}
