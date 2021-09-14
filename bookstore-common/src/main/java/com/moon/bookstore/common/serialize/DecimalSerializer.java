package com.moon.bookstore.common.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.moon.bookstore.common.annotation.DecimalSerializeOpt;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * DecimalSerializer
 * @author yujiangtao
 * @date 2021/8/30 下午9:34
 */
public class DecimalSerializer extends JsonSerializer<BigDecimal> implements ContextualSerializer {

    private int scale = 2;

    private int roundingMode = BigDecimal.ROUND_HALF_UP;

    private String pattern = "";

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (StringUtils.hasText(pattern)) {
            DecimalFormat df = new DecimalFormat(pattern);
            gen.writeString(df.format(value));
        } else {
            gen.writeNumber(value.setScale(scale, roundingMode));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        // 判断 beanProperty 是不是空
        if (property == null) {
            return prov.findNullValueSerializer(null);
        }
        // 判断类型是否是 BigDecimal
        if (Objects.equals(property.getType().getRawClass(), BigDecimal.class)) {
            DecimalSerializeOpt annotation = property.getAnnotation(DecimalSerializeOpt.class);
            if (annotation != null) {
                pattern = annotation.pattern();
                scale = annotation.scale();
                roundingMode = annotation.roundingMode();
                return this;
            }
        }
        return prov.findValueSerializer(property.getType(), property);
    }
}
