package ru.yandex.practicum.handler.hub;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.grpc.telemetry.event.DeviceRemovedEventProto;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.handler.HandlerHubEvent;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;

@HandlerHubEvent(HubEventProto.PayloadCase.DEVICE_REMOVED)
@Component
public class HubDeviceRemovedEventHandler extends HubEventHandler<DeviceRemovedEventAvro> {

    public HubDeviceRemovedEventHandler(Producer producer) {
        super(producer);
    }

    @Override
    protected DeviceRemovedEventAvro mapToAvro(HubEventProto event) {
        DeviceRemovedEventProto hubEvent = event.getDeviceRemoved();
        return DeviceRemovedEventAvro.newBuilder()
                .setId(hubEvent.getId())
                .build();
    }
}