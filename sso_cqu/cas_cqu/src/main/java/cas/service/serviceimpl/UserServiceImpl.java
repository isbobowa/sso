package cas.service.serviceimpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cas.dao.UserDao;
import cas.entity.User;
import cas.service.UserService;
import cas.util.AesUtil;

import java.util.List;

/**
 * @Description 用户业务类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean insertUser(User user) {
        List<User> UserDb = userDao.selectList(
                new EntityWrapper<User>().eq("username", user.getUsername())
        );
        if (UserDb.size() > 0) {
            return false;
        }
        userDao.insert(user);
        return true;
    }

    @Override
    public User login(String username, String password) {
        User[] user = {null};
        List<User> userList = userDao.selectList(
                new EntityWrapper<User>().eq("username", username)
        );
        if (userList.size() > 0) {
            AesUtil aesUtil = new AesUtil(password);  //添加密钥
            String encyptPassword = aesUtil.encrypt();//加密
            //加盐密码验证
            userList.forEach((User u) ->{
                String encyPassSalt = encyptPassword+u.getSalt();
                aesUtil.setContent(encyPassSalt);   //添加密钥
                String result = aesUtil.encrypt();  //加密
                log.info("密码是：");
                log.info(result);
                log.info(u.getPassword());
                if (result != null && result.equals(u.getPassword())) {
                    user[0] = u;
                    log.info("校验成功");
                }
            });

        }
        return user[0];
    }
}
