package testFenin.dao;

import testFenin.entity.Post;

import java.util.List;

public interface PostDAO {
    //create
    void add(Post post);

    //read
    List<Post> getAll();

    Post getById(Long id);

    //update
    void update(Post post);

    //remove
    void remove(Post post);


}
