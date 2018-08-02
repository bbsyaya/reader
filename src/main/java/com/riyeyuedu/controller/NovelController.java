package com.riyeyuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.riyeyuedu.entity.*;
import com.riyeyuedu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@RestController
public class NovelController {
    private NovelService novelService;

    private ScoreService scoreService;

    @Autowired
    public void setNovelService(NovelService novelService) {
        this.novelService = novelService;
    }

    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @CrossOrigin
    public Map<String, List<Map<String, Object>>> getAllNovel() {

        Map<String, List<Map<String, Object>>> novels = new HashMap<>();
        novels.put("scoreNovels", novelService.getNovelByScoreL17());
        novels.put("newNovels", novelService.getNovelByNewL17());
        novels.put("classNovels1", novelService.getNovelByLidL5(1));
        novels.put("classNovels2", novelService.getNovelByLidL5(2));
        novels.put("classNovels3", novelService.getNovelByLidL5(3));
        novels.put("classNovels4", novelService.getNovelByLidL5(4));
        novels.put("classNovels5", novelService.getNovelByLidL5(5));
        novels.put("classNovels6", novelService.getNovelByLidL5(6));
        novels.put("classNovels7", novelService.getNovelByLidL5(7));

        return novels;
    }

    @RequestMapping(value = "/novel/complete", method = RequestMethod.GET)
    @CrossOrigin
    public List<Map<String, Object>> getCompleteNovel() {

        return novelService.getNovelByStateL10(1);
    }

    @RequestMapping(value = "/novel/recommend")
    @CrossOrigin
    public List<Map<String, Object>> getNovelByRecommend() {
        return novelService.getNovelByRecommendL10();
    }

    @RequestMapping(value = "/novel/new")
    @CrossOrigin
    public List<Map<String, Object>> getNovelByNew() {
        return novelService.getNovelByNewL10();
    }

    @RequestMapping(value = "/novel/collect")
    @CrossOrigin
    public List<Map<String, Object>> getNovelByCollectNum() {
        return novelService.getCollectNovelByLid(0);
    }

    @RequestMapping(value = "/novel/click")
    @CrossOrigin
    public List<Map<String, Object>> getNovelByClick() {
        return novelService.getNovelByClickL10();
    }

    @RequestMapping(value = "/novel/recent")
    @CrossOrigin
    public List<Map<String, Object>> getNovelByRecent() {
        return novelService.getNovelByRecentL23();
    }

    @RequestMapping(value = "/novel/edit")
    @CrossOrigin
    public List<NovelEntity> getEditNovel() {
        return novelService.getEditNovel();
    }

    @RequestMapping(value = "/novel/score")
    @CrossOrigin
    public List<Map<String, Object>> getNovelByScore() {
        return novelService.getNovelByScoreL10();
    }

    @RequestMapping(value = "/novel/{nid}", method = RequestMethod.GET)
    @CrossOrigin
    public Map<String, Object> getNovelById(@PathVariable Long nid) {
        Map<String, Object> novel = novelService.getNovelByNid(nid);
        novel.put("scoreNum", scoreService.getScoreNumByNid(nid));
        novelService.addClickNum(nid);
        return novel;
    }

    @RequestMapping(value = "/novel/all")
    @CrossOrigin
    public Map<String, Object> getNovel(@RequestParam("page") int page, @RequestParam("lid") int lid, @RequestParam("state") int state, @RequestParam("active") int active) {
        Page pager = PageHelper.startPage(page, 20);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> m = new HashMap<>();
        m.put("lid", lid);
        m.put("state", state);
        m.put("active", active);
        map.put("novel", novelService.getAllNovel(m));
        map.put("total", pager.getTotal());
        return map;
    }

    @RequestMapping(value = "/novel/inRank")
    @CrossOrigin
    public Map<String, Object> getInRank(@RequestParam("page") int page, @RequestParam("tid") int tid, @RequestParam("lid") int lid) {
        Page pager = PageHelper.startPage(page, 20);
        Map<String, Object> map = new HashMap<>();
        if (tid == 1) {
            map.put("novel", novelService.getClickNovelByLid(lid));
        } else if (tid == 2) {
            map.put("novel", novelService.getRecommendNovelByLid(lid));
        } else if (tid == 3) {
            map.put("novel", novelService.getScoreNovelByLid(lid));
        } else if (tid == 4) {
            map.put("novel", novelService.getNewNovelByLid(lid));
        } else if (tid == 5) {
            Map<String, Object> m = new HashMap<>();
            m.put("lid", lid);
            m.put("active", 0);
            map.put("novel", novelService.getFinishNovelByLid(m));
        } else if (tid == 6) {
            map.put("novel", novelService.getCollectNovelByLid(lid));
        }
        map.put("total", pager.getTotal());
        return map;
    }

