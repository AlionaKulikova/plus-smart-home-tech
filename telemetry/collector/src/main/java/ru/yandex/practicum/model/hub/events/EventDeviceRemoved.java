package ru.yandex.practicum.model.hub.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.model.hub.enums.HubEventType;

@Setter
@Getter
@ToString(callSuper = true)
public class EventDeviceRemoved extends HubEvent {
    private String id;

    @Override
    public HubEventType getType() {
        return HubEventType.DEVICE_REMOVED;
    }
}