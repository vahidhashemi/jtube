package com.devcrutch;

import com.devcrutch.model.User;
import com.devcrutch.model.Video;
import com.devcrutch.repository.VideoRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VideoRepositoryIntegrationTest extends MainTest{
    @Autowired
    VideoRepository videoRepository;

    UserServiceTest userRepo;

    @Before
    public void init() {
        userRepo = new UserServiceTest();
    }

    @Test
    public void test() {
        User user = new User("user1", "qwe" , false);
        Video video = new Video(user, "New Vid", "New Desc",new Date(),"url" );
        Video savedVideo = videoRepository.saveAndFlush(video);
        assertNotNull(savedVideo);
        assertEquals(video, savedVideo);
    }
}
