package com.tangtang.manager.controller.system;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.dto.StudentRegistrationDTO;
import com.tangtang.manager.dto.TestTargetResultDTO;
import com.tangtang.manager.dto.TestTargetScoreDTO;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.pojo.BaseTestQuestion;
import com.tangtang.manager.pojo.BaseTestResult;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.AdminUserService;
import com.tangtang.manager.service.CaseService;
import com.tangtang.manager.service.TestQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

/**
 * @Title: CaseController
 * @Description: 测试题目管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/17 11：57
 */
@Controller
@RequestMapping("testQuestion")
public class TestQuestionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestQuestionService testQuestionService;


    /**
     *
     * 功能描述: 跳到测试题目列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/17 11：58
     */
    @RequestMapping("/testQuestionManage")
    public String userManage() {
        return "/testQuestion/testQuestionManage";
    }

    /**
     *
     * 功能描述: 跳到个人测试结果列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/31 11：58
     */
    @RequestMapping("/testResultManage")
    public String testResultManage() {
        return "/testQuestion/testResultManage";
    }


    /**
     *
     * 功能描述: 分页查询测试题目列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/17 11：59
     */
    @RequestMapping(value = "/getTestQuestionList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getUserList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ BaseTestQuestion question) {
        /*logger.info("分页查询用户列表！搜索条件：userSearch：" + userSearch + ",pageNum:" + page.getPageNum()
                + ",每页记录数量pageSize:" + page.getPageSize());*/
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取用户列表
            pdr = testQuestionService.getTestQuestionList(question, pageNum ,pageSize);
            logger.info("题目列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("题目列表查询异常！", e);
        }
        return pdr;
    }

    /**
     *
     * 功能描述: 分页查询个人测试结果列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/31 11：59
     */
    @RequestMapping(value = "/getTestResultListByUserId", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getTestResultListByUserId(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize, BaseTestResult testResult, @RequestParam("userId") String userId) {
        testResult.setUserId(userId);
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取结果列表
            pdr = testQuestionService.getTestResultListByUserId(testResult, pageNum ,pageSize);
            logger.info("测试结果列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("测试结果列表查询异常！", e);
        }
        return pdr;
    }

    /**
     *
     * 功能描述: 获取测试题目数据
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/18 16:28
     */
    @GetMapping("getTestPaper")
    public @ResponseBody List<BaseTestQuestion> getCaseShowData(){
        logger.info("获取测试题目数据");
        return testQuestionService.getTestQuestionList();
    }

    /**
     * 新增测试题目
     * @param testQuestion
     * @return
     */
    @RequestMapping(value = "/addTestQuestion", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCase(BaseTestQuestion testQuestion) {

        Map<String,Object> data = new HashMap();
        if(testQuestion.getId() == null){
            logger.info("新增测试题目 ------> successfulCase:" + testQuestion);
            data = testQuestionService.addTestQuestion(testQuestion);
        }else{
            logger.info("编辑测试题目 ------> successfulCase:" + testQuestion);
            data = testQuestionService.updateTestQuestion(testQuestion);
        }

        return data;
    }


    /**
     * 删除测试题目
     * @Author tangtang
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除测试题目！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = testQuestionService.del(id);
        return data;
    }

    /**
     * 比对测试答案
     * @Author tangtang
     * @param answer
     * @return
     */
    @PostMapping("checkAnswers")
    @ResponseBody
    public Map<String, Object> checkAnswers(String answer, String recordId, String userName) {
        logger.info("比对测试答案！answerArray:" + answer);
        Map<String, Object> data = new HashMap<>();
        TestTargetScoreDTO scoreDTO = new TestTargetScoreDTO();
        TestTargetResultDTO resultDTO = new TestTargetResultDTO();
        BaseTestResult baseTestResult = new BaseTestResult();
        baseTestResult.setId(Integer.parseInt(recordId));
        double  ZYSZ_SCORE = baseTestResult.getZYSZ(),
                CYSZ_SCORE = baseTestResult.getCYSZ(),
                GSSFZS_SCORE = baseTestResult.getGSSFZS(),
                CWYXZS_SCORE = baseTestResult.getCWYXZS(),
                YYGLZS_SCORE = baseTestResult.getYYGLZS(),
                CYJBNL_SCORE = baseTestResult.getCYJBNL(),
                YYGLNL_SCORE = baseTestResult.getYYGLNL(),
                SCYXNL_SCORE = baseTestResult.getSCYXNL();

//        String  WORK_RESULT = resultDTO.getWORK_RESULT(),
//                SKILL_RESULT = resultDTO.getSKILL_RESULT(),
//                LIFU_RESULT = resultDTO.getLIFU_RESULT(),
//                ZYSZ_RESULT = resultDTO.getZYSZ_RESULT(),
//                CYSZ_RESULT = resultDTO.getCYSZ_RESULT(),
//                GSSFZS_RESULT = resultDTO.getGSSFZS_RESULT(),
//                CWYXZS_RESULT = resultDTO.getCWYXZS_RESULT(),
//                YYGLZS_RESULT = resultDTO.getYYGLZS_RESULT(),
//                CYJBNL_RESULT = resultDTO.getCYJBNL_RESULT(),
//                YYGLNL_RESULT = resultDTO.getYYGLNL_RESULT(),
//                SCYXNL_RESULT = resultDTO.getSCYXNL_RESULT();

        //开始计算分数
        String[] answerArray = answer.split(",");
        for(int i=0; i<answerArray.length; i++){
            String[] midAnswer = answerArray[i].split("=");
            String[] answerParam = midAnswer[1].split("_");
            String answerScore = answerParam[1];
            String testType = answerParam[2];
            if(testType.equals("ZYSZ")){
                ZYSZ_SCORE += Integer.valueOf(answerScore);
            }else if(testType.equals("CYSZ")){
                CYSZ_SCORE += Integer.valueOf(answerScore);
            }else if(testType.equals("GSSFZS")){
                GSSFZS_SCORE += Integer.valueOf(answerScore);
            }else if(testType.equals("CWYXZS")){
                CWYXZS_SCORE += Integer.valueOf(answerScore);
            }else if(testType.equals("YYGLZS")){
                YYGLZS_SCORE += Integer.valueOf(answerScore);
            }else if(testType.equals("CYJBNL")){
                CYJBNL_SCORE += Integer.valueOf(answerScore);
            }else if(testType.equals("YYGLNL")){
                YYGLNL_SCORE += Integer.valueOf(answerScore);
            }else if(testType.equals("SCYXNL")){
                SCYXNL_SCORE += Integer.valueOf(answerScore);
            }
        }

        //最终测试得分
        double FINAL_SCORE = (ZYSZ_SCORE+CYSZ_SCORE+GSSFZS_SCORE+CWYXZS_SCORE+YYGLZS_SCORE+CYJBNL_SCORE+YYGLNL_SCORE+SCYXNL_SCORE)/8;  //总分值




        //收集优势以及缺陷
        String YS = ""; //优势
        String QX = ""; //缺陷

        if(ZYSZ_SCORE>=70){
            YS += "<p class=\"p2\">职业素质："+ZYSZ_SCORE+"分</p>";
        }else {
            QX += "<p class=\"p2\"><span>职业素质："+ZYSZ_SCORE+"分</span></p>";
        }

        if(CYSZ_SCORE>=70){
            YS += "<p class=\"p2\">创业素质："+CYSZ_SCORE+"分</p>";
        }else {
            QX += "<p class=\"p2\"><span>创业素质："+CYSZ_SCORE+"分</span></p>";
        }

        if(GSSFZS_SCORE>=70){
            YS += "<p class=\"p2\">公司商法知识："+GSSFZS_SCORE+"分</p>";
        }else {
            QX += "<p class=\"p2\"><span>公司商法知识："+GSSFZS_SCORE+"分</span></p>";
        }

        if(CWYXZS_SCORE>=70){
            YS += "<p class=\"p2\">财务营销知识："+CWYXZS_SCORE+"分</p>";
        }else {
            QX += "<p class=\"p2\"><span>财务营销知识："+CWYXZS_SCORE+"分</span></p>";
        }

        if(YYGLZS_SCORE>=70){
            YS += "<p class=\"p2\">运营管理知识："+YYGLZS_SCORE+"分</p>";
        }else {
            QX += "<p class=\"p2\"><span>运营管理知识："+YYGLZS_SCORE+"分</span></p>";
        }

        if(CYJBNL_SCORE>=70){
            YS += "<p class=\"p2\">创业基本能力："+CYJBNL_SCORE+"分</p>";
        }else {
            QX += "<p class=\"p2\"><span>创业基本能力："+CYJBNL_SCORE+"分</span></p>";
        }

        if(YYGLNL_SCORE>=70){
            YS += "<p class=\"p2\">运营管理能力："+YYGLNL_SCORE+"分</p>";
        }else {
            QX += "<p class=\"p2\"><span>运营管理能力："+YYGLNL_SCORE+"分</span></p>";
        }

        if(SCYXNL_SCORE>=70){
            YS += "<p class=\"p2\">市场营销能力："+SCYXNL_SCORE+"分</p>";
        }else {
            QX += "<p class=\"p2\"><span>市场营销能力："+SCYXNL_SCORE+"分</span></p>";
        }


        //生成测试最终结果
        String FINAL_RESULT = "<br>\n" +
                "<div class=\"duanluo\">\n" +
                "<p class=\"p2\">"+    userName     +"同学您好，您选择了《大学生创业发展能力综合测评》，根据本次测评的目的和要求，测评的内容与要素包括：综合素质（包括职业素质与创业素质）；经营管理知识（包括：公司商法知识、财务营销知识和运营管理知识）；创业胜任能力（包括：创业基本能力、运营管理能力、市场营销能力）共计40个测试题目。根据测评结果，您的测评达标成绩为"+ FINAL_SCORE +"分，本报告向您提交该次测评的综合成绩分析。</p></div>\n" +
                "\n" +
                "<br>\n" +
                "<div class=\"duanluo\">\n" +
                "<div class=\"bianju\">\n" +
                "<p class=\"biaoti\">一、各维度组分测评成绩达标值分析</p>\n" +
                "<img id='iii' />" +
                "<canvas id=\"canvas\" height=\"450\" width=\"780\" ></canvas>" +
                "<p class=\"p2\">"+   userName   +"同学，针对您在《大学生创业发展能力综合测评》的测评结果分析，得出您的各测评维度组分的成绩如下：</p>\n" +
                "<p class=\"p2\">1、职业素质维度成绩达标值为 <span>"+  ZYSZ_SCORE  +"%</span> ；</p>\n" +
                "<p class=\"p2\">2、创业素质维度成绩达标值为 <span>"+  CYSZ_SCORE  +"%</span> ；</p>\n" +
                "<p class=\"p2\">3、公司商法知识维度成绩达标值为 <span>"+  GSSFZS_SCORE  +"%</span>  ；</p>\n" +
                "<p class=\"p2\">4、财务营销知识维度成绩达标值为 <span>"+  CWYXZS_SCORE  +"%</span>  ；</p>\n" +
                "<p class=\"p2\">5、运营管理知识维度成绩达标值为 <span>"+  YYGLZS_SCORE  +"%</span>  ；</p>\n" +
                "<p class=\"p2\">6、创业基本能力维度成绩达标值为 <span>"+  CYJBNL_SCORE  +"%</span>  ；</p>\n" +
                "<p class=\"p2\">7、运营管理能力维度成绩达标值为 <span>"+  YYGLNL_SCORE  +"%</span>  ；</p>\n" +
                "<p class=\"p2\">8、市场营销能力维度成绩达标值为 <span>"+  SCYXNL_SCORE  +"%</span>  ；</p>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "<br>\n" +
                "<div class=\"duanluo\">\n" +
                "<div class=\"bianju\">\n" +
                "<p class=\"biaoti\">二、您的创业发展优势</p>\n" +
                "<p class=\"p2\">根据您的测评成绩综合分析，您在以下方面具备了良好的创业发展优势，希望您继续保持自己的优势，在您的创业发展过程中继续努力学习与创新实践，进一步提高自己的综合素质与创业能力。</p>\n" + YS +
                "</div>\n" +
                "</div>\n" +
                "<br>\n" +
                "<div class=\"duanluo\">\n" +
                "<div class=\"bianju\">\n" +
                "<p class=\"biaoti\">三、有待进一步学习、培训与提高的方面</p>\n" +
                "<p class=\"p2\">根据您的测评结果，您在以下方面需要进行学习通过学习、培训、提高与完善自己，请您在今后的工作中密切关注。</p>\n" + QX +
                "</div>\n" +
                "</div>" +
                "<br>";

        //数据持久化，测试结果入库
        //记录分数
        baseTestResult.setZYSZ(ZYSZ_SCORE);
        baseTestResult.setCYSZ(CYSZ_SCORE);
        baseTestResult.setGSSFZS(GSSFZS_SCORE);
        baseTestResult.setCWYXZS(CWYXZS_SCORE);
        baseTestResult.setYYGLZS(YYGLZS_SCORE);
        baseTestResult.setCYJBNL(CYJBNL_SCORE);
        baseTestResult.setYYGLNL(YYGLNL_SCORE);
        baseTestResult.setSCYXNL(SCYXNL_SCORE);
        baseTestResult.setTestResult(FINAL_RESULT);
        baseTestResult.setEndTime(DateUtils.getCurrentDate());

        data = testQuestionService.updateTestResult(baseTestResult);

        //向前端返回结果
        return data;
    }

    /**
     *
     * 功能描述: 通过UserID获取测试结果
     *
     * @param: userId
     * @return:
     * @auther: tangtang
     * @date: 2020/03/26 16:28
     */
    @RequestMapping(value = "/getTestResult", method = RequestMethod.POST)
    public @ResponseBody List<BaseTestResult> getTestResultByUserId(String userId){
        logger.info("通过UserID获取测试结果");
        return testQuestionService.getTestResultByUserId(userId);
    }

    /**
     *
     * 功能描述: 通过ID获取测试结果
     *
     * @param: userId
     * @return:
     * @auther: tangtang
     * @date: 2020/03/31 16:28
     */
    @RequestMapping(value = "/getTestResultById", method = RequestMethod.POST)
    public @ResponseBody List<BaseTestResult> getTestResultById(String recordId){
        logger.info("通过ID获取测试结果");
        return testQuestionService.getTestResultById(recordId);
    }

    /**
     * 进入测试前检查之前是否有考试记录，考试记录是否数据完整
     */
    @RequestMapping(value = "/checkTestResult", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> checkTestResultById(String userId, String userName) throws ParseException {
        logger.info("检查之前的考试结果记录是否存在");
        BaseTestResult baseTestResult = new BaseTestResult();
        baseTestResult.setUserId(userId);
        baseTestResult.setUserName(userName);
        Map<String,Object> data = new HashMap();
        List<BaseTestResult> testResults = testQuestionService.getTestResultByUserId(userId); //获取考试结果记录
        if(testResults.size()>0){
            int length = testResults.size()-1;  //集合长度
            BaseTestResult latelyRecord = testResults.get(length); //最新的一条记录
            String currentTime = DateUtils.getCurrentDate();
            String startTime = latelyRecord.getStartTime();
            String leftTime = DateUtils.getLeftTime(currentTime,startTime);

            String preResult = latelyRecord.getTestResult();
            if(preResult==null || preResult==""){
                data.put("recordId",latelyRecord.getId()); //返回上次测试的结果id，用于后面提交考卷时的数据更新
                data.put("leftTime",leftTime); //返回剩余时间
            }else {
                data = testQuestionService.addSomethingToTestResult(baseTestResult);
            }
        }else {
            data = testQuestionService.addSomethingToTestResult(baseTestResult);
        }
        return data;
    }


}
