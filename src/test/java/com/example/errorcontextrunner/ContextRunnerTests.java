package com.example.errorcontextrunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ImportRuntimeHints(ApplicationContextRunnerRuntimeHints.class)
@ExtendWith(SpringExtension.class)
public class ContextRunnerTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AutoConfig.class));

    @Test
    void testContextRunner() {
        contextRunner.run(context -> {
            assertThat(context).doesNotHaveBean("hello");
        });
    }


}


