package ru.yandex.practicum.service;

import ru.yandex.practicum.model.hub.events.HubEvent;
import ru.yandex.practicum.model.sensor.events.SensorEvent;

public interface EventService {
    void addSensorEvent(SensorEvent sensorEvent);

    void addHubEvent(HubEvent hubEvent);
}