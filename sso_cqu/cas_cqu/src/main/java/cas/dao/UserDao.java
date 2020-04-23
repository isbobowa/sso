package cas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import cas.entity.User;

/**
 * @Description 用户数据接口层
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<User>{



}
