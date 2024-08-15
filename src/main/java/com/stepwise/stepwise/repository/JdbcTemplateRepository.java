package com.stepwise.stepwise.repository;

import com.stepwise.stepwise.dto.ConfUserDto;
import com.stepwise.stepwise.utils.DatabaseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateRepository {
    private final DatabaseUtils databaseUtils;

    public JdbcTemplateRepository(DatabaseUtils databaseUtils) {
        this.databaseUtils = databaseUtils;
    }

    public Page<ConfUserDto> getListUser(ConfUserDto dto) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        StringBuilder strSQL = new StringBuilder();

        strSQL.append(" select  * , null as user_password from uam.conf_user ");
        strSQL.append(" where 1 = 1 ");
      if(StringUtils.isNotEmpty(dto.getAllSearch())){
          strSQL.append(" and (user_username like '%'||:allSearch||'%'  OR  user_fname like '%'||:allSearch||'%' OR user_lname  like '%'||:allSearch||'%' ) ");
          parameters.addValue("allSearch" , dto.getAllSearch()) ;
      }

        return databaseUtils.pageQuery(
                strSQL.toString(),
                parameters,
                dto.getPage(),
                dto.getPerPage(),
                ConfUserDto.class);

    }
}
