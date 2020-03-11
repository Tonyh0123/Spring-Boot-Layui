package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_notice")
public class BaseNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "notice_title")
    private String noticeTitle;

    @Column(name = "notice_date")
    private String noticeDate;

    @Column(name = "notice_url")
    private String noticeUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
}
