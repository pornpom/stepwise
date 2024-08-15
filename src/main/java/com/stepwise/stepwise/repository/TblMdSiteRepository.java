package com.stepwise.stepwise.repository;

import com.stepwise.stepwise.entity.TblMdSiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblMdSiteRepository extends JpaRepository<TblMdSiteEntity, Integer> {
}
