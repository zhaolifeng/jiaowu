/*内容管理 start*/
/** 添加内容 **/
function addContent(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加内容";
    diag.URL = "./openContentDialog";
    diag.show();
}

/** 编辑内容 **/
function editRes(resourceId){

    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "编辑内容";
    diag.URL = "./getResById?id=" + resourceId;
    diag.show();
}

/** 发布内容 **/
function pubContent(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 600;
    diag.Title = "发布内容";
    diag.URL = "./openPubResDialog";
    diag.show();
}

/** 内容发布详情 **/
function pubDetail(batchId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "内容发布详情";
    diag.URL = "./pubDetail?id="+batchId;
    diag.show();
}

/** 内容发布重新上线 **/
function pubAirlineResAgain(batchId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 200;
    diag.Title = "内容再次发布";
    diag.URL = "./openPubResAgainDialog?id="+batchId;
    diag.show();
}

/*内容管理 end*/

/*文章管理 start*/
function addArticle(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加文章";
    diag.URL = "./openArticleDialog";
    diag.show();
}


function pubArticle(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "发布文章";
    diag.URL = "./openPubArticleDialog";
    diag.show();
}

function articleDCJH(articleId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 250;
    diag.Title = "设置典藏精华";
    diag.URL = "./openArticleDCJHDialog?articleId=" + articleId;
    diag.show();
}

/*文章管理 end*/

/*话题管理 start*/
/** 添加话题 **/
function addTopic(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加话题";
    diag.URL = "./openTopicDialog";
    diag.show();
}


/** 发布话题 **/
function pubTopic(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 600;
    diag.Title = "发布话题";
    diag.URL = "./openPubTopicDialog";
    diag.show();
}


/** 旅游话题发布重新上线 **/
function pubAirlineTopicAgain(batchId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 200;
    diag.Title = "话题再次发布";
    diag.URL = "./openPubTopicAgainDialog?id="+batchId;
    diag.show();
}

/** 添加话题评论 **/
function addTopicReply(topicId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 500;
    diag.Title = "添加评论";
    diag.URL = "./openAddTopicReplyDialog?topicId="+topicId;
    diag.show();
}
/*话题管理 end*/

/*专题管理 start*/
function addProject(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加文章";
    diag.URL = "./openProjectDialog";
    diag.show();
}

function addArticle2Project(projectId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加文章到专题";
    diag.URL = "./addArticle2ProjectDialog?projectId=" + projectId;
    diag.show();
}

function pubProject(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "发布文章";
    diag.URL = "./openPubProjectDialog";
    diag.show();
}

function projectDCJH(projectId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 250;
    diag.Title = "设置典藏精华";
    diag.URL = "./openProjectDCJHDialog?projectId=" + projectId;
    diag.show();
}

/** 编辑专题 **/
function editProject(projectId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "话题再次发布";
    diag.URL = "./openEditProjectDialog?projectId="+projectId;
    diag.show();
}

/*专题管理 end*/

/*游戏下载管理 start*/
/** 添加游戏安装包 **/
function addGameApk(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加游戏安装包";
    diag.URL = "./openGameApkDialog";
    diag.show();
}

/** 发布游戏安装包 **/
function pubGameApkDialog(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 400;
    diag.Title = "发布游戏安装包";
    diag.URL = "./openPubGameApkDialog";
    diag.show();
}

/** 编辑游戏安装包 **/
function editGameApk(resourceId){

    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "编辑内容";
    diag.URL = "./getGameApkById?id=" + resourceId;
    diag.show();
}

/** 游戏安装包发布详情 **/
function pubGameApkDetail(batchId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 300;
    diag.Title = "内容发布详情";
    diag.URL = "./pubGameApkDetail?id="+batchId;
    diag.show();
}

/** 游戏安装包发布重新上线 **/
function pubGameApkAgain(batchId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 200;
    diag.Title = "内容再次发布";
    diag.URL = "./openpubGameApkAgainDialog?id="+batchId;
    diag.show();
}
/*添加游戏安装包 end*/

