package ro.hmihai.fms;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ro.hmihai.fms");

        noClasses()
            .that()
                .resideInAnyPackage("ro.hmihai.fms.service..")
            .or()
                .resideInAnyPackage("ro.hmihai.fms.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..ro.hmihai.fms.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
