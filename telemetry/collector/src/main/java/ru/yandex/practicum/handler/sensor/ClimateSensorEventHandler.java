package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.handler.HandlerSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.ClimateSensorAvro;
import ru.yandex.practicum.model.sensor.enums.SensorEventType;
import ru.yandex.practicum.model.sensor.events.ClimateSensorEvent;
import ru.yandex.practicum.model.sensor.events.SensorEvent;

@HandlerSensorEvent(SensorEventType.CLIMATE_SENSOR_EVENT)
@Component
public class ClimateSensorEventHandler extends SensorEventHandler<ClimateSensorAvro> {
    public ClimateSensorEventHandler(Producer producer) {
        super(producer);
    }

    @Override
    protected ClimateSensorAvro mapToAvro(SensorEvent event) {
        ClimateSensorEvent sensorEvent = (ClimateSensorEvent) event;
        return ClimateSensorAvro.newBuilder()
                .setTemperatureC(sensorEvent.getTemperatureC())
                .setHumidity(sensorEvent.getHumidity())
                .setCo2Level(sensorEvent.getCo2Level())
                .build();
    }
}