package htp.dao.spring_impl;


import htp.dao.UserRepository;
import htp.entities.db_entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


@Repository
@ComponentScan("htp")
public class UserRepSpringImp implements UserRepository {
    public static final String USER_ID = "user_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String CREATED = "created";
    public static final String CHANGED = "changed";
    public static final String ROLE = "role";
    public static final String IS_DELETED = "isdeleted";
    public static final String M_VALUE = "m_value";
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";
    public static final Long UNIQUE_USER = 1L;
    public static Long userId;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepSpringImp(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        final String getMaxId = "select max(user_id) m_value from m_user";
        userId = jdbcTemplate.queryForObject(getMaxId, this::getLongValue);
    }


    private Long getId() {
        userId++;
        return userId;
    }


    private User fillUser(ResultSet set, int i) throws SQLException {
        User user = new User();
        user.setUserId(set.getLong(USER_ID));
        user.setLogin(set.getString(LOGIN));
        user.setPassword(set.getString(PASSWORD));
        user.setCreated(set.getTimestamp(CREATED));
        user.setChanged(set.getTimestamp(CHANGED));
        //user.setRole(set.getString(ROLE));
        user.setIsdeleted(set.getBoolean(IS_DELETED));
        return user;
    }

    private Long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }

    private boolean loginExist(String login) {
        try {
            final String getLogin = "select count(*) m_value from m_user where login = :login";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(LOGIN, login);
            Long numberLogins = namedParameterJdbcTemplate.queryForObject(getLogin, params, this::getLongValue);
            return Objects.equals(numberLogins, UNIQUE_USER);
        } catch (NullPointerException e) {
            return true;
        }
    }
    private boolean idExists (Long id){
        try {
            final String getId = "select count(*) m_value from m_user where user_id = :user_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue(USER_ID, id);
            Long numberId = namedParameterJdbcTemplate.queryForObject(getId, param,this::getLongValue);
            return Objects.equals(numberId, UNIQUE_USER);
        } catch (NullPointerException e){
            return true;
        }
    }
    private boolean idNotExists (Long id){
        return !idExists(id);
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public User save(User item) {
        if (loginExist(item.getLogin())) {
            System.out.println("Such login exists");
            return new User();
        } else {
            final String createQuery = "INSERT INTO m_user (user_id, login, password, created, changed, role, isdeleted) " +
                    "VALUES (:user_id, :login, :password, :created, :changed,/* :role,*/ :isdeleted);";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(USER_ID, getId());
            params.addValue(LOGIN, item.getLogin());
            params.addValue(PASSWORD, item.getPassword());
            params.addValue(CREATED, item.getCreated());
            params.addValue(CHANGED, item.getChanged());
            //params.addValue(ROLE, item.getRole());
            params.addValue(IS_DELETED, false);

            namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{USER_ID});

            return findById(Objects.requireNonNull(keyHolder.getKey()).longValue());
        }
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public User update(User item) {
        final String updateUser = "update m_user set login = :login, password = :password, changed = :changed where user_id = :user_id";
        if (idExists(item.getUserId())){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LOGIN, item.getLogin());
        params.addValue(PASSWORD, item.getPassword());
        params.addValue(CHANGED, item.getChanged());
        params.addValue(USER_ID, item.getUserId());
        namedParameterJdbcTemplate.update(updateUser,params);
        return findById(item.getUserId());
        } else {
            System.out.println("Such User doesn't exists");
            return new User();
        }
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public void delete(Long id) {
        final String deleteUser = "delete from m_user where user_id = :user_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(USER_ID, id);
        if (idNotExists(id)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameterJdbcTemplate.update(deleteUser, params);
        }
    }
    @Override
    @Transactional  (rollbackFor = Exception.class)
    public void fakeDelete(Long id) {
        if (idExists(id)) {
            final String updateUser = "update m_user set isdeleted = :isdeleted where user_id = :user_id";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(IS_DELETED, true);
            params.addValue(USER_ID, id);
            namedParameterJdbcTemplate.update(updateUser, params);
            } else {
            System.out.println("Such User doesn't exists");
            }
    }

    @Override
    public User findById(Long id) {
        final String findById = "select * from m_user where m_user.user_id = :user_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(USER_ID, id);
        if (idNotExists(id)){
            System.out.println("Such ID doesn't exist");
            return new User();
        } else {
            return namedParameterJdbcTemplate.queryForObject(findById, params, this::fillUser);
        }
    }

    @Override
    public User findByLogin(String login) {
        final String findById = "select * from m_user where m_user.login = :login";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LOGIN, login);
        if (loginExist(login)){
            System.out.println("Such Login exists");
            return new User();
        } else {
            return namedParameterJdbcTemplate.queryForObject(findById, params, this::fillUser);
        }
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        final String findAll = "select* from m_user order by user_id limit :limit offset :offset";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LIMIT, limit);
        params.addValue(OFFSET, offset);
        return namedParameterJdbcTemplate.query(findAll, params, this::fillUser);
    }
}
