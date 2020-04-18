package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_reply")
public class BaseReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "message_id")
    private String message_id;

    @Column(name = "reply_content")
    private String reply_content;

    @Column(name = "reply_date_time")
    private String reply_date_time;

    @Column(name = "reply_user_name")
    private String reply_user_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getReply_date_time() {
        return reply_date_time;
    }

    public void setReply_date_time(String reply_date_time) {
        this.reply_date_time = reply_date_time;
    }

    public String getReply_user_name() {
        return reply_user_name;
    }

    public void setReply_user_name(String reply_user_name) {
        this.reply_user_name = reply_user_name;
    }
}
