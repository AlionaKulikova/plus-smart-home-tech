package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.handler.HandlerSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;
import ru.yandex.practicum.model.sensor.enums.SensorEventType;
import ru.yandex.practicum.model.sensor.events.LightSensorEvent;
import ru.yandex.practicum.model.sensor.events.SensorEvent;

@HandlerSensorEvent(SensorEventType.LIGHT_SENSOR_EVENT)
@Component
public class LightSensorEventHandler extends SensorEventHandler<LightSensorAvro> {
	public LightSensorEventHandler(Producer producer) {
		super(producer);
	}

	@Override
	protected LightSensorAvro mapToAvro(SensorEvent event) {
		LightSensorEvent sensorEvent = (LightSensorEvent) event;
		return LightSensorAvro.newBuilder()
				.setLinkQuality(sensorEvent.getLinkQuality())
				.setLuminosity(sensorEvent.getLuminosity())
				.build();
	}
}
