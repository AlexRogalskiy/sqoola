package com.sensiblemetrics.api.sqoola.common.converter;

import com.sensiblemetrics.api.sqoola.common.AbstractBaseTest;
import com.sensiblemetrics.api.sqoola.common.model.wrapper.SegmentKey;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class SegmentConverterTest extends AbstractBaseTest {

    @Autowired
    private SegmentConverter.String2SegmentKey str2key;

    @Autowired
    private SegmentConverter.Integer2SegmentKey int2key;

    @Test
    public void testString2SegmentKeyConverter() {
        // given
        final String strId = "123456";
        final Long longId = 123456L;

        // when
        final SegmentKey result = this.str2key.convert(strId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getId()).isEqualTo(longId);
    }

    @Test
    public void testInteger2SegmentKeyConverter() {
        // given
        final Integer intId = 123456;
        final Long longId = 123456L;

        // when
        final SegmentKey result = this.int2key.convert(intId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getId()).isEqualTo(longId);
    }
}
