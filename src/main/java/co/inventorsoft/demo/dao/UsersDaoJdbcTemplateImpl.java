package co.inventorsoft.demo.dao;

import co.inventorsoft.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Component
public class UsersDaoJdbcTemplateImpl implements UsersDao{

    private final JdbcTemplate template;

    private Map<Long, User> usersMap = new HashMap<>();

    private final String SQL_SELECT_USERS = "SELECT * FROM user_table1";

    private final String SQL_SELECT_ALL_BY_FIRST_NAME =
            "SELECT * FROM user_table1 WHERE first_name = ?";

    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM user_table1 WHERE id = ?";

    private final String SQL_INSERT_USER = "" +
            "INSERT INTO user_table1(first_name, last_name) VALUES (?, ?)";

    private final String SQL_DELETE_BY_ID = "DELETE from user_table WHERE id = ?";

    private final String SQL_UPDATE_BY_ID = "UPDATE user_table SET frist_name = ?2, last_name = ?3 WHERE id = ?1";

    @Autowired
    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .build();


    @Override
    public List<User> findAllByFirstName(String firstName) {
        return template.query(SQL_SELECT_ALL_BY_FIRST_NAME, userRowMapper, firstName);
    }

    @Override
    public Optional<User> find(Long id) {
        List<User> result = template.query(SQL_SELECT_BY_ID, userRowMapper, id);

        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    @Override
    public void save(User model) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstName", model.getFirstName());
        params.put("lastName", model.getLastName());
        template.update(SQL_INSERT_USER, params);
    }

    @Override
    public void update(User model) {
        template.update(SQL_UPDATE_BY_ID, model.getId(), model.getFirstName(), model.getLastName());
    }

    @Override
    public void delete(Long id) {
        template.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public List<User> findAll() {
        List<User> result =  template.query(SQL_SELECT_USERS, userRowMapper);
        usersMap.clear();
        return result;
    }
}
