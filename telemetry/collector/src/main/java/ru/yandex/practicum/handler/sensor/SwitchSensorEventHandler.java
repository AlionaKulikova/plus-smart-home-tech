package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.handler.HandlerSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;
import ru.yandex.practicum.model.sensor.enums.SensorEventType;
import ru.yandex.practicum.model.sensor.events.SensorEvent;
import ru.yandex.practicum.model.sensor.events.SwitchSensorEvent;

@HandlerSensorEvent(SensorEventType.SWITCH_SENSOR_EVENT)
@Component
public class SwitchSensorEventHandler extends SensorEventHandler<SwitchSensorAvro> {
	public SwitchSensorEventHandler(Producer producer) {
		super(producer);
	}

	@Override
	protected SwitchSensorAvro mapToAvro(SensorEvent event) {
		SwitchSensorEvent sensorEvent = (SwitchSensorEvent) event;
		return SwitchSensorAvro.newBuilder()
				.setState(sensorEvent.getState())
				.build();
	}
}
