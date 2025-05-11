package ru.yandex.practicum.model.hub.events;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.model.hub.enums.DeviceType;
import ru.yandex.practicum.model.hub.enums.HubEventType;

@Setter
@Getter
@ToString(callSuper = true)
public class EventDeviceAdded extends HubEvent {
    @NotBlank
    private String id;
    private DeviceType deviceType;

    @Override
    public HubEventType getType() {
        return HubEventType.DEVICE_ADDED;
    }
}