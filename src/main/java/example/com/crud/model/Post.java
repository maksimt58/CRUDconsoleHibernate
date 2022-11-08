package example.com.crud.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "content")
    private String content;
    @Column (name = "create_date")
    private Timestamp date_created;
    @Column (name = "update_date")
    private Timestamp date_updated;
    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Writer writer;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_labels",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")

    )
    private List<Label> labels = new ArrayList<>();

    public Post() {
    }

    public Post(Long id, String content, PostStatus postStatus, Writer writer) {
        this.id = id;
        this.content = content;
        this.postStatus = postStatus;
        this.writer = writer;
    }

    public Post(String content, List<Label> labels, PostStatus postStatus) {
        this.content = content;
        this.labels = labels;
        this.postStatus = postStatus;
    }
    public Post(Long id, String content, PostStatus postStatus) {
        this.id = id;
        this.content = content;
        this.postStatus = postStatus;
    }

    public Post(Long id, String content, PostStatus postStatus, List<Label> labels) {
        this.id = id;
        this.content = content;
        this.labels = labels;
        this.postStatus = postStatus;
    }

    public Post(Long id, String content, PostStatus postStatus, List<Label> labels, Writer writer) {
        this.id = id;
        this.content = content;
        this.labels = labels;
        this.postStatus = postStatus;
        this.writer = writer;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated() {
        return date_created;
    }

    public void setCreated(Timestamp created) {
        this.date_created = created;
    }

    public Timestamp getUpdated() {
        return date_updated;
    }

    public void setUpdated(Timestamp updated) {
        this.date_updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) && content.equals(post.content) && Objects.equals(labels, post.labels) && postStatus == post.postStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, date_created, date_updated, labels, postStatus);
    }


    public String toStringAllData() {
        return "=====" + "\n" +
                "Post: " + " \n" +
                "=====" + "\n" +
                "id = " + id + ", \n" +
                "Post Name = " + content  + ", \n" +
                "Writer = " + writer.getFirstName()+" "+ writer.getLastName() + ", \n" +
                "Created = " + date_created + ", \n" +
                "Updated = " + date_updated + ", \n" +
                "TAGS: " + "\n" + listToString(labels) + "\n" +
                "Post Status = " + postStatus + "\n" +
                "----------------------------------------------------------------------" + "\n";
    }

    private String listToString(List<?> list) {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }
        return result;
    }

    @Override
    public String toString() {
        return "=====" + "\n" +
                "Post: " + " \n" +
                "=====" + "\n" +
                "id = " + id + ", \n" +
                "Post Name = " + content  + ", \n" +
                "Post Status = " + postStatus + "\n" +
                "----------------------------------------------------------------------" + "\n";
    }
}
