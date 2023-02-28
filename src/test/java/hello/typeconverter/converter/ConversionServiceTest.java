package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        //컨버터 등록
        DefaultConversionService conversionService = new DefaultConversionService(); //ConversionService 인터페이스를 구현 (스프링 제공)
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        //컨버터 사용
//        Integer result = conversionService.convert("10", Integer.class);
        Assertions.assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        Assertions.assertThat(conversionService.convert(10, String.class)).isEqualTo("10");

        // StringToIpPortConverter
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        Assertions.assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        // IpPortToStringConverter
        String result = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        Assertions.assertThat(result).isEqualTo("127.0.0.1:8080");

    }

}
