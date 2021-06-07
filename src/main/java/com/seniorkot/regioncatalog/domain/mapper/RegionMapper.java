package com.seniorkot.regioncatalog.domain.mapper;

import com.seniorkot.regioncatalog.domain.model.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * Region Mapper interface (MyBatis).
 *
 * @author seniorkot
 */
@Mapper
public interface RegionMapper {

    @Select("SELECT * FROM regions WHERE id = #{id}")
    @ResultMap("RegionResultMap")
    Region getRegion(@Param("id") Long id);
}
