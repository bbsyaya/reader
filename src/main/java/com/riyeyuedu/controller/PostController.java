package com.riyeyuedu.controller;

import com.riyeyuedu.entity.AttitudeEntity;
import com.riyeyuedu.entity.PostEntity;
import com.riyeyuedu.entity.ReplyEntity;
import com.riyeyuedu.entity.ResponseEntity;
import com.riyeyuedu.service.NovelService;
import com.riyeyuedu.service.PostService;
import com.riyeyuedu.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PostController {
    private PostService postService;

    private NovelService novelService;

    private ReplyService replyService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setNovelService(NovelService novelService) {
        this.novelService = novelService;
    }

    @Autowired
    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }

    @RequestMapping(value = "/post/{nid}")
    @CrossOrigin
    public ResponseEntity getPost(@PathVariable Long nid) {
        Map<String, Object> map = new HashMap<>();
        map.put("novel", novelService.getTitleByNid(nid));
        map.put("post", postService.getPostByNid(nid));
        map.put("num", postService.getPostNumByNid(nid));
        return new ResponseEntity(map);
    }

    @RequestMapping(value = "/reply/{nid}/{pid}")
    @CrossOrigin
    public ResponseEntity getReply( @PathVariable Long nid,@PathVariable Long pid) {
        Map<String, Object> map = new HashMap<>();
        map.put("post", postService.getPostByPid(pid));
        map.put("reply", replyService.getReplyByPid(pid));
        map.put("novel", novelService.getTitleByNid(nid));
        return new ResponseEntity(map);
    }

    @RequestMapping(value = "/sendReply", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity sendReply(@RequestBody ReplyEntity replyEntity) {
        replyEntity.setTime(new Date().getTime());
        replyService.addReply(replyEntity);
        postService.addCommentNum(replyEntity.getPid());
        return new ResponseEntity(replyEntity);
    }

    @RequestMapping(value = "/putPost", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity putPost(@RequestBody PostEntity postEntity) {
        postEntity.setUpdateTime(new Date().getTime());
        return new ResponseEntity(postService.addPost(postEntity));
    }

    @RequestMapping(value = "/toAttitude/{uid}/{pid}/{state}")
    @CrossOrigin
    public ResponseEntity toAttitude(@PathVariable int uid, @PathVariable Long pid, @PathVariable int state) {
        AttitudeEntity attitudeEntity = new AttitudeEntity();
        attitudeEntity.setPid(pid);
        attitudeEntity.setUid(uid);
        attitudeEntity.setState(state);

        return new ResponseEntity(postService.toAttitude(attitudeEntity));
    }
}
