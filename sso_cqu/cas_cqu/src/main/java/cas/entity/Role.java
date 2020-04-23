package cas.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 角色实体类
 */
@Data
@TableName("role")
public class Role implements Serializable {

    private int id;
    /**
     * 用户角色名称
     */
    private String roleName;
    /**
     * 用户角色描述
     */
    private String describe;

    public Role(String roleName, String describe) {
        this.roleName = roleName;
        this.describe = describe;
    }

    public Role() {}
}
