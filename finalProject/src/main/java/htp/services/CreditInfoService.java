package htp.services;

import htp.dao.CreditInfoRepository;
import htp.dao.hibernate_Impl.CreditInfoHibernateImpl;
import htp.domain.model.CreditInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreditInfoService implements CreditInfoRepository {

    CreditInfoRepository creditInfoDao;

    public CreditInfoService(CreditInfoHibernateImpl creditInfoDao) {
        this.creditInfoDao = creditInfoDao;
    }

    @Override
    public CreditInfo findCreditInfoByApplicationId(Long applicationId) {
        return creditInfoDao.findCreditInfoByApplicationId(applicationId);
    }

    @Override
    public List<CreditInfo> findCreditInfosByPersonalNumber(String personalNumber) {
        return creditInfoDao.findCreditInfosByPersonalNumber(personalNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreditInfo save(CreditInfo item) {
        return creditInfoDao.save(item);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
