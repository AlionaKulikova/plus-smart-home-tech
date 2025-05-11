package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.handler.HandlerSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;
import ru.yandex.practicum.model.sensor.enums.SensorEventType;
import ru.yandex.practicum.model.sensor.events.MotionSensorEvent;
import ru.yandex.practicum.model.sensor.events.SensorEvent;


@HandlerSensorEvent(SensorEventType.MOTION_SENSOR_EVENT)
@Component
public class MotionSensorEventHandler extends SensorEventHandler<MotionSensorAvro> {
	public MotionSensorEventHandler(Producer producer) {
		super(producer);
	}

	@Override
	protected MotionSensorAvro mapToAvro(SensorEvent event) {
		MotionSensorEvent sensorEvent = (MotionSensorEvent) event;
		return MotionSensorAvro.newBuilder()
				.setLinkQuality(sensorEvent.getLinkQuality())
				.setMotion(sensorEvent.getMotion())
				.setVoltage(sensorEvent.getVoltage())
				.build();
	}
}
