package htp.services;

import htp.dao.CreditInfoRepository;
import htp.dao.hibernate_Impl.CreditInfoHibernateImpl;
import htp.entities.db_entities.CreditInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditInfoService implements CreditInfoRepository {

    CreditInfoRepository creditInfoDao;

    public CreditInfoService(CreditInfoHibernateImpl creditInfoDao) {
        this.creditInfoDao = creditInfoDao;
    }

    @Override
    public List<CreditInfo> findCreditInfosByPersonalNumber(String personalNumber) {
        return creditInfoDao.findCreditInfosByPersonalNumber(personalNumber);
    }

    @Override
    public CreditInfo save(CreditInfo item) {
        return creditInfoDao.save(item);
    }

    @Override
    public CreditInfo update(CreditInfo item) {
        return creditInfoDao.update(item);
    }

    @Override
    public void delete(Long id) {
        creditInfoDao.delete(creditInfoDao.findById(id).getInfoId());
    }

    @Override
    public CreditInfo findById(Long id) {
        return creditInfoDao.findById(id);
    }
}
