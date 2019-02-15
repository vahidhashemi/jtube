package com.devcrutch;

import com.devcrutch.model.User;
import com.devcrutch.model.Video;
import com.devcrutch.repository.VideoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class VideoRepositoryIntegrationTest {
    @Autowired
    VideoRepository videoRepository;

    UserServiceIntegrationTest userRepo;

    @Before
    public void init() {
        userRepo = new UserServiceIntegrationTest();
    }

    @Test
    public void test() {
        User user = userRepo.createUser("user1", "qwe" , false);
        Video video = new Video(user, "New Vid", "New Desc",new Date(),"url" );
        Video savedVideo = videoRepository.saveAndFlush(video);
        assertNotNull(savedVideo);
        assertEquals(video, savedVideo);
    }
}
