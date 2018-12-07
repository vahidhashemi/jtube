package com.devcrutch.controller;

import com.devcrutch.model.Video;
import com.devcrutch.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

    @GetMapping(value = "videos")
    public List<Video> getVideos() {
        return videoRepository.findAll();
    }

    @GetMapping(value = "videos/{id}")
    public Video getVideo(Long id) {
        return videoRepository.findById(id).get();
    }

}
