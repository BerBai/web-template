package com.ber.template.controller;

import com.ber.template.domain.JsonResult;
import com.ber.template.domain.MongoDome;
import com.ber.template.service.MongoDomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MongoDemoController {
    @Autowired
    private MongoDomeService mongoDomeService;

    @RequestMapping("/get")
    public JsonResult getAll() {
        List<MongoDome> mongoDomes = mongoDomeService.getAll();
        return new JsonResult(true, mongoDomes);
    }

    @RequestMapping("/add")
    public JsonResult add(MongoDome mongoDome) {
        Boolean aBoolean = mongoDomeService.addOrUpdate(mongoDome);
        return new JsonResult(aBoolean);
    }

    @RequestMapping("/delete")
    public JsonResult delete(MongoDome mongoDome) {
        Boolean aBoolean = mongoDomeService.delete(mongoDome);
        return new JsonResult(aBoolean);
    }
}
