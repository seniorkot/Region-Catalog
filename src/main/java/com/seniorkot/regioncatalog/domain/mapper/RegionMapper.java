package com.seniorkot.regioncatalog.domain.mapper;

import com.seniorkot.regioncatalog.domain.model.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

/**
 * Region Mapper interface (MyBatis).
 *
 * @author seniorkot
 */
@Mapper
public interface RegionMapper {

    /**
     * Fetches all regions from DB.
     *
     * @return {@link List} of {@link Region} entities
     */
    @ResultMap("RegionResultMap")
    @Select("SELECT * FROM regions")
    List<Region> getAll();

    /**
     * Fetches a region by ID.
     *
     * @param id Region ID
     * @return {@link Optional} of {@link Region} entity
     */
    @ResultMap("RegionResultMap")
    @Select("SELECT * FROM regions WHERE id = #{id}")
    Optional<Region> getById(@Param("id") Long id);

    /**
     * Fetches a region by name and short name.
     *
     * @param name Region name
     * @param shortName Region short name
     * @return {@link Optional} of {@link Region} entity
     */
    @ResultMap("RegionResultMap")
    @Select("SELECT * FROM regions WHERE name = #{name} AND short_name = #{short_name}")
    Optional<Region> getByNames(@Param("name") String name,
                                @Param("short_name") String shortName);

    /**
     * Inserts new region to DB.
     *
     * @param region {@link Region} entity
     */
    @Insert("INSERT INTO regions (name, short_name) VALUES (#{name}, #{shortName})")
    void insert(Region region);

    /**
     * Updates existing region by ID.
     *
     * @param region {@link Region} entity
     */
    @Update("UPDATE regions SET name = #{name}, short_name = #{shortName} WHERE id = #{id}")
    void update(Region region);

    /**
     * Deletes a region.
     *
     * @param region {@link Region} entity
     */
    @Delete("DELETE FROM regions WHERE id = #{id}")
    void delete(Region region);

    /**
     * Deletes a region by ID.
     *
     * @param id {@link Region} ID
     */
    @Delete("DELETE FROM regions WHERE id = #{id}")
    void deleteById(@Param("id") Long id);

    /**
     * Returns a number of {@link Region} entities.
     *
     * @return Total number of entities
     */
    @Select("SELECT COUNT(*) FROM regions")
    int count();
}
