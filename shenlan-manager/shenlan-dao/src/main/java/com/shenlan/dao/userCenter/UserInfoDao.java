package com.shenlan.dao.userCenter;

import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.dao.BaseDao;
import com.shenlan.domain.bo.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserInfoDao extends BaseDao<UserInfo> {

    public List<UserInfo> findByUser(UserInfo userInfo) {
        List<UserInfo> userInfoList = this.getSqlSession().selectList(namespace + ".findByUser", userInfo);
        return userInfoList.isEmpty() ? null : userInfoList;
    }

    public PageParameter queryUsers(PageParameter page, UserInfo userInfo) {
        PageBoundsProxy pbp = new PageBoundsProxy(page);
        List<UserInfo> userList = this.getSqlSession().selectList(namespace + ".queryUsers", userInfo, pbp.getPageBounds());
        pbp.setPageParameter(userList.isEmpty() ? null : userList);
        return page;
    }

    public PageParameter queryUser(PageParameter page, Map parm) {
        PageBoundsProxy pbp = new PageBoundsProxy(page);
        List<UserInfo> userList = this.getSqlSession().selectList(namespace + ".queryUser", parm, pbp.getPageBounds());
        pbp.setPageParameter(userList.isEmpty() ? null : userList);
        return page;
    }

    public int updateUser(UserInfo userInfo) {
        int result = this.getSqlSession().update(namespace + ".updateUserInfo", userInfo);
        return result;
    }

    public int queryTotalUserCount() {
        Integer result = this.getSqlSession().selectOne(namespace + ".queryTotalUserCount");
        if (result == null) {
            result = 0;
        }
        return Integer.parseInt(result + "");
    }

    public int queryForbiddenTotalUserCount() {
        Integer result = this.getSqlSession().selectOne(namespace + ".queryForbiddenTotalUserCount");
        if (result == null) {
            result = 0;
        }
        return Integer.parseInt(result + "");
    }

    /**
     * 首页每日用户新增数统计
     *
     * @param map
     * @return
     */
    public List queryUserDayCount(Map map) {
        return this.getSqlSession().selectList(this.namespace + ".queryUserDayCount", map);
    }

    /**
     * 首页用户累计数统计
     *
     * @param map
     * @return
     */
    public List queryUserDayCounts(Map map) {
        return this.getSqlSession().selectList(this.namespace + ".queryUserDayCounts", map);
    }
}