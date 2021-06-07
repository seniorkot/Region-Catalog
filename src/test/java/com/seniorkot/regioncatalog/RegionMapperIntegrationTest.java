package com.seniorkot.regioncatalog;

import com.seniorkot.regioncatalog.domain.mapper.RegionMapper;
import com.seniorkot.regioncatalog.domain.model.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Integration test for {@link RegionMapper}.
 *
 * @author seniorkot
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionMapperIntegrationTest {

    @Autowired
    private RegionMapper regionMapper;

    @Test
    public void simpleTest() {
        Region region = regionMapper.getRegion(1L);

        assertThat(region).isNotNull();
        assertThat(region.getId()).isEqualTo(1L);
        assertThat(region.getName()).isEqualTo("г. Санкт-Петербург");
        assertThat(region.getShortName()).isEqualTo("СПб");
    }
}
