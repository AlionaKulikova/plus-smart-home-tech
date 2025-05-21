package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.grpc.telemetry.event.LightSensorEvent;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.handler.HandlerSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;

@HandlerSensorEvent(SensorEventProto.PayloadCase.LIGHT_SENSOR_EVENT)
@Component
public class LightSensorEventHandler extends SensorEventHandler<LightSensorAvro> {
	public LightSensorEventHandler(Producer producer) {
		super(producer);
	}

	@Override
	protected LightSensorAvro mapToAvro(SensorEventProto event) {
		LightSensorEvent sensorEvent = event.getLightSensorEvent();
		return LightSensorAvro.newBuilder()
				.setLinkQuality(sensorEvent.getLinkQuality())
				.setLuminosity(sensorEvent.getLuminosity())
				.build();
	}
}
