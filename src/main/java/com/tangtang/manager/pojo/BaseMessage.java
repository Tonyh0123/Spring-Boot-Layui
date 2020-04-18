package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_message")
public class BaseMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "message_owner")
    private String message_owner;

    @Column(name = "message_sender")
    private String message_sender;

    @Column(name = "message_title")
    private String message_title;

    @Column(name = "message_content")
    private String message_content;

    @Column(name = "message_date_time")
    private String message_date_time;

    @Column(name = "project_name")
    private String project_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage_owner() {
        return message_owner;
    }

    public void setMessage_owner(String message_owner) {
        this.message_owner = message_owner;
    }

    public String getMessage_sender() {
        return message_sender;
    }

    public void setMessage_sender(String message_sender) {
        this.message_sender = message_sender;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getMessage_date_time() {
        return message_date_time;
    }

    public void setMessage_date_time(String message_date_time) {
        this.message_date_time = message_date_time;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
}
