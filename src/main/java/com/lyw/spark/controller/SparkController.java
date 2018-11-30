package com.lyw.spark.controller;

import com.lyw.spark.scala.SparkApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述：spark
 *
 * @author liangyuwu
 * @Time 2018/11/29 16:18
 */
@RestController
@RequestMapping("spark")
public class SparkController {

    private static Logger logger = LoggerFactory.getLogger(SparkController.class);

    /**
     * 返回文件内容
     * @param filePath
     * @return
     */
    @ResponseBody
    @RequestMapping("/content")
    protected String getContents(@RequestParam String filePath) {
        return new SparkApp().analyText(filePath);
    }
}
