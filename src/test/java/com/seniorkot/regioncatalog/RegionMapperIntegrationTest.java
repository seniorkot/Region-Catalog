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
 * Integration test for the {@link RegionMapper}.
 *
 * @author seniorkot
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionMapperIntegrationTest {

    @Autowired
    private RegionMapper regionMapper;

    /**
     * Tests the region mapper for correct SELECT statements execution.
     */
    @Test
    public void idSelectTest() {
        int count = regionMapper.count();
        assertThat(count).isEqualTo(4);

        Region region = regionMapper.getById(1L).orElse(null);

        assertThat(region).isNotNull();
        assertThat(region.getId()).isEqualTo(1L);
        assertThat(region.getName()).isEqualTo("г. Санкт-Петербург");
        assertThat(region.getShortName()).isEqualTo("СПб");

        region = regionMapper.getByNames("г. Москва", "Мск").orElse(null);

        assertThat(region).isNotNull();
        assertThat(region.getId()).isEqualTo(2L);
        assertThat(region.getName()).isEqualTo("г. Москва");
        assertThat(region.getShortName()).isEqualTo("Мск");
    }

    /**
     * Tests the region mapper for correct INSERT, UPDATE and
     * DELETE statements execution.
     */
    @Test
    public void insertUpdateDeleteTest() {
        Region region = new Region();
        region.setName("Test full name");
        region.setShortName("Test short name");
        regionMapper.insert(region);

        Region fetched = regionMapper.getById(5L).orElse(null);

        assertThat(fetched).isNotNull();
        assertThat(fetched.getId()).isEqualTo(5L);
        assertThat(fetched.getName()).isEqualTo("Test full name");
        assertThat(fetched.getShortName()).isEqualTo("Test short name");

        fetched.setName("Test full name UPDATED");
        regionMapper.update(fetched);

        fetched = regionMapper.getById(5L).orElse(null);
        assertThat(fetched).isNotNull();
        assertThat(fetched.getId()).isEqualTo(5L);
        assertThat(fetched.getName()).isEqualTo("Test full name UPDATED");
        assertThat(fetched.getShortName()).isEqualTo("Test short name");

        regionMapper.delete(fetched);
        fetched = regionMapper.getById(5L).orElse(null);
        assertThat(fetched).isNull();
    }
}
