package com.shenlan.api.userCenter;

import com.shenlan.api.IBaseService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.bo.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户中心管理接口
 *
 * @author YUAN
 */
public interface IUserCenterService extends IBaseService<UserInfo> {

    public List<UserInfo> findByUser(UserInfo userInfo);

    PageParameter queryUsers(PageParameter page, UserInfo userInfo);

    PageParameter queryUser(PageParameter page, Map parm);

    public void updateUserInfo(UserInfo userInfo);

    UserInfo getUserById(Long id);

    public int queryTotalUserCount();

    public int queryForbiddenTotalUserCount();

    /**
     * 首页每日用户新增数统计
     */
    public List queryUserDayCount(Map map);

    /**
     * 首页用户累计数统计
     */
    public List queryUserDayCounts(Map map);

}
