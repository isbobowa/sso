package cas.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cas.entity.User;
import cas.service.UserService;
import cas.util.Result.Result;
import cas.util.Result.ResultCodeEnum;

/**
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public Result insertUser (User user) {
        boolean boo = userService.insertUser(user);
        if (boo) {
            return new Result(ResultCodeEnum.INSERTSUCCESS,"添加成功");
        }
        return new Result(ResultCodeEnum.INSERTFAIL,"用户已存在");
    }
}
