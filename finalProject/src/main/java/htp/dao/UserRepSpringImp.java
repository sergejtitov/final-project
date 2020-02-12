package htp.dao;


import htp.dao.DAOinterfaces.UserRepository;
import htp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;

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
    public static final String IS_DELETED = "isDeleted";
    public static final String M_VALUE = "m_value";
    public static long userId;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepSpringImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @PostConstruct
    private void setId(){
        final String getMaxId = "select max(user_id) m_value from m_user";
        userId = jdbcTemplate.queryForObject(getMaxId, this::getLongValue);
    }
    private long getId() {
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
        user.setRole(set.getString(ROLE));
        user.setDeleted(set.getBoolean(IS_DELETED));
        return user;
    }

    private long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }

    private boolean loginExist(String login) {
        final String getLogin = "select count(*) m_value from m_user where login = :login";
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("login", login);
            long numberLogins = namedParameterJdbcTemplate.queryForObject(getLogin, params, this::getLongValue);
            return numberLogins > 0;
        } catch (NullPointerException e) {
            return true;
        }
    }
    private boolean idExists (Long id){
        final String getId = "select count(*) m_value from m_user where user_id = :user_id";
        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("user_id", id);
            long numberId = namedParameterJdbcTemplate.queryForObject(getId, param,this::getLongValue);
            return numberId >0;
        } catch (NullPointerException e){
            return true;
        }
    }

    @Override
    @Transactional  (rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Long save(User item) {
        if (loginExist(item.getLogin())) {
            System.out.println("Such login exists");
            return 0L;
        } else {
            final String createQuery = "INSERT INTO m_user (user_id, login, password, created, changed, role, isdeleted) " +
                    "VALUES (:user_id, :login, :password, :created, :changed, :role, :isdeleted);";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("user_id", getId());
            params.addValue("login", item.getLogin());
            params.addValue("password", item.getPassword());
            params.addValue("created", item.getCreated());
            params.addValue("changed", item.getChanged());
            params.addValue("role", item.getRole());
            params.addValue("isdeleted", item.isDeleted());

            namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"user_id"});

            return Objects.requireNonNull(keyHolder.getKey()).longValue();
        }
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public User update(User item) {
        final String updateUser = "update m_user set login = :login, password = :password, changed = :changed where user_id = :user_id";
        if (idExists(item.getUserId())){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", item.getLogin());
        params.addValue("password", item.getPassword());
        params.addValue("changed", item.getChanged());
        params.addValue("user_id", item.getUserId());
        namedParameterJdbcTemplate.update(updateUser,params);
        return findById(item.getUserId());
        } else {
            System.out.println("Such User doesn't exists");
            return null;
        }
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public void delete(Long id) {
        final String deleteUser = "delete from m_user where user_id = :user_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", id);
        if (!idExists(id)) {
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
            params.addValue("isdeleted", true);
            params.addValue("user_id", id);
            namedParameterJdbcTemplate.update(updateUser, params);
            } else {
            System.out.println("Such User doesn't exists");
            }
    }

    @Override
    public User findById(Long id) {
        final String findById = "select * from m_user where m_user.user_id = :user_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", id);
        if (!idExists(id)){
            System.out.println("Such ID doesn't exists");
            return null;
        } else {
            return namedParameterJdbcTemplate.queryForObject(findById, params, this::fillUser);
        }
    }

    @Override
    public User findByLogin(String login) {
        final String findById = "select * from m_user where m_user.login = :login";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", login);
        try {
            return namedParameterJdbcTemplate.queryForObject(findById, params, this::fillUser);
        } catch (Exception e){
            System.out.println("User with such login doesn't exist");
            return null;
        }

    }

    @Override
    public List<User> findAll() {
        final String findAll = "select* from m_user";
        return namedParameterJdbcTemplate.query(findAll, this::fillUser);
    }
}
