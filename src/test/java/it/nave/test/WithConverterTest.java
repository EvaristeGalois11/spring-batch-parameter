package it.nave.test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=with-converter")
class WithConverterTest extends BaseTest {
}
