package com.shenlan.service.rocketmq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
//import com.easto.domain.bo.EastoGame;
//import com.easto.domain.bo.EastoPVStatistics;
//import com.easto.domain.bo.EastoUVStatistics;
//import com.easto.service.contentManage.EastoGameService;
//import com.easto.service.dataStatistics.EastoPVStatisticsService;
//import com.easto.service.dataStatistics.EastoUVStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.List;

/**
 * 业务监听实现Demo
 */
public class RocketMqMessageListenerImpl implements RocketMqMessageListener {
    protected Logger logger	= LoggerFactory.getLogger(getClass());

//    @Autowired
//    private EastoUVStatisticsService eastoUVStatisticsService;
//
//    @Autowired
//    private EastoPVStatisticsService eastoPVStatisticsService;
//
//    @Autowired
//    private EastoGameService eastoGameService;

    public boolean onMessage(List<MessageExt> messages, ConsumeConcurrentlyContext Context) {
        String ipv4 = "";
        Integer ip = 0;
        try {
            ipv4 = InetAddress.getLocalHost().getHostAddress();
            logger.info("ipv4======"+ipv4);
            ip = Integer.parseInt(ipv4.split("\\.")[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < messages.size(); i++) {
            Message msg = messages.get(i);
            logger.debug("======msg======" + msg.getBody());
            try {
//                if (msg.getTopic().equals("UVTOPIC")) {
//                    EastoUVStatistics eastoUVStatistics = JSON.parseObject(msg.getBody(),EastoUVStatistics.class);
//                    if(eastoUVStatistics.getUid()%2==ip%2){
//                        logger.info("----UVTOPIC.msg.getBody()----" + JSON.toJSONString(eastoUVStatistics));
//                        eastoUVStatisticsService.saveUVData2Redis(eastoUVStatistics);
//                    }
//
//                }
//                if (msg.getTopic().equals("PVTOPIC")) {
//                    EastoPVStatistics eastoPVStatistics = JSON.parseObject(msg.getBody(),EastoPVStatistics.class);
//                    if(eastoPVStatistics.getUid()%2==ip%2) {
//                        logger.info("----PVTOPIC.msg.getBody()----" + JSON.toJSONString(eastoPVStatistics));
//                        eastoPVStatisticsService.savePVData2Redis(eastoPVStatistics);
//                    }
//                }
//
//                // 游戏apk下载消息
//                if (msg.getTopic().equals("apkDownloadTopic")) {
//                    EastoGame eastoGame = JSON.parseObject(msg.getBody(),EastoGame.class);
//                    if(eastoGame.getUid()%2==ip%2) {
//                        logger.info("----apkDownloadTopic.msg.getBody()----" + JSON.toJSONString(eastoGame));
//                        eastoGameService.updateGameApkDownloadTimes(eastoGame);
//                    }
//                }
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
