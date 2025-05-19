package ru.yandex.practicum.model.sensor.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.model.sensor.enums.SensorEventType;

@Setter
@Getter
@ToString
public class TemperatureSensorEvent extends SensorEvent {
    private Integer temperatureC;
    private Integer temperatureF;

    @Override
    public SensorEventType getType() {
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }
}