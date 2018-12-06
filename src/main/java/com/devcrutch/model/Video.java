package com.devcrutch.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Video {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
    private String title;
    private String description;
    private Date date;
 }