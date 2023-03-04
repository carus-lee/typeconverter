package hello.typeconverter.formatter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionServiceTest() {

        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        //컨버터 등록
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());

        //포맷터 등록
        conversionService.addFormatter(new MyNumberFormatter());

        //컨버터 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class); // StringToIpPortConverter
        Assertions.assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        //포맷터 사용
//        Integer result = conversionService.convert("1,000", Integer.class);
//        Assertions.assertThat(result).isEqualTo(1000);
        Assertions.assertThat(conversionService.convert("1,000", Integer.class)).isEqualTo(1000);

//        String result2 = conversionService.convert(1000, String.class);
//        Assertions.assertThat(result2).isEqualTo("1,000");
        Assertions.assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");
    }
}
