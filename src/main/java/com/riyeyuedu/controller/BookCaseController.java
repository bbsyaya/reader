package com.riyeyuedu.controller;

import com.riyeyuedu.entity.BookCaseEntity;
import com.riyeyuedu.entity.RecordEntity;
import com.riyeyuedu.entity.ResponseEntity;
import com.riyeyuedu.service.BookCaseService;
import com.riyeyuedu.service.NovelService;
import com.riyeyuedu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class BookCaseController {
    private BookCaseService bookCaseService;

    private NovelService novelService;

    private RecordService recordService;

    @Autowired
    public void setNovelService(NovelService novelService) {
        this.novelService = novelService;
    }

    @Autowired
    public void setBookCaseService(BookCaseService bookCaseService) {
        this.bookCaseService = bookCaseService;
    }

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping(value = "/user/addBookcase/{uid}/{type}")
    @CrossOrigin
    public ResponseEntity addBookCase(@PathVariable int uid, @PathVariable String type) {
        BookCaseEntity bookCaseEntity = new BookCaseEntity();
        bookCaseEntity.setUid(uid);
        bookCaseEntity.setType(type);

        if (bookCaseService.getBookCase(bookCaseEntity) != null) {
            return new ResponseEntity(400, "已存在同名类别的书架", null);
        }

        return new ResponseEntity(bookCaseService.addBookCase(bookCaseEntity));
    }

    @RequestMapping(value = "/user/addToBookCase/{uid}/{nid}/{type}")
    @CrossOrigin
    @Transactional
    public ResponseEntity addToBookCase(@PathVariable Long nid, @PathVariable int uid, @PathVariable String type) {
        BookCaseEntity bookCase = new BookCaseEntity();
        bookCase.setUid(uid);
        bookCase.setType(type);

        Long bid = bookCaseService.getBookCase(bookCase).getBid();

        Map<String, Object> map = new HashMap<>();
        map.put("bid", bid);
        map.put("nid", nid);

        if (bookCaseService.isNovelInBookcase(map) == null) {
            bookCaseService.addNovelToBookcase(map);
            return new ResponseEntity("加入成功");
        } else {
            return new ResponseEntity(400, "已存在于书架", null);
        }
    }

    @RequestMapping(value = "/user/bookcase/{uid}/{type}")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity getNovelInBookcase(@PathVariable int uid, @PathVariable String type) {
        System.out.println(uid + ";" + type);
        BookCaseEntity bookCaseEntity = new BookCaseEntity();
        bookCaseEntity.setUid(uid);
        bookCaseEntity.setType(type);

        Long bid = bookCaseService.getBookCase(bookCaseEntity).getBid();
        System.out.println(bid);

        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> novelMap = novelService.getNovelInBookCase(bookCaseEntity);
        System.out.println(novelMap);

        RecordEntity recordEntity;

        Map<String, Object> temp;

        for (Map<String, Object> novel : novelMap) {
            Long nid = (Long) novel.get("nid");
            recordEntity = new RecordEntity();
            recordEntity.setUid(uid);
            recordEntity.setNid(nid);
            recordEntity = recordService.getRecord(recordEntity);
            System.out.println(recordEntity);

            if (recordEntity != null) {
                temp = recordService.getRecordDetail(recordEntity);
                Long cid = (Long) temp.get("record_cid");
                String chapterName = (String) temp.get("record_chapter_name");
                novel.put("record_cid", cid);
                novel.put("record_chapter_name", chapterName);
            } else {
                novel.put("record_cid", -1);
                novel.put("record_chapter_name", "-1");
            }
        }

        map.put("novel", novelMap);
        map.put("num", bookCaseService.getNovelCount(bid).get("num"));
        return new ResponseEntity(map);
    }

    @RequestMapping(value = "/bookcase/recent/{uid}")
    @CrossOrigin
    public ResponseEntity getNovelByRecent(@PathVariable int uid) {
        List<Map<String, Object>> recordList = recordService.getAllRecordDetail(uid);

        Map<String, Object> temp;

        List<Map<String, Object>> novelList = new LinkedList<>();

        RecordEntity recordEntity;

        for (Map<String, Object> record : recordList) {
            recordEntity = new RecordEntity();
            recordEntity.setNid((Long) record.get("nid"));
            recordEntity.setUid(uid);
            temp = novelService.getNovelByRecord(recordEntity);
            temp.put("record_cid", record.get("record_cid"));
            temp.put("record_chapter_name", record.get("record_chapter_name"));
            novelList.add(temp);
        }

        return new ResponseEntity(novelList);
    }

    @RequestMapping(value = "/user/deleteNovelInBookcase/{uid}/{nid}/{type}")
    @CrossOrigin
    public ResponseEntity deleteNovelInBookcase(@PathVariable int uid, @PathVariable Long nid, @PathVariable String type) {
        BookCaseEntity bookCaseEntity = new BookCaseEntity();
        bookCaseEntity.setUid(uid);
        bookCaseEntity.setType(type);
        Long bid = bookCaseService.getBookCase(bookCaseEntity).getBid();

        Map<String, Object> map = new HashMap<>();
        map.put("bid", bid);
        map.put("nid", nid);

        if (bookCaseService.isNovelInBookcase(map) == null) {
            return new ResponseEntity(400, "书架中不存在此书", null);
        } else {
            bookCaseService.deleteNovelInBookCase(map);
            return new ResponseEntity("删除成功");
        }
    }

    @RequestMapping(value = "/allBookcase/{uid}")
    @CrossOrigin
    public ResponseEntity getAllBookcase(@PathVariable int uid) {
        return new ResponseEntity(bookCaseService.getAllBookcase(uid));
    }

    @RequestMapping(value = "/removeBookcase/{uid}/{nid}/{type}")
    @CrossOrigin
    public ResponseEntity removeBookcase(@PathVariable int uid, @PathVariable Long nid, @PathVariable String type) {
        BookCaseEntity bookCaseEntity = new BookCaseEntity();
        bookCaseEntity.setUid(uid);
        bookCaseEntity.setType(type);

        Long bid = bookCaseService.getBookCase(bookCaseEntity).getBid();

        Map<String, Object> map = new HashMap<>();
        map.put("bid", bid);
        map.put("nid", nid);

        return new ResponseEntity(bookCaseService.removeBookcase(map));
    }

    @RequestMapping(value = "/user/toTop/{uid}/{nid}/{type}/{level}")
    @CrossOrigin
    public ResponseEntity toTop(@PathVariable int uid, @PathVariable Long nid, @PathVariable String type, @PathVariable int level) {
        BookCaseEntity bookCaseEntity = new BookCaseEntity();
        bookCaseEntity.setUid(uid);
        bookCaseEntity.setType(type);

        Long bid = bookCaseService.getBookCase(bookCaseEntity).getBid();

        Map<String, Object> map = new HashMap<>();
        map.put("bid", bid);
        map.put("nid", nid);
        map.put("level", level);

        return new ResponseEntity(bookCaseService.toTop(map));
    }

    @RequestMapping(value = "/user/topNum/{uid}/{type}")
    @CrossOrigin
    public ResponseEntity getTopNum(@PathVariable int uid, @PathVariable String type) {
        BookCaseEntity bookCaseEntity = new BookCaseEntity();
        bookCaseEntity.setUid(uid);
        bookCaseEntity.setType(type);

        Long bid = bookCaseService.getBookCase(bookCaseEntity).getBid();
        System.out.println(bid);

        return new ResponseEntity(bookCaseService.getTopNum(bid));
    }

    @RequestMapping(value = "/cancelTop/{uid}/{nid}/{type}")
    @CrossOrigin
    public ResponseEntity cancelTop(@PathVariable int uid, @PathVariable Long nid, @PathVariable String type) {
        BookCaseEntity bookCaseEntity = new BookCaseEntity();
        bookCaseEntity.setUid(uid);
        bookCaseEntity.setType(type);

        Long bid = bookCaseService.getBookCase(bookCaseEntity).getBid();

        Map<String, Object> map = new HashMap<>();
        map.put("bid", bid);
        map.put("nid", nid);

        return new ResponseEntity(bookCaseService.updateLevelDown(map));
    }

    @RequestMapping(value = "/deleteBookcase/{uid}/{type}")
    @CrossOrigin
    public ResponseEntity deleteBookcase(@PathVariable int uid, @PathVariable String type) {
        Long bid = bookCaseService.getDefaultBookcase(uid).getBid();
        BookCaseEntity bookCaseEntity = new BookCaseEntity();
        bookCaseEntity.setType(type);
        bookCaseEntity.setUid(uid);
        List<Map<String, Object>> list = novelService.getNovelInBookCase(bookCaseEntity);
        Map<String, Object> novel;
        for (Map map : list) {
            novel = new HashMap<>();
            novel.put("nid", map.get("nid"));
            novel.put("bid", bid);
            bookCaseService.removeBookcase(novel);
        }

        bid = bookCaseService.getBookCase(bookCaseEntity).getBid();

        return new ResponseEntity(bookCaseService.deleteBookcase(bid));
    }

    @RequestMapping(value = "/searchInBookcase/{uid}/{title}")
    @CrossOrigin
    public ResponseEntity searchNovel(@PathVariable int uid, @PathVariable String title) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("title", title);
        return new ResponseEntity(novelService.searchNovelInBookCase(map));
    }
}

