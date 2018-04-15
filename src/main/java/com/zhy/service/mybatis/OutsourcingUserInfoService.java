package com.zhy.service.mybatis;

import com.zhy.model.outsourcing.OutsourcingUserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 18:57 2018/3/24
 * Describe: 外包人员信息
 */
@Service
public interface OutsourcingUserInfoService {

    /**
     * 外包人员信息登记表
     * @return 所有正在进行中或报名中的外包人员登记信息
     */
    List<OutsourcingUserInfo> selectAllOutsourcingUserInfoByMangerPhone(String mangerPhone);

    /**
     * 通过手机号后的接包用户者信息
     * @param phone 手机号
     * @return 接包用户者信息
     */
    OutsourcingUserInfo getUserInfoByPhone(String phone);
}