/*添加广告管理 start*/
function addAdPublish(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加广告";
    diag.URL = "./addAdPublish";
    diag.show();
}

/*内容管理 end*/

/*广告管理 start*/
/*添加广告主管理 start*/
function addAdOwner(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加广告主";
    diag.URL = "./addAdOwner";
    diag.show();
}

/*添加广告位管理 start*/
function addAdPosition(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加页面";
    diag.URL = "./addAdPosition";
    diag.show();
}


/*添加广告物料 start*/
function addAdResource(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加物料";
    diag.URL = "./addAdResource";
    diag.show();
}

/**修改广告位**/
function updateAdPosition(taskId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "页面修改";
    diag.URL = "./getAdPositionById?id="+taskId;
    diag.show();
}

function userdetail(taskId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "用户详情";
    diag.URL = "./getUserById?id="+taskId;
    diag.show();
}


function adDetail(taskId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "广告详情";
    diag.URL = "./getAdPublishById?id="+taskId;
    diag.show();
}
/**修改广告主**/
function updateAdOwner(taskId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "广告主修改";
    diag.URL = "./getAdOwnerById?id="+taskId;
    diag.show();
}

/**编辑广告物料**/
function updateAdResource(taskId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "物料编辑";
    diag.URL = "./getAdResourceById?id="+taskId;
    diag.show();
}

/**添加奖品**/
function addPrize(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "添加奖品";
    diag.URL = "./addPrizeDialog";
    diag.show();
}

/**编辑奖品**/
function editPrize(prizeId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "编辑奖品";
    diag.URL = "./editPrizeDialog?id="+prizeId;
    diag.show();
}

/**获奖详情**/
function gainPrizeDetail(recordId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 450;
    diag.Title = "编辑奖品";
    diag.URL = "./gainPrizeDetail?id="+recordId;
    diag.show();
}

/*广告管理 end*/

/*活动管理 start*/
/**添加活动**/
function addCampaign(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 500;
    diag.Title = "";
    diag.URL = "./addCampaignDialog";
    diag.show();
}

/**奖品活动详情**/
function prizeCampaignDetail(campaignId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 500;
    diag.Title = "";
    diag.URL = "./prizeCampaignDetail?id="+campaignId;
    diag.show();
}

/**添加位置**/
function addLoaction(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 280;
    diag.Title = "添加位置";
    diag.URL = "openDialogLocation.html";
    diag.show();
}

/**添加活动类型**/
function addAcType(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 280;
    diag.Title = "添加活动类型";
    diag.URL = "openDialogAcType.html";
    diag.show();
}

/**为待上线的活动添加奖品**/
function addPrize2Campaign(campaignId,adownerId,campaignName){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 400;
    diag.Title = "添加奖品到活动";
    diag.URL = "./addPrize2CampaignDialog?id="+campaignId +"&adownerId=" + adownerId;
    diag.show();
}

/**为已添加奖品的待上线活动 编辑奖品**/
function editPrizeCampaign(campaignId,adownerId,campaignName){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 400;
    diag.Title = "编辑活动下奖品";
    diag.URL = "./editPrizeCampaignDiolog?id="+campaignId +"&adownerId=" + adownerId;
    diag.show();
}


/** 活动发布重新上线 **/
function onlineCampaignAgain(campaignId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 200;
    diag.Title = "活动再次发布";
    diag.URL = "./onlineCampaignAgainDialog?id="+campaignId;
    diag.show();
}

/*活动管理 end*/

/*积分管理 start*/
/**添加积分任务**/
function addIntegral(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "添加任务";
    diag.URL = "./openTaskDialog";
    diag.show();
}

/**编辑积分任务**/
function editIntegralTask(taskId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "编辑积分任务";
    diag.URL = "./getTaskInfoById?id="+taskId;
    diag.show();
}

/** 积分任务重新上线 **/
function pubIntegralTaskAgain(taskId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 200;
    diag.Title = "积分任务再次发布";
    diag.URL = "./pubIntegralTaskAgainDialog?id="+taskId;
    diag.show();
}


