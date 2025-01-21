package com.example.study_hexagonal;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.*;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HexagonalArchitectureTests {
    private final JavaClasses classes = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS) // 테스트 클래스 구조는 제외
            .importPackages("com.example.study_hexagonal");


    @Test
    @Tag("critical")
    @Order(1)
    void 헥사고널_아키텍처_구조_검증() {
        ArchRule rule = onionArchitecture()
                .domainModels("com.example.study_hexagonal.domain..")
                .domainServices("com.example.study_hexagonal.application..")
                .applicationServices("com.example.study_hexagonal.infrastructure.config..")
                .adapter("web", "com.example.study_hexagonal.infrastructure.adapter.in.web..")
                .adapter("persistence", "com.example.study_hexagonal.infrastructure.adapter.out.persistence..")
                .adapter("cache", "com.example.study_hexagonal.infrastructure.adapter.out.cache..");

        rule.check(classes);
    }

    @Test
    @Tag("critical")
    void 도메인_계층은_다른_계층에_의존하지_않아야_함() {
        noClasses()
                .that()
                .resideInAPackage("com.example.study_hexagonal.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("com.example.study_hexagonal.application..", "com.example.study_hexagonal.infrastructure..")
                .check(classes);
    }

    @Test
    @Tag("critical")
    void 애플리케이션_계층은_인프라스트럭처_계층에_의존하지_않아야_함() {
        noClasses()
                .that()
                .resideInAPackage("com.example.study_hexagonal.application..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("com.example.study_hexagonal.infrastructure..")
                .check(classes);
    }

    @Test
    @Tag("critical")
    void 어댑터들은_서로_의존하지_않아야_함() {
        noClasses()
                .that()
                .resideInAPackage("com.example.study_hexagonal.infrastructure.adapter.in..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("com.example.study_hexagonal.infrastructure.adapter.out..")
                .check(classes);

        noClasses()
                .that()
                .resideInAPackage("com.example.study_hexagonal.infrastructure.adapter.out..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("com.example.study_hexagonal.infrastructure.adapter.in..")
                .check(classes);
    }

    @Test
    @Tag("critical")
    void 포트_인터페이스는_애플리케이션_계층에_있어야_함() {
        classes()
                .that().haveSimpleNameEndingWith("Port")
                .should().resideInAPackage("..application..")
                .check(classes);
    }

    @Test
    @Tag("critical")
    void 어댑터_클래스는_인프라스트럭처_계층에_있어야_함() {
        classes()
                .that().haveSimpleNameEndingWith("Adapter")
                .should().resideInAPackage("..infrastructure.adapter..")
                .check(classes);
    }

}
