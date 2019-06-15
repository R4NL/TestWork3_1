package testFenin.servise.DAOImp;

import testFenin.bl.Util;
import testFenin.dao.PostDAO;
import testFenin.entity.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostService extends Util implements PostDAO {
    private Connection connection = getConnection();

    public PostService() {
    }

    public static PostService get(){
        return new PostService();
    }

    @Override
    public void add(Post post) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO test.public.testtable(TEXT, DATA, AUTHOR) VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, post.getText());
            preparedStatement.setString(2, post.getData());
            preparedStatement.setString(3, post.getAuthor());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(preparedStatement);
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> postList = new ArrayList<>();
        String sql = "SELECT ID, TEXT, DATA, AUTHOR FROM test.public.testtable";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("ID"));
                post.setText(resultSet.getString("TEXT"));
                post.setData(resultSet.getString("DATA"));
                post.setAuthor(resultSet.getString("AUTHOR"));
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(statement);
        }
        return postList;
    }


    @Override
    public Post getById(Long id) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, TEXT, DATA, AUTHOR FROM test.public.testtable WHERE ID=?";
        Post post = new Post();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            post.setId(resultSet.getLong("ID"));
            post.setText(resultSet.getString("TEXT"));
            post.setData(resultSet.getString("DATA"));
            post.setAuthor(resultSet.getString("AUTHOR"));
//            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(preparedStatement);
        }
        return post;
    }

    @Override
    public void update(Post post) {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE TEST.PUBLIC.TESTTABLE SET TEXT=?,DATA=?,AUTHOR=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, post.getText());
            preparedStatement.setString(2, post.getData());
            preparedStatement.setString(3, post.getAuthor());
            preparedStatement.setLong(4, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(preparedStatement);
        }
    }

    @Override
    public void remove(Post post) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM TEST.PUBLIC.TESTTABLE WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(preparedStatement);
        }
    }

    private void closeAll(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
