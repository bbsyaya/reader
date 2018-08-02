package com.riyeyuedu.controller;

import com.riyeyuedu.entity.*;
import com.riyeyuedu.service.ChapterService;
import com.riyeyuedu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@RestController
public class ChapterController {
    private ChapterService chapterService;

    private RecordService recordService;

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping(value = "/chapter/{cid}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getChapter(@PathVariable("cid") Long cid) {
        System.out.println("in");
        System.out.println(cid);
        Map<String, Object> map = chapterService.getChapterByCid(cid);
        System.out.println(map);
        Long nid = (Long) map.get("nid");
        Long fcid = chapterService.getFirstChapter(nid).getCid();
        Long lcid = chapterService.getLastChapter(nid).getCid();
        map.put("fcid", fcid);
        map.put("lcid", lcid);

        System.out.println(map);

        return new ResponseEntity(map);
    }

    @RequestMapping(value = "/user/continue/{uid}/{nid}")
    @CrossOrigin
    public ResponseEntity continueRead(@PathVariable("uid") int uid, @PathVariable("nid") Long nid) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setUid(uid);
        recordEntity.setNid(nid);
        if (recordService.getRecord(recordEntity) != null) {
            return new ResponseEntity(recordService.getRecord(recordEntity).getCid());
        } else {
            return new ResponseEntity(null);
        }
    }

    @RequestMapping(value = "/directory/{nid}", method = RequestMethod.GET)
    @ResponseBody
    public List<ChapterEntity> getDirectoryByNid(@PathVariable("nid") Long nid) {
        return chapterService.getDirectoryByNid(nid);
    }
}
