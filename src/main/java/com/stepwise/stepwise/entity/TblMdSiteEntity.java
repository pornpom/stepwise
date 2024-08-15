package com.stepwise.stepwise.entity;

import com.stepwise.stepwise.config.OauthUser;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_md_site", schema = "master")
public class TblMdSiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_id")
    private Integer siteId;

    @Column(name = "site_name", nullable = false)
    private String siteName;

    @Column(name = "location")
    private String location;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longtitude")
    private Double longitude;

    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "create_by", updatable = false)
    private Integer createBy;
    @Basic
    @Column(name = "create_dt", updatable = false)
    private Date createDt;
    @Basic
    @Column(name = "update_by", insertable = false)
    private Integer updateBy;
    @Basic
    @Column(name = "update_dt", insertable = false)
    private Date updateDt;


    @PrePersist
    public void prePersist() {
        this.createBy = OauthUser.oauthUserId();
        this.createDt = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    public void preUpdate() {
        this.updateBy = OauthUser.oauthUserId();
        this.updateDt = new Date(System.currentTimeMillis());
    }

}
