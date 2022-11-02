package com.devcloud.mall.service;

import com.devcloud.mall.domain.Admin;

/**
 * @author 吴员外
 * @date 2022/10/25 17:20
 */

public interface AdminService {


    String login(Admin admin);

    void logout();

}
