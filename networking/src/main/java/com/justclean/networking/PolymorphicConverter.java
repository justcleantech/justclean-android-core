package com.justclean.networking;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Mario Gamal on 10/11/20
 * Copyright © 2020 Jusclean. All rights reserved.
 */
final class PolymorphicConverter {
    public static final class PolymorphicRequestBodyConverter<T>
            implements Converter<T, RequestBody> {

        private final Factory skipPast;
        private final Annotation[] parameterAnnotations;
        private final Annotation[] methodsAnnotations;
        private final Retrofit retrofit;
        private final Map<Class<?>, Converter<T, RequestBody>> cache = new LinkedHashMap<>();

        public static <T> Factory newFactory(final Class<T> baseType) {
            return new Factory() {
                @Override
                public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                                      Annotation[] parameterAnnotations,
                                                                      Annotation[] methodAnnotations,
                                                                      Retrofit retrofit) {
                    if (getRawType(type) != baseType) {
                        return null;
                    }
                    return new PolymorphicRequestBodyConverter<>(this, parameterAnnotations,
                            methodAnnotations, retrofit);
                }
            };
        }

        PolymorphicRequestBodyConverter(Factory skipPast, Annotation[] parameterAnnotations,
                                        Annotation[] methodsAnnotations, Retrofit retrofit) {
            this.skipPast = skipPast;
            this.parameterAnnotations = parameterAnnotations;
            this.methodsAnnotations = methodsAnnotations;
            this.retrofit = retrofit;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            Class<?> cls = value.getClass();
            Converter<T, RequestBody> requestBodyConverter;
            synchronized (cache) {
                requestBodyConverter = cache.get(cls);
            }
            if (requestBodyConverter == null) {
                requestBodyConverter =
                        retrofit.nextRequestBodyConverter(skipPast, cls, parameterAnnotations,
                                methodsAnnotations);
                synchronized (cache) {
                    cache.put(cls, requestBodyConverter);
                }
            }
            return requestBodyConverter.convert(value);
        }
    }
}