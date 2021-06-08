package com.seniorkot.regioncatalog.domain.model.request;

import com.seniorkot.regioncatalog.utils.interfaces.RequestRequiredFields;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link com.seniorkot.regioncatalog.domain.model.Region}
 * POJO used in request bodies.
 *
 * @author seniorkot
 */
@Data
public class RegionRequest implements RequestRequiredFields {

    private String name;

    private String shortName;

    @Override
    public List<String> getBlankRequiredFields() {
        List<String> list = new ArrayList<>();
        if (StringUtils.isEmpty(this.name)) {
            list.add("name");
        }
        if (StringUtils.isEmpty(this.shortName)) {
            list.add("shortName");
        }
        return list;
    }
}
