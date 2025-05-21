package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.handler.HandlerSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorAvro;

@HandlerSensorEvent(SensorEventProto.PayloadCase.TEMPERATURE_SENSOR_EVENT)
@Component
public class TemperatureSensorEventHandler extends SensorEventHandler<TemperatureSensorAvro> {
    public TemperatureSensorEventHandler(Producer producer) {
        super(producer);
    }

    @Override
    protected TemperatureSensorAvro mapToAvro(SensorEventProto event) {
        ru.yandex.practicum.grpc.telemetry.event.TemperatureSensorEvent sensorEvent =  event.getTemperatureSensorEvent();
        return TemperatureSensorAvro.newBuilder()
                .setTemperatureC(sensorEvent.getTemperatureC())
                .setTemperatureF(sensorEvent.getTemperatureF())
                .build();
    }
}