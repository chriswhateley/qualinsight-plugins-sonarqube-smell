/*
 * This file is part of qualinsight-plugins-sonarqube-wtf-internal.
 *
 * qualinsight-plugins-sonarqube-wtf-internal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * qualinsight-plugins-sonarqube-wtf-internal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with qualinsight-plugins-sonarqube-wtf-internal.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.qualinsight.plugins.sonarqube.wtf.internal.extension;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.sonar.plugins.java.api.CheckRegistrar.RegistrarContext;
import com.qualinsight.plugins.sonarqube.wtf.internal.check.WTFCheck;

public class WTFChecksRegistrarTest {

    @SuppressWarnings("unchecked")
    @Test
    public void checkClasses_should_return_expectedChecks() {
        Assertions.assertThat(WTFChecksRegistrar.checkClasses())
            .containsExactly(WTFCheck.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCheckClasses_should_return_expectedChecks() {
        Assertions.assertThat(WTFChecksRegistrar.testCheckClasses())
            .containsExactly(WTFCheck.class);
    }

    @Test
    public void register_should_registerChecks() {
        final RegistrarContext context = new RegistrarContext();
        final WTFChecksRegistrar registrar = new WTFChecksRegistrar();
        registrar.register(context);
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(context.checkClasses())
            .containsExactlyElementsOf(WTFChecksRegistrar.checkClasses());
        softly.assertThat(context.testCheckClasses())
            .containsExactlyElementsOf(WTFChecksRegistrar.testCheckClasses());
        softly.assertThat(context.repositoryKey())
            .isEqualTo(WTFRulesDefinition.REPOSITORY_KEY);
        softly.assertAll();
    }
}