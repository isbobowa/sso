package cas.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cas.dao.DomainDao;
import cas.entity.Domain;
import cas.service.DomainService;

import java.util.List;

/**
 * @Description 子系统（域名）管理业务层实现类
 */
@Service
public class DomainServiceimpl implements DomainService {

    @Autowired
    private DomainDao domainDao;

    @Override
    public List<Domain> selectAll() {
        return domainDao.selectList(null);
    }
}
