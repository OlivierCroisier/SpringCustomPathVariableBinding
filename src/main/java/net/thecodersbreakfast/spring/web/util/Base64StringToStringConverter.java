package net.thecodersbreakfast.spring.web.util;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Set;

public class Base64StringToStringConverter implements ConditionalGenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, String.class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.hasAnnotation(Base64.class);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Base64 annotation = targetType.getAnnotation(Base64.class);
        String value = (String) source;
        if (value == null) {
            return null;
        } else {
            try {
                java.util.Base64.Decoder decoder = annotation.decoder() == Base64.Decoder.BASIC ? java.util.Base64.getDecoder() : java.util.Base64.getUrlDecoder();
                byte[] bytes = decoder.decode(value);
                return new String(bytes, annotation.charset());
            } catch (IllegalArgumentException | UnsupportedEncodingException e) {
                throw new IllegalStateException("Failed to base64-decode value " + value, e);
            }
        }
    }

}
