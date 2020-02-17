package htp.dao.SpringImpl;

import htp.dao.DAOinterfaces.ApplicantRepository;
import htp.entities.Applicant;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@ComponentScan("htp")
public class ApplicantRepSpringImpl implements ApplicantRepository {
    public static final String APPLICANT_ID = "applicant_id";
    public static final String PERSONAL_NUMBER = "personal_number";
    public static final String FIRST_NAME = "first_name";
    public static final String SECOND_NAME = "second_name";
    public static final String PATRONYMIC = "patronymic";
    public static final String TYPE_OF_APPLICANT = "type_of_applicant";
    public static final String DATE_OF_BIRTHDAY = "date_of_birthday";
    public static final String INCOME = "income";
    public static final String INCOME_CURRENCY = "income_currency";
    public static final String SEX = "sex";
    public static final String EXPERIENCE = "experience";
    public static final String MARITAL_STATUS = "marital_status";
    public static final String EDUCATION = "education";
    public static final String CHILDREN_QUANTITY = "children_quantity";
    public static final String APPLICATION_ID = "application_id";
    public static final String M_VALUE = "m_value";

    public static long applicantID;

    private NamedParameterJdbcTemplate namedParameter;

    public ApplicantRepSpringImpl(JdbcTemplate jdbc) {
        namedParameter = new NamedParameterJdbcTemplate(jdbc);
        final String getMaxId = "select max(applicant_id) m_value from m_applicant";
        applicantID = jdbc.queryForObject(getMaxId, this::getLongValue);
    }

    private long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }
    private long getId() {
        applicantID++;
        return applicantID;
    }

    @Override
    public List<Applicant> findAll(int limit, int offset) {
        final String getAll = "select * from m_applicant order by applicant_id limit :limit offset :offset";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("limit", limit);
        params.addValue("offset", offset);
        return namedParameter.query(getAll, params, this::fillApplicant);
    }

    @Override
    public List<Applicant> findByApplication(Long application_id) {
        try {
            final String getByApplication = "select * from m_applicant where application_id = :application_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("application_id", application_id);
            return namedParameter.query(getByApplication, param, this::fillApplicant);
        } catch (Exception e){
            System.out.println("Such Application doesn't exist");
            return null;
        }
    }

    @Override
    @Transactional  (rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Long save(Applicant item) {
        final String createQuery = "INSERT INTO m_applicant (applicant_id, first_name, second_name, patronymic, " +
                "type_of_applicant, date_of_birthday, income, income_currency, sex, experience, marital_status, education, children_quantity, personal_number, application_id) " +
                "VALUES (:applicant_id, :first_name, :second_name, :patronymic, :type_of_applicant, :date_of_birthday," +
                " :income, :income_currency, :sex, :experience, :marital_status, :education, :children_quantity, :personal_number, :application_id);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("applicant_id", getId());
        params.addValue("first_name", item.getFirstName());
        params.addValue("second_name", item.getSecondName());
        params.addValue("patronymic", item.getPatronymic());
        params.addValue("type_of_applicant", item.getTypeOfApplicant());
        params.addValue("date_of_birthday", item.getBirthdayDate());
        params.addValue("income", item.getIncome());
        params.addValue("income_currency", item.getIncomeCurrency());
        params.addValue("sex", item.getSex());
        params.addValue("experience", item.getExperience());
        params.addValue("marital_status", item.getMaritalStatus());
        params.addValue("education", item.getEducation());
        params.addValue("children_quantity", item.getChildrenQuantity());
        params.addValue("personal_number", item.getPersonalNumber());
        params.addValue("application_id", item.getApplicationId());

        namedParameter.update(createQuery, params, keyHolder, new String[]{"applicant_id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    @Transactional  (rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Applicant update(Applicant item) {
        final String updateAppl = "update m_applicant set first_name = :first_name, second_name = :second_name, patronymic = :patronymic, type_of_applicant = :type_of_applicant," +
                "date_of_birthday = :date_of_birthday, income = :income, income_currency = :income_currency, sex = :sex, experience = :experience, marital_status = :marital_status," +
                "education = :education, children_quantity = :children_quantity, personal_number = :personal_number where applicant_id = :applicant_id";
        if (idExists(item.getApplicantId())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("first_name", item.getFirstName());
            params.addValue("second_name", item.getSecondName());
            params.addValue("patronymic", item.getPatronymic());
            params.addValue("type_of_applicant", item.getTypeOfApplicant());
            params.addValue("date_of_birthday", item.getBirthdayDate());
            params.addValue("income", item.getIncome());
            params.addValue("income_currency", item.getIncomeCurrency());
            params.addValue("sex", item.getSex());
            params.addValue("experience", item.getExperience());
            params.addValue("marital_status", item.getMaritalStatus());
            params.addValue("education", item.getEducation());
            params.addValue("children_quantity", item.getChildrenQuantity());
            params.addValue("personal_number", item.getPersonalNumber());
            params.addValue("applicant_id", item.getApplicantId());
            namedParameter.update(updateAppl,params);
            return findById(item.getApplicantId());
        } else {
            System.out.println("Such Applicant doesn't exists");
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        final String deleteApplicant = "delete from m_applicant where applicant_id = :applicant_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("applicant_id", id);
        if (!idExists(id)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameter.update(deleteApplicant, params);
        }

    }

    @Override
    public Applicant findById(Long id) {
        final String getApplicant = "select * from m_applicant where applicant_id = :applicant_id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("applicant_id", id);
        try {
            return namedParameter.queryForObject(getApplicant,param,this::fillApplicant);
        } catch (Exception e) {
            System.out.println("Such Applicant doesn't exist");
            return null;
        }
    }


    private Applicant fillApplicant(ResultSet set, int i) throws SQLException {
        Applicant applicant = new Applicant();
        applicant.setApplicantId(set.getLong(APPLICANT_ID));
        applicant.setFirstName(set.getString(FIRST_NAME));
        applicant.setSecondName(set.getString(SECOND_NAME));
        applicant.setPatronymic(set.getString(PATRONYMIC));
        applicant.setTypeOfApplicant(set.getInt(TYPE_OF_APPLICANT));
        applicant.setBirthdayDate(set.getTimestamp(DATE_OF_BIRTHDAY));
        applicant.setIncome(set.getLong(INCOME));
        applicant.setIncomeCurrency(set.getString(INCOME_CURRENCY));
        applicant.setSex(set.getString(SEX));
        applicant.setExperience(set.getInt(EXPERIENCE));
        applicant.setExperience(set.getInt(MARITAL_STATUS));
        applicant.setEducation(set.getInt(EDUCATION));
        applicant.setChildrenQuantity(set.getInt(CHILDREN_QUANTITY));
        applicant.setPersonalNumber(set.getString(PERSONAL_NUMBER));
        applicant.setApplicationId(set.getLong(APPLICATION_ID));
        return applicant;
    }
    private boolean idExists (Long id){
        final String getId = "select count(*) m_value from m_applicant where applicant_id = :applicant_id";
        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("applicant_id", id);
            long numberId = namedParameter.queryForObject(getId, param,this::getLongValue);
            return numberId >0;
        } catch (NullPointerException e){
            return true;
        }
    }
}
