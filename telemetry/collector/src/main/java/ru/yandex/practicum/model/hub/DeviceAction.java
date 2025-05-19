package ru.yandex.practicum.model.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.model.hub.enums.ActionType;

@Setter
@Getter
public class DeviceAction {
    @NotBlank
    private String sensorId;
    @NotNull(message = "type не может быть null")
    private ActionType type;
    Integer value;
}