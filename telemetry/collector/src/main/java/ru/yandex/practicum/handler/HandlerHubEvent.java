package ru.yandex.practicum.handler;

import ru.yandex.practicum.model.hub.enums.HubEventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HandlerHubEvent {
    HubEventType value();
}