package it.nave.test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=without-converter")
class WithoutConverterTest extends BaseTest {
}