    @RequestMapping(value = "/novel/rank")
    @CrossOrigin
    public Map<String, Object> getNovelRank(@RequestParam("lid") int lid) {
        Map<String, Object> map = new HashMap<>();
        map.put("clickNovel", novelService.getClickNovelByLidL10(lid));
        map.put("recommendNovel", novelService.getRecommendNovelByLidL10(lid));
        map.put("popularNovel", novelService.getPopularNovelByLidL10(lid));
        map.put("newNovel", novelService.getNewNovelByLidL10(lid));
        map.put("finishNovel", novelService.getFinishNovelByLidL10(lid));
        map.put("collectNovel", novelService.getCollectNovelByLidL10(lid));
        return map;
    }

    @RequestMapping(value = "/novel/all/{lid}")
    @CrossOrigin
    public Map<String, Object> getAllNovelByLid(@RequestParam("page") int page, @PathVariable int lid) {
        Page pager = PageHelper.startPage(page, 20);
        Map<String, Object> map = new HashMap<>();
        map.put("novel", novelService.getAllNovelByLid(lid));
        map.put("total", pager.getTotal());
        return map;
    }

    @RequestMapping(value = "/novel/finish")
    @CrossOrigin
    public Map<String, Object> getFinishNovel(@RequestParam("page") int page, @RequestParam("lid") int lid, @RequestParam("active") int active) {
        Page pager = PageHelper.startPage(page, 20);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> m = new HashMap<>();
        m.put("lid", lid);
        m.put("active", active);
        map.put("novel", novelService.getFinishNovelByLid(m));
        map.put("total", pager.getTotal());
        return map;
    }

    @RequestMapping(value = "/classify/{lid}", method = RequestMethod.GET)
    @CrossOrigin
    public Map<String, Object> getNovelByCategory(@PathVariable int lid) {
        Map<String, Object> novel = new HashMap<>();
        novel.put("recommendNovel", novelService.getRecommendNovelByLidL15(lid));
        novel.put("newNovel", novelService.getNewNovelByLidL23(lid));
        novel.put("popularNovel", novelService.getPopularNovelByLid(lid));
        return novel;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getNovelByName(@RequestParam("page") int page, @RequestParam("name") String name, @RequestParam("active") int active) {
        Page pager = PageHelper.startPage(page, 10);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", name);
        map1.put("active", active);
        map.put("novel", novelService.search(map1));
        map.put("total", pager.getTotal());
        return new ResponseEntity(map);
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity score(@RequestBody ScoreEntity scoreEntity) {
        scoreEntity.setTime(new Date().getTime());
        if (scoreService.getScore(scoreEntity) != null) {
            scoreService.updateScore(scoreEntity);
        } else {
            scoreService.addScore(scoreEntity);
        }

        Double avg = scoreService.getAvgScoreByNid(scoreEntity.getNid());
        Map<String, Object> map = new HashMap<>();
        map.put("nid", scoreEntity.getNid());
        map.put("score", avg);
        novelService.updateScore(map);

        return new ResponseEntity(null);
    }

    @RequestMapping(value = "/novelScore/{nid}/{page}")
    @CrossOrigin
    public ResponseEntity getScoreByNid(@PathVariable Long nid, @PathVariable int page) {
        Page pager = PageHelper.startPage(page, 15);

        Map<String, Object> map = new HashMap<>();
        map.put("score", scoreService.getScoreByNid(nid));
        map.put("total", pager.getTotal());
        return new ResponseEntity(map);
    }

    @RequestMapping(value = "/myScore/{uid}/{nid}")
    @CrossOrigin
    public ResponseEntity getMyScore(@PathVariable int uid, @PathVariable Long nid) {
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setNid(nid);
        scoreEntity.setUid(uid);
        return new ResponseEntity(scoreService.getScoreByUid(scoreEntity));
    }

    @RequestMapping(value = "/score/{uid}/{page}")
    @CrossOrigin
    public ResponseEntity getScoresByUid(@PathVariable int uid, @PathVariable int page) {
        Page pager = PageHelper.startPage(page, 10);
        Map<String, Object> map = new HashMap<>();
        map.put("score", scoreService.getScoresByUid(uid));
        map.put("total", pager.getTotal());
        return new ResponseEntity(map);
    }

    @RequestMapping(value = "/title/{nid}")
    @CrossOrigin
    public ResponseEntity getTitleByNid(@PathVariable("nid") Long nid) {
        return new ResponseEntity(novelService.getTitleByNid(nid));
    }

    @RequestMapping(value = "/novel_exist")
    @CrossOrigin
    public ResponseEntity novelIsExist(@RequestParam("title") String title) {
        return new ResponseEntity(novelService.getNovelByTitle(title) != null);
    }

    @RequestMapping(value = "/novel/info/{nid}")
    @CrossOrigin
    public  ResponseEntity getNovelInfo(@PathVariable("nid") Long nid) {
        return new ResponseEntity(novelService.getNovelByNid(nid));
    }
}
