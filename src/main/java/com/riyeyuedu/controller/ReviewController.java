package com.riyeyuedu.controller;

import com.riyeyuedu.entity.ResponseEntity;
import com.riyeyuedu.entity.ReviewEntity;
import com.riyeyuedu.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "/sendReview", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity sendReview(@RequestBody ReviewEntity reviewEntity) {
        reviewEntity.setTime(new Date().getTime());
        return new ResponseEntity(reviewService.addReview(reviewEntity));
    }

    @RequestMapping(value = "/getReview/{rid}")
    @CrossOrigin
    public ResponseEntity getReview(@PathVariable int rid) {
        return new ResponseEntity(reviewService.getReviewByRid(rid));
    }

    @RequestMapping(value = "/deleteReview/{id}")
    @CrossOrigin
    public ResponseEntity deleteReview(@PathVariable Long id) {
        return new ResponseEntity(reviewService.deleteReview(id));
    }
}
