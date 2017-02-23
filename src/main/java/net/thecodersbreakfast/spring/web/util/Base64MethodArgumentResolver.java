package net.thecodersbreakfast.spring.web.util;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

public class Base64MethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Base64PathVariable.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Base64PathVariable annotation = parameter.getParameterAnnotation(Base64PathVariable.class);

        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Map<String, String> pathVariables = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String pathVariableName = annotation.value();
        String pathVariableValue = pathVariables.get(pathVariableName);

        if (pathVariableValue == null) {
            if (annotation.required()) {
                throw new MissingPathVariableException(pathVariableName, parameter);
            } else {
                return null;
            }
        } else {
            try {
                Base64.Decoder decoder = annotation.decoder() == Base64PathVariable.Decoder.BASIC ? Base64.getDecoder() : Base64.getUrlDecoder();
                byte[] bytes = decoder.decode(pathVariableValue);
                return new String(bytes, annotation.charset());
            } catch (IllegalArgumentException | UnsupportedEncodingException e) {
                throw new ServletRequestBindingException("Failed to base64-decode path variable " + pathVariableName, e);
            }
        }
    }

}
