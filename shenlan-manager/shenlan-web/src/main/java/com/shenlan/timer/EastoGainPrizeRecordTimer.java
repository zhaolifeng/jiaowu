package com.shenlan.timer;

//import com.easto.api.activeManage.IEastoCampaignService;
//import com.easto.api.contentManage.IEastoAirlineResService;
//import com.easto.api.contentManage.IEastoResService;
import org.apache.log4j.Logger;

/**
 * Created by 赵利锋 on 2016/7/11.
 */
public class EastoGainPrizeRecordTimer {
    protected static final Logger log= Logger.getLogger(EastoGainPrizeRecordTimer.class);

//    @Autowired
//    private IEastoResService eastoResService;
//
//    @Autowired
//    private IEastoAirlineResService eastoAirlineResService;
//
//    @Autowired
//    private IEastoCampaignService eastoCampaignService;

    private int timeout;
    
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 内容发布时：（当前时间与内容发布时间与下线时间进行比较）
     * 1、当前时间 在 内容发布时间与内容下线时间之间，状态更新为 上线状态
     * 2、当前时间 在 内容下线时间之后，状态更新为  下线状态
     */
    public void airlineResTimer(){
        log.info("-----contentTimer begin-----");
        
//        //1、更新待上线内容状态为上线
//        eastoAirlineResService.pubAirlineResTimer();
//        //2、更新发布内容状态为下线
//        eastoAirlineResService.offlineAirlineResTimer();
        
        log.info("-----contentTimer end-----");
    }
    
    /**
     * 活动发布时：（当前时间与活动开始时间与活动结束时间比较），对于活动还要判断是否已经添加了奖品。
     * 1、当前时间 在 活动开始时间与活动结束时间之间，状态更新为 发布状态
     * 2、当前时间 在 活动结束时间之后，状态 更新为  下线状态
     */
    public void campaignTimer(){
        log.info("-----campaignTimer begin-----");
        
        //1、更新 已经添加了奖品的待发布的活动为发布状态
        
        //2、更新在线的活动为下线状态
        
        log.info("-----campaignTimer end-----");
    }
    
}
