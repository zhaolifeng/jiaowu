package com.shenlan.timer;

//import com.easto.api.activeManage.IEastoCampaignService;
//import com.easto.api.activeManage.IEastoPrizeCampaignService;
//import com.easto.api.advertisingManage.IEastoAdPublishService;
//import com.easto.api.activeManage.IEastoPrizeRecordService;
//import com.easto.api.contentManage.IEastoAirlineResService;
//import com.easto.api.integralCenter.IIntegralExchRuleService;
//import com.easto.api.integralCenter.IIntegralTaskService;
//import com.easto.domain.bo.EastoCampaign;
//import com.easto.domain.bo.EastoPrizeCampaign;
//import com.easto.domain.bo.EastoPrizeRecord;
//import CampaignIntegeralUser;
//import CampaignPrizeRecord;
//import com.easto.service.dataStatistics.EastoPVStatisticsService;
//import com.easto.service.dataStatistics.EastoUVStatisticsService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

/**
 * Created by 赵利锋 on 2016/7/11.
 */
public class EastoBusinessTimer {
    protected static final Logger log= Logger.getLogger(EastoBusinessTimer.class);
//    private ArrayUtils
//    @Autowired
//    private IEastoAirlineResService eastoAirlineResService;
//
//    @Autowired
//    private IEastoCampaignService eastoCampaignService;
//
//    @Autowired
//    private IEastoPrizeCampaignService eastoPrizeCampaignService;
//
//    @Autowired
//    private IEastoAdPublishService eastoAdPublishService;
//
//    @Autowired
//    private IEastoPrizeRecordService eastoPrizeRecordService;
//
//    @Autowired
//    private IIntegralTaskService integralTaskService;
//
//    @Autowired
//    private EastoUVStatisticsService eastoUVStatisticsService;
//
//    @Autowired
//    private EastoPVStatisticsService eastoPVStatisticsService;

//    private int timeout;
//
//    public void setTimeout(int timeout) {
//        this.timeout = timeout;
//    }
//
//    /**
//     * 内容发布时：（当前时间与内容发布时间与下线时间进行比较）
//     * 1、当前时间 在 内容发布时间与内容下线时间之间，状态更新为 上线状态
//     * 2、当前时间 在 内容下线时间之后，状态更新为  下线状态
//     */
//    public void airlineResTimer(){
//        log.info("-----contentTimer begin-----");
//
//        //1、更新待上线内容状态为上线
//        eastoAirlineResService.pubAirlineResTimer();
//        //2、更新发布内容状态为下线
//        eastoAirlineResService.offlineAirlineResTimer();
//
//        log.info("-----contentTimer end-----");
//    }
//
//    /**
//     * 活动发布时：（当前时间与活动开始时间与活动结束时间比较），对于活动还要判断是否已经添加了奖品。
//     * 1、当前时间 在 活动开始时间与活动结束时间之间，状态更新为 发布状态
//     * 2、当前时间 在 活动结束时间之后，状态 更新为  下线状态
//     */
//    public void campaignTimer(){
//        log.info("-----campaignTimer begin-----");
//
//        //1、更新 已经添加了奖品的待发布的活动为发布状态
//        eastoCampaignService.onlineCampaignTimer();
//        //2、更新在线的活动为下线状态
//        eastoCampaignService.offlineCampaignTimer();
//
//        log.info("-----campaignTimer end-----");
//    }
//
//    /**
//     * 广告发布时：（当前时间与广告发布时间与下线时间进行比较）
//     * 1、当前时间 在 广告发布时间与广告下线时间之间，状态更新为 上线状态
//     * 2、当前时间 在 广告下线时间之后，状态更新为下线状态
//     */
//    public void adPublishTimer(){
//        log.info("-----advertisementTimer begin-----");
//
//        //1、更新待上线广告状态为上线
//        eastoAdPublishService.pubAdPublishTimer();
//        //2、更新发布广告状态为下线
//        eastoAdPublishService.offAdPublishResTimer();
//
//        log.info("-----advertisementTimer end-----");
//    }
//
//    /**
//     * 积分任务上下线定时器
//     */
//    public void integralTaskTimer(){
//        log.info("-----integralTaskTimer begin-----");
//
//        //1、更新待上线任务状态为上线
//        integralTaskService.publishIntegralTaskTimer();
//        //2、更新发布任务状态为下线
//        integralTaskService.offlineIntegralTaskTimer();
//
//        log.info("-----integralTaskTimer end-----");
//    }
//
//    /**
//     * 活动结束后，插入表easto_prize_record获奖记录
//     *
//     * 根据活动投放的奖品数量插入记录数
//     */
//    public void saveGainPrizeReccord(){
//        log.info("-----saveGainPrizeReccord begin-----");
//
//        //查询已经结束的活动发出的奖品数量
//        List<CampaignPrizeRecord> campaignPrizeCount = eastoPrizeRecordService.getCampaignPrizeCount();
//        log.info("=======campaignPrizeCount=======" + JSON.toJSONString(campaignPrizeCount));
//
//        //查询出 活动id与活动提供的奖品数量
//        if(campaignPrizeCount.size() > 0){
//            for(int i=0;i<campaignPrizeCount.size();i++){
//                long campaignId = campaignPrizeCount.get(i).getCampaignId();
//                long prizeCounts = campaignPrizeCount.get(i).getPrizeCounts();
//
//                //查询某一活动下前prizeCounts个记录
//                Map map =  new HashMap();
//                map.put("campaignId",campaignId);
//                map.put("prizeCounts",prizeCounts);
//                List<CampaignIntegeralUser> campaignIntegeralUserList = eastoPrizeRecordService.findGameTopn(map);
//                log.info("======用户活动积分表中，某一活动下的记录数campaignIntegeralUserList=======" + campaignIntegeralUserList.size());
//
//                //查询某一活动下的奖品
//                List<EastoPrizeCampaign> eastoPrizeCampaignList = eastoPrizeRecordService.getPrizeInCampaign(campaignId);
//                log.info("======某一活动下的奖品eastoPrizeCampaignList=======" + JSON.toJSONString(eastoPrizeCampaignList));
//                int flag = 0;
//                if(eastoPrizeCampaignList.size()>0){
//                    for(int j=0;j<eastoPrizeCampaignList.size();j++){
//                        Integer prizeLevel = eastoPrizeCampaignList.get(j).getPrizeLevel();
//                        long prizeCount = eastoPrizeCampaignList.get(j).getPrizeCount();
//                        //插入prizeCount条获奖记录到表easto_prize_record
//                        for(int m=flag;m<(prizeCount+flag);m++){
//                            if(m<campaignIntegeralUserList.size()){
//                                long userId = campaignIntegeralUserList.get(m).getUserId();
//                                String userName = campaignIntegeralUserList.get(m).getUserName();
//
//                                EastoCampaign eastoCampaign = eastoCampaignService.getCampaignInfoById(campaignId);
//                                log.info("----活动" + campaignId + "下的，活动详情----" + JSON.toJSONString(eastoCampaign));
//                                String campaignName = eastoCampaign.getCampaignName();
//                                Map LevelMap = new HashMap();
//                                LevelMap.put("campaignId",campaignId);
//                                LevelMap.put("prizeLevel",prizeLevel);
//                                List<EastoPrizeCampaign> eastoPrizeCampaign = eastoPrizeCampaignService.getPrizeCampaignLevelInfo(LevelMap);
//                                log.info("----getPrizeCampaignLevelInfo----" + JSON.toJSONString(eastoPrizeCampaign));
//                                long prizeId = 0l;
//                                String prizeName = "";
//                                if(eastoPrizeCampaign.size()>0){
//                                    prizeId = eastoPrizeCampaign.get(0).getPrizeId();
//                                    prizeName = eastoPrizeCampaign.get(0).getPrizeName();
//                                }else{
//                                    log.info("活动编码" + campaignId + "活动名称" + campaignName + "下等级为" + prizeLevel + "的奖品为空" );
//                                }
//                                String prizeStatus="wdj_002";
//                                long adownerId = eastoCampaign.getAdownerId();
//                                String gameName = campaignIntegeralUserList.get(m).getGameName();
//                                EastoPrizeRecord eastoPrizeRecord = new EastoPrizeRecord();
//
//                                eastoPrizeRecord.setUserId(userId);
//                                eastoPrizeRecord.setUserName(userName);
//                                eastoPrizeRecord.setCampaignId(campaignId);
//                                eastoPrizeRecord.setCampaignName(campaignName);
//                                eastoPrizeRecord.setPrizeLevel(prizeLevel);
//                                eastoPrizeRecord.setAdownerId(adownerId);
//                                eastoPrizeRecord.setPrizeId(prizeId);
//                                eastoPrizeRecord.setPrizeName(prizeName);
//                                eastoPrizeRecord.setPrizeStatus(prizeStatus);
//
//                                log.info("----eastoPrizeRecord----" + JSON.toJSONString(eastoPrizeRecord));
//                                eastoPrizeRecordService.savePrizeRecord(eastoPrizeRecord);
//                                flag += 1;
//                            }
//                        }
//                    }
//                }else{
//                	log.info("活动编码" + campaignId + "下没有奖品");
//                }
//            }
//        }
//
//        log.info("-----saveGainPrizeReccord end-----");
//    }
//
//    /**
//     * pv、uv定时从缓存持久化到数据库中
//     */
//    public void dataStatisticsTimer(){
//        log.info("-----dataStatisticsTimer begin-----");
//        eastoUVStatisticsService.saveUVData();
//        eastoPVStatisticsService.savePVData();
//        log.info("-----dataStatisticsTimer end-----");
//    }
}
