package ru.yandex.practicum.handler.hub;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.handler.HandlerHubEvent;
import ru.yandex.practicum.kafka.telemetry.event.*;
import ru.yandex.practicum.model.hub.enums.HubEventType;
import ru.yandex.practicum.model.hub.events.EventScenarioAdded;
import ru.yandex.practicum.model.hub.events.HubEvent;


import java.util.List;

@HandlerHubEvent(HubEventType.SCENARIO_ADDED)
@Component
public class HubScenarioAddedEventHandler extends HubEventHandler<ScenarioAddedEventAvro> {

	public HubScenarioAddedEventHandler(Producer producer) {
		super(producer);
	}

	@Override
	protected ScenarioAddedEventAvro mapToAvro(HubEvent event) {
		EventScenarioAdded hubEvent = (EventScenarioAdded) event;
		return ScenarioAddedEventAvro.newBuilder()
				.setName(hubEvent.getName())
				.setActions(deviceActionAvro(hubEvent))
				.setConditions(getScenarioConditionAvro(hubEvent))
				.build();
	}

	private List<DeviceActionAvro> deviceActionAvro(EventScenarioAdded event) {
		return event.getActions().stream()
				.map(action -> DeviceActionAvro.newBuilder()
						.setSensorId(action.getSensorId())
						.setType(ActionTypeAvro.valueOf(action.getType().name()))
						.setValue(action.getValue())
						.build())
				.toList();
	}

	private List<ScenarioConditionAvro> getScenarioConditionAvro(EventScenarioAdded event) {
		return event.getConditions().stream()
				.map(condition -> ScenarioConditionAvro.newBuilder()
						.setSensorId(condition.getSensorId())
						.setType(ConditionTypeAvro.valueOf(condition.getType().name()))
						.setOperation(ConditionOperationAvro.valueOf(condition.getOperation().name()))
						.setValue(condition.getValue())
						.build())
				.toList();
	}
}