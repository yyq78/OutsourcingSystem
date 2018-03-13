package com.zhy.controller.getoutsourcinginfos;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.redis.RedisForOutsourcing;
import com.zhy.utils.SortUtils;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 20:29 2018/3/12
 * Describe: 对价格或是金额进行排序
 */
@Controller
@RequestMapping("/sort")
public class SortOutsourcingInfo {

    private Logger logger = LoggerFactory.getLogger(SortOutsourcingInfo.class);

    @Autowired
    RedisForOutsourcing redisForOutsourcing;

    @PostMapping("/sortByAmount")
    @ResponseBody
    public JSONArray sortByAmount(HttpServletRequest request){

        int startPage = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int start = (startPage-1)*pageSize;

        List<OutsourcingInfo> list = redisForOutsourcing.getAllOutsourcingList();
        Map<String, Integer> map = redisForOutsourcing.getPageNumber();

        SortUtils sortUtils = new SortUtils();
        List<OutsourcingInfo> amountSortResult = sortUtils.sortByAmount(list, start, pageSize, startPage);
        redisForOutsourcing.saveByListAndMap(amountSortResult, map);

        JSONArray sortByAmountForJsonArray = redisForOutsourcing.getPageJsonArray();

        logger.info("金钱排序的外包信息：" + sortByAmountForJsonArray.toString());
        return sortByAmountForJsonArray;
    }

    @PostMapping("/sortByTime")
    @ResponseBody
    public JSONArray sortByTime(HttpServletRequest request){

        int startPage = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int start = (startPage-1)*pageSize;

        List<OutsourcingInfo> list = redisForOutsourcing.getAllOutsourcingList();
        Map<String, Integer> map = redisForOutsourcing.getPageNumber();

        SortUtils sortUtils = new SortUtils();
        List<OutsourcingInfo> amountSortResult = sortUtils.sortByTime(list, start, pageSize);
        redisForOutsourcing.saveByListAndMap(amountSortResult, map);

        JSONArray sortByAmountForJsonArray = redisForOutsourcing.getPageJsonArray();

        logger.info("时间排序的外包信息：" + sortByAmountForJsonArray.toString());
        return sortByAmountForJsonArray;
    }

}
