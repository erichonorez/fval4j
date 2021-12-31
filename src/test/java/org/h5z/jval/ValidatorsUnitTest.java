package org.h5z.jval;

import static org.assertj.core.api.Assertions.assertThat;
import static org.h5z.jval.Validators.required;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ValidatorsUnitTest {

    @Nested
    @DisplayName("Required")
    class Required {

        @Test
        @DisplayName("Should fail if the value is null")
        public void test() {
            Trie<String> result = required(() -> "Required").apply(null);

            assertAll(
                () -> assertThat(result.isInvalid()).isTrue(),
                () -> assertThat(result.getErrors().size()).isEqualTo(1),
                () -> assertThat(result.getErrors().get(0)).isEqualTo("Required")
            );
        }
    }

}
