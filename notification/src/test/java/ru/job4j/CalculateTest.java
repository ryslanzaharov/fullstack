package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CalculateTest {
    @Test
    public void whenOnePlusThenTwo() {
        assertThat(new Calculate().add(1, 1),
                is(2)
        );
    }

}
