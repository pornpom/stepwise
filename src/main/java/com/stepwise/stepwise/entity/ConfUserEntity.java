package com.stepwise.stepwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conf_user", schema = "uam")
public class ConfUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_username", nullable = false, length = 16)
    private String userUsername;

    @Column(name = "user_fname", length = 128)
    private String userFname;

    @Column(name = "user_lname", length = 128)
    private String userLname;

    @Column(name = "status")
    private String status ;

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

    @Column(name = "user_type")
    private String userType;

    @Column(name = "email")
    private String email;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "identification_no")
    private String identificationNo;

    @PrePersist
    public void prePersist() {
        this.createDt = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDt = new Date(System.currentTimeMillis());
    }
}

