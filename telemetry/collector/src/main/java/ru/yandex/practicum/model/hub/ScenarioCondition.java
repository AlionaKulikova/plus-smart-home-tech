package ru.yandex.practicum.model.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.model.hub.enums.ScenarioConditionOperationType;
import ru.yandex.practicum.model.hub.enums.ScenarioConditionType;

@Setter
@Getter
public class ScenarioCondition {
    @NotBlank
    private String sensorId;
    @NotNull
    private ScenarioConditionType type;
    @NotNull
    private ScenarioConditionOperationType operation;
    @NotNull
    Object value;
}
