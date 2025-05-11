package ru.yandex.practicum.service;

import lombok.AllArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exception.NotFoundException;
import ru.yandex.practicum.handler.Handler;
import ru.yandex.practicum.handler.hub.HubEventHandler;
import ru.yandex.practicum.handler.sensor.SensorEventHandler;
import ru.yandex.practicum.model.hub.events.HubEvent;
import ru.yandex.practicum.model.sensor.events.SensorEvent;

@Service
@AllArgsConstructor
public class EventServiceManager implements EventService{
    private final Handler handler;

    @Override
    public void addSensorEvent(SensorEvent sensorEvent) {
        SensorEventHandler<? extends SpecificRecordBase> sensorEventHandler = handler
                .getSensorEventHandlers().get(sensorEvent.getType());
        if (sensorEventHandler != null)
            sensorEventHandler.handle(sensorEvent);
        else
            throw new NotFoundException("Тип " + sensorEvent.getType() + " не найден");
    }

    @Override
    public void addHubEvent(HubEvent hubEvent) {
        HubEventHandler<? extends SpecificRecordBase> hubEventHandler = handler
                .getHubEventHandlers().get(hubEvent.getType());
        if (hubEventHandler != null)
            hubEventHandler.handle(hubEvent);
        else
            throw new NotFoundException("Тип " + hubEvent.getType() + " не найден");
    }

}
