package com.example.errorcontextrunner;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.test.context.assertj.AssertableApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

class ApplicationContextRunnerRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        springRegisterHints(hints,classLoader);
        applicationRegisterHints(hints,classLoader);
    }

    private void springRegisterHints(RuntimeHints hints, ClassLoader classLoader) {
        var reflection = hints.reflection();

        hints.proxies().registerJdkProxy(ConfigurableApplicationContext.class, AssertableApplicationContext.class);

        reflection.registerTypeIfPresent(classLoader,
                "org.springframework.context.annotation.ConfigurationClassPostProcessor",
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS);

        reflection.registerTypeIfPresent(classLoader,
                "org.springframework.context.event.EventListenerMethodProcessor",
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);

        reflection.registerTypeIfPresent(classLoader,
                "org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor",
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);

        reflection.registerTypeIfPresent(classLoader,
                "org.springframework.context.event.DefaultEventListenerFactory",
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);

        reflection.registerTypeIfPresent(classLoader,
                "org.springframework.context.annotation.CommonAnnotationBeanPostProcessor",
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);

        // Reactive
        reflection.registerTypeIfPresent(classLoader,
                "org.springframework.web.reactive.HandlerResult",
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);
    }

    private void applicationRegisterHints(RuntimeHints hints, ClassLoader classLoader) {
        // Local reflection
        hints.reflection().registerTypeIfPresent(classLoader,
                "com.example.errorcontextrunner.AutoConfig",
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);

        hints.resources().registerType(TypeReference.of("com.example.errorcontextrunner.AutoConfig"));
    }

}
