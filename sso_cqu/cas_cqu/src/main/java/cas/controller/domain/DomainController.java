package cas.controller.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cas.entity.Domain;
import cas.service.DomainService;
import cas.util.Result.Result;
import cas.util.Result.ResultCodeEnum;

import java.util.List;

/**
 * @Description 子系统(域名)管理控制层
 */
@Slf4j
@RestController
@RequestMapping("/domain")
public class DomainController {

    @Autowired
    private DomainService domainService;

    @GetMapping
    public Result<List<Domain>> getAllDomain () {
        List<Domain> domainList = domainService.selectAll();
        return new Result<>(ResultCodeEnum.SELECTSUCCESS,null,domainList);
    }

}
