package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_index_banner")
public class BaseIndexBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "banner_href_link")
    private String hrefLink;

    @Column(name = "banner_img_url")
    private String imgUrl;

    @Column(name = "banner_img_title")
    private String imgTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHrefLink() {
        return hrefLink;
    }

    public void setHrefLink(String hrefLink) {
        this.hrefLink = hrefLink;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }
}
