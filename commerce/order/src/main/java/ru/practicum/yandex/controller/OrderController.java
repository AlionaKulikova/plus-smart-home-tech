package ru.practicum.yandex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.yandex.service.OrderService;
import ru.yandex.practicum.dto.NewOrder;
import ru.yandex.practicum.dto.OrderDto;
import ru.yandex.practicum.dto.ReturnProduct;

import java.util.List;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
	private final OrderService orderService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<OrderDto> getUserOrders(@RequestParam String username) {
		log.info("Запрос на получение заказов пользователя {}", username);
		return orderService.getUserOrders(username);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public OrderDto createNewOrder(@RequestBody NewOrder request) {
		log.info("Запрос на создание нового заказа {}", request);
		return orderService.createNewOrder(request);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/return")
	public OrderDto returnOrder(@RequestBody ReturnProduct request) {
		log.info("Запрос на возврат товара {}", request);
		return orderService.returnOrder(request);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/payment")
	public OrderDto paymentOrder(@RequestBody String orderId) {
		log.info("Запрос на оплату заказа {}", orderId);
		return orderService.paymentOrder(orderId);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/payment/failed")
	public OrderDto paymentOrderWithError(@RequestBody String orderId) {
		log.info("Запрос на оплату заказа  {} с ошибкой", orderId);
		return orderService.paymentOrderWithError(orderId);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/delivery")
	public OrderDto deliveryOrder(@RequestBody String orderId) {
		log.info("Запрос на доставку заказа {}", orderId);
		return orderService.deliveryOrder(orderId);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/delivery/failed")
	public OrderDto deliveryOrderWithError(@RequestBody String orderId) {
		log.info("Запрос на доставку заказа {} с ошибкой", orderId);
		return orderService.deliveryOrderWithError(orderId);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/completed")
	public OrderDto completedOrder(@RequestBody String orderId) {
		log.info("Заверешние заказа {}", orderId);
		return orderService.completedOrder(orderId);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/calculate/total")
	public OrderDto calculateTotalOrder(@RequestBody String orderId) {
		log.info("Запрос на расчет стоимости заказа {}", orderId);
		return orderService.calculateTotalOrder(orderId);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/calculate/delivery")
	public OrderDto calculateDeliveryOrder(@RequestBody String orderId) {
		log.info("Запрос на расчет стоимости доставки заказа {}", orderId);
		return orderService.calculateDeliveryOrder(orderId);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/assembly")
	public OrderDto assemblyOrder(@RequestBody String orderId) {
		log.info("Запрос на сборку заказа {}", orderId);
		return orderService.assemblyOrder(orderId);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/assembly/failed")
	public OrderDto assemblyOrderWithError(@RequestBody String orderId) {
		log.info("Запрос на сборку заказа {} с ошибкой", orderId);
		return orderService.assemblyOrderWithError(orderId);
	}
}
