package tddmicroexercises.tirepressuremonitoringsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class AlarmTest {

    @Test
    public void by_default_alarm_is_off() {
        Alarm alarm = new Alarm();
        assertFalse(alarm.isAlarmOn());
    }

    @Test
    public void alarm_is_off_when_pressure_is_in_range() {
        Sensor fakeSensor = () -> 19;
        Alarm alarm = new Alarm(fakeSensor);
        alarm.check();
        assertFalse(alarm.isAlarmOn());
    }

    @Test
    public void alarm_is_on_when_pressure_is_too_low() {
        Sensor fakeSensor = () -> 4;
        Alarm alarm = new Alarm(fakeSensor);
        alarm.check();
        assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void alarm_is_on_when_pressure_is_too_high() {
        Sensor fakeSensor = () -> 25;
        Alarm alarm = new Alarm(fakeSensor);
        alarm.check();
        assertTrue(alarm.isAlarmOn());
    }

    @ParameterizedTest
    @CsvSource({"19,false", "4,true", "25,true"})
    public void alarm_is_depending_on_pressure(int fakePressure, boolean expectedResult) {
        Sensor fakeSensor = () -> fakePressure;
        Alarm alarm = new Alarm(fakeSensor);
        alarm.check();
        assertEquals(expectedResult, alarm.isAlarmOn());
    }
}
