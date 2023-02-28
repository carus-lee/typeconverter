package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void IntegerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @Test
    void stringToIpPort() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
//        IpPort converter = new IpPort("127.0.0.1", 8080);
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);
//        assertThat(result.getIp()).isEqualTo("127.0.0.1");
//        assertThat(result.getPort()).isEqualTo(8080);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080)); // @EqualsAndHashCode 에노테이션으로 인해 객체의 Data 값 비교가 가능해짐
    }

    @Test
    void IpPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }
}
