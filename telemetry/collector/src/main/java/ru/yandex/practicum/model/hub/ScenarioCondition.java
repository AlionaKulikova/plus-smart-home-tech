package ru.yandex.practicum.model.hub;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.model.hub.enums.ScenarioConditionOperationType;
import ru.yandex.practicum.model.hub.enums.ScenarioConditionType;

@Setter
@Getter
public class ScenarioCondition {
    private String sensorId;
    private ScenarioConditionType type;
    private ScenarioConditionOperationType operation;
    Object value;
}
