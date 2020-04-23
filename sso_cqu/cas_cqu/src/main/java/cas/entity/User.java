package cas.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @Description 用户实体类：13个属性
 */
@Data
@TableName("user")
public class User implements Serializable {

    private int id;
    /**
     * 用户名：邮箱
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 加密所用盐
     */
    private String salt;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 年级
     */
    private String grades;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 角色，职位：数据库使用外键，被依赖表是角色表
     */
    private int roleId;
    /**
     * 个人介绍
     */
    private String remark;

    public User(String username, String name, String salt, String password, String sex, String phone, String grades, String className, int roleId, String remark) {
        this.username = username;
        this.name = name;
        this.salt = salt;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.grades = grades;
        this.className = className;
        this.roleId = roleId;
        this.remark = remark;
    }

    public User() {}

    @Override
    public String toString() {
        return JSON.parse("{" +
                "id:" + id +
                ", username:'" + username + '\'' +
                ", name:'" + name + '\'' +
                ", salt:'" + salt + '\'' +
                ", password:'" + password + '\'' +
                ", sex:'" + sex + '\'' +
                ", phone:'" + phone + '\'' +
                ", grades:'" + grades + '\'' +
                ", className:'" + className + '\'' +
                ", roleId:" + roleId +
                ", remark:'" + remark + '\''+
                "}").toString();
    }
}
