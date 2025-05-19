package ru.yandex.practicum.handler.hub;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.handler.HandlerHubEvent;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;
import ru.yandex.practicum.model.hub.enums.HubEventType;
import ru.yandex.practicum.model.hub.events.EventScenarioRemoved;
import ru.yandex.practicum.model.hub.events.HubEvent;

@HandlerHubEvent(HubEventType.SCENARIO_REMOVED)
@Component
public class HubScenarioRemovedEventHandler extends HubEventHandler<ScenarioRemovedEventAvro> {

    public HubScenarioRemovedEventHandler(Producer producer) {
        super(producer);
    }

    @Override
    protected ScenarioRemovedEventAvro mapToAvro(HubEvent event) {
        EventScenarioRemoved removedEvent = (EventScenarioRemoved) event;
        return ScenarioRemovedEventAvro.newBuilder()
                .setName(removedEvent.getName())
                .build();
    }
}