package io.kenxue.cicd.adapter.rest.kubernetes;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/14:27
 * @Description:
 */
@RestController
@Api(tags = "k8s授权模块",description = "授权登录k8s")
@RequestMapping("kubernetes/auth")
public class AuthController {
}
