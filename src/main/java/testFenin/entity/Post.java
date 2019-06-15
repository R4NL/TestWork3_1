package testFenin.entity;

import java.util.Objects;

public class Post {

    private Long id;

    private String text;

    private String data;

    private String author;

    public Post() {
    }

    public Post(String text, String data, String author) {
        this.text = text;
        this.data = data;
        this.author = author;
    }

    public Post(Long id, String text, String data, String author) {
        this.id = id;
        this.text = text;
        this.data = data;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return  Objects.equals(text, post.text) &&
                Objects.equals(data, post.data) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, data, author);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", data='" + data + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
