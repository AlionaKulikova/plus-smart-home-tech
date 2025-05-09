package ru.yandex.practicum.handler.hub;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Config;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.handler.HandlerHubEvent;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import ru.yandex.practicum.model.hub.enums.HubEventType;
import ru.yandex.practicum.model.hub.events.EventDeviceAdded;
import ru.yandex.practicum.model.hub.events.HubEvent;

@HandlerHubEvent(HubEventType.DEVICE_ADDED)
@Component
public class HubDeviceAddedEventHandler extends HubEventHandler<DeviceAddedEventAvro> {

	public HubDeviceAddedEventHandler(Config config, Producer producer) {

		super(config, producer);
	}

	@Override
	protected DeviceAddedEventAvro mapToAvro(HubEvent event) {
		EventDeviceAdded hubEvent = (EventDeviceAdded) event;
		return DeviceAddedEventAvro.newBuilder()
				.setId(hubEvent.getId())
				.setType(DeviceTypeAvro.valueOf(hubEvent.getDeviceType().name()))
				.build();
	}
}
