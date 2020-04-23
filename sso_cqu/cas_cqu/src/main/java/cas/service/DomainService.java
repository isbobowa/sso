package cas.service;

import org.springframework.transaction.annotation.Transactional;
import cas.entity.Domain;

import java.util.List;

/**
 * @Description 子系统（域名）管理业务层接口
 */
public interface DomainService {

    /**
     * 查询全部
     * @return list
     */
    @Transactional
    List<Domain> selectAll ();

}