/**添加积分兑换规则**/
function addExchRule(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "添加积分任务";
    diag.URL = "./openExchRuleDialog";
    diag.show();
}

/**添加兑换渠道**/
function addCanal(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "添加兑换渠道";
    diag.URL = "openDialogCanal.html";
    diag.show();
}
/*积分管理 end*/

/*广告管理 start*/
/**添加广告**/
function addAdvert(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "添加广告";
    diag.URL = "openDialogAdvert.html";
    diag.show();
}

/**添加广告位**/
function addAdvertPlace(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 170;
    diag.Title = "添加广告位";
    diag.URL = "openDialogAdvertPlace.html";
    diag.show();
}
/**添加广告位**/
function addAdvertPlace(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 170;
    diag.Title = "添加广告位";
    diag.URL = "openDialogAdvertPlace.html";
    diag.show();
}

/**添加广告主**/
function addAdvertMain(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 170;
    diag.Title = "添加广告主";
    diag.URL = "openDialogAdvertMain.html";
    diag.show();
}

/*广告管理 end*/

/*管理员管理 start*/
/*添加管理员*/
function addManager(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 550;
    diag.Title = "添加管理员";
    diag.URL = "./addManager";
    // diag.OKEvent = function(){diag.close();};//点击确定后调用的方法
    diag.show();
}
/*修改管理员*/
function updateManager(managerId,vId){
    if(0==vId){
        top.Dialog.alert("已删除的管理员不允许修改");
        return;
    }
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 550;
    diag.Title = "修改管理员";
    diag.URL = "./updateManager?id="+managerId;
    diag.show();
}
/*添加角色*/
function addRole(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 550;
    diag.Title = "添加角色";
    diag.URL = "./addRole";
    diag.show();
}
/*修改角色*/
function updateRole(roleId,vId){
	if(0==vId){
        top.Dialog.alert("已删除的角色不允许修改");
        return;
    }
	$.ajax({
        type: "POST",
        url:"./checkRoleUser",
        data:{id:roleId},
        async: false,//false 为同步
        error: function(request) {
            top.Dialog.alert("修改失败");
        },
        success: function(data) {
        	if(data.success == true)
            {
        		top.Dialog.alert("该角色已被："+data.data+"使用，不允许修改!");
               
            }else{
            	var diag = new Dialog();
                diag.Width = 500;
                diag.Height = 550;
                diag.Title = "修改角色";
                diag.URL = "./updateRole?id="+roleId;
                diag.show();
            }
        	
        }
    });
	
    
}
/*查看角色*/
function viewRole(roleId,vId){
	if(0==vId){
        top.Dialog.alert("已删除的角色不允许查看");
        return;
    }
	var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 550;
    diag.Title = "查看角色";
    diag.URL = "./viewRole?id="+roleId;
    diag.show();

}


/** 预览图片 **/
function showImg(imgurl){
    var diag = new Dialog();
    diag.Width = 810;
    diag.Height = 660;
    diag.Title = "预览";
    diag.InnerHtml = '<img width=800 height=650" src='+imgurl+'>';
    diag.show();
}
/**
 * 关闭弹出对话框
 */
function closeDialog(){
    Dialog.close();
}
/*展示二位码 end*/

function show2wei(articleId){
    var diag = new Dialog();
    diag.Width = 300;
    diag.Height = 300;
    diag.Title = "文章二维码";
    diag.URL = "./showArticle2wei?articleId="+articleId;
    diag.show();
}

function addCampus(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 400;
    diag.Title = "添加校区";
    diag.URL = "./initCampus";
    diag.show();
}

function editCampus(campusId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "修改校区";
    diag.URL = "./getCampus?id="+campusId;
    diag.show();
}

function addCourse(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "添加课程";
    diag.URL = "../jwCourse/initCourse";
    diag.show();
}

function editCourse(courseId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "修改课程";
    diag.URL = "../jwCourse/getCourse?id="+courseId;
    diag.show();
}


