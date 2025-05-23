package ru.yandex.practicum.model.hub.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.model.hub.enums.HubEventType;

import java.time.Instant;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        defaultImpl = HubEventType.class
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = EventDeviceAdded.class, name = "DEVICE_ADDED"),
        @JsonSubTypes.Type(value = EventDeviceRemoved.class, name = "DEVICE_REMOVED"),
        @JsonSubTypes.Type(value = EventScenarioAdded.class, name = "SCENARIO_ADDED"),
        @JsonSubTypes.Type(value = EventScenarioRemoved.class, name = "SCENARIO_REMOVED"),
})

@Getter
@Setter
@ToString
public abstract class HubEvent {
    @NotBlank
    private String hubId;
    private Instant timestamp = Instant.now();

    @NotNull
    public abstract HubEventType getType();
}