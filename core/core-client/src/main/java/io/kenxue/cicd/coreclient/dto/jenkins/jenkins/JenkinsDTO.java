package io.kenxue.cicd.coreclient.dto.jenkins.jenkins;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Data
@Accessors(chain = true)
public class JenkinsDTO extends CommonDTO {
    /**
     * jenkins uri
     */
    private String uri;
    /**
     * 别名
     */
    private String name;
    /**
     * 访问用户名
     */
    private String username;
    /**
     * 访问密码或秘钥
     */
    private String password;
    /**
     * 备注
     */
    private String remark;
}
