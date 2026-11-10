package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // ---------- Part A: happy path ----------

    @Test
    void letterGrade_95_isA() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    void letterGrade_85_isB() {
        assertEquals("B", calc.letterGrade(85));
    }

    @Test
    void gpa_95_85_is3_5() {
        assertEquals(3.5, calc.gpa(new double[] {95, 85}), 1e-9);
    }

    @Test
    void scholarship_gpa3_9_credits16_noViolation_is100() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    void scholarship_gpa3_5_credits12_noViolation_is70() {
        assertEquals(70, calc.scholarship(3.5, 12, false));
    }

    // ---------- Part B: 100% branch / line ----------

    // letterGrade
    @Test
    void letterGrade_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    void letterGrade_above100_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    @Test
    void letterGrade_75_isC() {
        assertEquals("C", calc.letterGrade(75));
    }

    @Test
    void letterGrade_65_isD() {
        assertEquals("D", calc.letterGrade(65));
    }

    @Test
    void letterGrade_50_isF() {
        assertEquals("F", calc.letterGrade(50));
    }

    // gpa: both || conditions of the guard true
    @Test
    void gpa_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    void gpa_empty_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[0]));
    }

    // gpa: C, D and default (F) switch branches
    @Test
    void gpa_75_65_50_is1_0() {
        assertEquals(1.0, calc.gpa(new double[] {75, 65, 50}), 1e-9);
    }

    // scholarship: each || condition of the guard true on its own
    @Test
    void scholarship_negativeGpa_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 15, false));
    }

    @Test
    void scholarship_gpaAbove4_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 15, false));
    }

    @Test
    void scholarship_negativeCredits_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.0, -1, false));
    }

    @Test
    void scholarship_violation_is0() {
        assertEquals(0, calc.scholarship(3.9, 16, true));
    }

    // gpa >= 3.8 true but credits >= 15 false -> falls to the 70 rule
    @Test
    void scholarship_gpa3_9_credits14_is70() {
        assertEquals(70, calc.scholarship(3.9, 14, false));
    }

    // gpa >= 3.4 true but credits >= 12 false -> falls to the 50 rule
    @Test
    void scholarship_gpa3_5_credits10_is50() {
        assertEquals(50, calc.scholarship(3.5, 10, false));
    }

    @Test
    void scholarship_gpa3_2_credits16_is50() {
        assertEquals(50, calc.scholarship(3.2, 16, false));
    }

    @Test
    void scholarship_gpa2_5_credits16_is0() {
        assertEquals(0, calc.scholarship(2.5, 16, false));
    }
}
