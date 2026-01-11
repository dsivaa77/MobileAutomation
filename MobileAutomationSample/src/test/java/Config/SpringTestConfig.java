package Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
        "Config",
        "Pages",
        "Utils"
})
@Import(MobileDriverConfig.class)
public class SpringTestConfig {
}
