package cas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import cas.entity.Domain;

/**
 * @Description 子系统（域名）管理数据接口层
 */
@Mapper
@Repository
public interface DomainDao extends BaseMapper<Domain> {


}
