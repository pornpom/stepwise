package com.stepwise.stepwise.service;

import com.stepwise.stepwise.dto.TblMdSiteDto;
import com.stepwise.stepwise.entity.TblMdSiteEntity;
import com.stepwise.stepwise.repository.TblMdSiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SiteService {
    private final ModelMapper modelMapper;
    private final TblMdSiteRepository tblMdSiteRepository;

    public SiteService(TblMdSiteRepository tblMdSiteRepository, ModelMapper modelMapper) {
        this.tblMdSiteRepository = tblMdSiteRepository;
        this.modelMapper = modelMapper;
    }

    public TblMdSiteDto saveSite(TblMdSiteDto dto){
        TblMdSiteEntity tblMdSiteEntity = modelMapper.map(dto ,TblMdSiteEntity.class );
        tblMdSiteRepository.save(tblMdSiteEntity);
        return dto;
    }
}
