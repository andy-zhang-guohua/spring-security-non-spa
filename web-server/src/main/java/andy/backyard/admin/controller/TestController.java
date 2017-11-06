package andy.backyard.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * 此类作为测试用途，上线发布之前暂时不要删除
 * Created by ZhangGuohua on 2017/10/31.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/jsr310", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> jsr310() {
        Map<String, Object> map = new TreeMap<>();
        map.put("time", LocalDateTime.now());
        map.put("date", LocalDate.now());

        return map;
    }

    @RequestMapping(value = "/jsr310/date", method = RequestMethod.GET)
    @ResponseBody
    public LocalDate jsr310_date() {
        return LocalDate.now();
    }

    @RequestMapping(value = "/jsr310/date-time", method = RequestMethod.GET)
    @ResponseBody
    public LocalDateTime jsr310_date_time() {
        return LocalDateTime.now();
    }
}
