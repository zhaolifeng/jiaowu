package com.shenlan.service.UserCenter;

import com.shenlan.api.userCenter.IUserCenterService;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.userCenter.UserInfoDao;
import com.shenlan.domain.bo.UserInfo;
import com.shenlan.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("userCenterService")

public class UserCenterService extends BaseService<UserInfo> implements IUserCenterService {

    @Autowired
    private UserInfoDao userInfoDao;

    public List<UserInfo> findByUser(UserInfo userInfo) {
        return userInfoDao.findByUser(userInfo);
    }

    /**
     * 根据用户名模糊查询用户，分页展示
     */
    public PageParameter queryUsers(PageParameter page, UserInfo userInfo) {
        return userInfoDao.queryUsers(page, userInfo);
    }

    /**
     * 根据用户名模糊查询用户，分页展示
     */
    public PageParameter queryUser(PageParameter page, Map parm) {
        return userInfoDao.queryUser(page, parm);
    }

    public UserInfo getUserById(Long id) {
        UserInfo userInfo = userInfoDao.selectById(id);
        return userInfo;
    }

    public int queryTotalUserCount() {
        return userInfoDao.queryTotalUserCount();
    }

    public int queryForbiddenTotalUserCount() {
        return userInfoDao.queryForbiddenTotalUserCount();
    }

    /**
     * 首页每日用户新增数统计
     *
     * @param map
     * @return
     */
    public List queryUserDayCount(Map map) {
        return userInfoDao.queryUserDayCount(map);
    }

    /**
     * 首页用户累计数统计
     *
     * @param map
     * @return
     */
    public List queryUserDayCounts(Map map) {
        return userInfoDao.queryUserDayCounts(map);
    }

    /**
     * 更新用户信息
     *
     * @param userInfo
     */
    @Transactional(readOnly = false)
    public void updateUserInfo(UserInfo userInfo) {
        this.userInfoDao.updateUser(userInfo);
    }
}
