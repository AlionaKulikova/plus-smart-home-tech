package ru.yandex.practicum.handler;

import ru.yandex.practicum.model.sensor.enums.SensorEventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HandlerSensorEvent {
    SensorEventType value();
}