function addTeacher(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 500;
    diag.Title = "添加老师";
    diag.URL = "../jwTeacher/initTeacher";
    diag.show();
}

function editTeacher(teacherId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 500;
    diag.Title = "修改课程";
    diag.URL = "../jwTeacher/getTeacher?id="+teacherId;
    diag.show();
}

function selectCourse(teacherId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "修改课程";
    diag.URL = "../jwTeacher/initCourse?id="+teacherId;
    diag.show();
}

function initTeacher(campusId){
    var diag = new Dialog();
    diag.Width = 550;
    diag.Height = 550;
    diag.Title = "老师列表";
    diag.URL = "../jwCampusSelectCourse/initTeacher?id="+campusId;
    diag.show();
}

function selectTeacher(campusId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 500;
    diag.Title = "老师列表";
    diag.URL = "../jwCampusSelectCourse/campusAddteacher?id="+campusId;
    diag.show();
}

function addClassRoom(){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "添加教室";
    diag.URL = "../jwClassRoom/initClassRoom";
    diag.show();
}

function editClassRoom(classRoomId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "添加教室";
    diag.URL = "../jwClassRoom/initEditClassRoom?classRoomId="+classRoomId;
    diag.show();
}

function addClasses(){
    var diag = new Dialog();
    diag.Width = 400;
    diag.Height = 370;
    diag.Title = "创建班级";
    diag.URL = "../jwClasses/initClasses";
    diag.show();
}

function addConfig(){
    var diag = new Dialog();
    diag.Width = 600;
    diag.Height = 370;
    diag.Title = "创建班级";
    diag.URL = "../jwClasses/initClasses";
    diag.show();
}

function configClasses(classesId){
    var diag = new Dialog();
    diag.Width = 500;
    diag.Height = 370;
    diag.Title = "配置班级";
    diag.URL = "../jwClasses/initClassesConfig?id="+classesId;
    diag.show();
}
function showClasses(classesId){
    var diag = new Dialog();
    diag.Width = 600;
    diag.Height = 300;
    diag.Title = "班级详情";
    diag.URL = "../jwClasses/classesDetail?id="+classesId;
    diag.show();
}
function addStudent(){
    var diag = new Dialog();
    diag.Width = 600;
    diag.Height = 500;
    diag.Title = "创建学生";
    diag.URL = "../jwStudent/initStudent";
    diag.show();
}

function editStduent(studentId){
    var diag = new Dialog();
    diag.Width = 600;
    diag.Height = 500;
    diag.Title = "编辑学生";
    diag.URL = "../jwStudent/getStudent?id="+studentId;
    diag.show();
}

function selectClasses(studentId,campusId){
    var diag = new Dialog();
    diag.Width = 700;
    diag.Height = 400;
    diag.Title = "报班";
    diag.URL = "../jwStudent/classes?id="+studentId+"&campusId="+campusId;
    diag.show();
}
function initFee(id){
    var diag = new Dialog();
    diag.Width = 400;
    diag.Height = 200;
    diag.Title = "缴费";
    diag.URL = "../jwStudent/initFee?id="+id;
    diag.show();
}

function showStudentCheckInList(studentId,campusId,classesId){
    var diag = new Dialog();
    diag.Width = 650;
    diag.Height = 600;
    diag.Title = "老师考勤明细";
    diag.URL = "../jwStudent/checkInDetail?studentId="+studentId+"&campusId="+campusId+"&classesId="+classesId;
    diag.show();
}

function showTeacherCheckInList(teacherId,campusId){
    var diag = new Dialog();
    diag.Width = 600;
    diag.Height = 600;
    diag.Title = "老师考勤明细";
    diag.URL = "../jwTeacher/checkInDetail?teacherId="+teacherId+"&campusId="+campusId;
    diag.show();
}

function checkInList(teacherId,campusId){
    var diag = new Dialog();
    diag.Width = 600;
    diag.Height = 600;
    diag.Title = "老师考勤明细";
    diag.URL = "../jwTeacher/checkInDetail?teacherId="+teacherId+"&campusId="+campusId;
    diag.show();
}


