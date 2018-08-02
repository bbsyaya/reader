package com.riyeyuedu.controller;

import com.riyeyuedu.controller.Format.IndexRecordFormat;
import com.riyeyuedu.entity.RecordEntity;
import com.riyeyuedu.entity.ResponseEntity;
import com.riyeyuedu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class RecordController {
    private RecordService recordService;

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping(value = "/user/addRecord", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity addRecord(@RequestBody IndexRecordFormat format) {

        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setCid(format.getCid());
        recordEntity.setNid(format.getNid());
        recordEntity.setUid(format.getUid());
        recordEntity.setTime(new Date().getTime());
        boolean result;

        if (recordService.getRecord(recordEntity) != null) {
            result = recordService.updateRecord(recordEntity);
        } else {
            result = recordService.addRecord(recordEntity);
        }

        return new ResponseEntity(200, "添加纪录成功", result);
    }
}
