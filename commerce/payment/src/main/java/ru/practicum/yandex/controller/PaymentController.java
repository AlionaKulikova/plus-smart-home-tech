package ru.practicum.yandex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.yandex.service.PaymentService;
import ru.yandex.practicum.dto.OrderDto;
import ru.yandex.practicum.dto.PaymentDto;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
	private final PaymentService paymentService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public PaymentDto addPayment(@RequestBody OrderDto orderDto) {
		log.info("Запрос на формирование оплаты для заказа {}",orderDto);
		return paymentService.addPayment(orderDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/totalCost")
	public double getTotalCost(@RequestBody OrderDto orderDto) {
		log.info("Запрос на расчёт полной стоимости заказа {}", orderDto);
		return paymentService.getTotalCost(orderDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/refund")
	public void refundPayment(@RequestBody String paymentId) {
		log.info("Запрос метода для эмуляции успешной оплаты в платежного шлюза {}", paymentId);
		paymentService.refundPayment(paymentId);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/productCost")
	public double getProductsCost(@RequestBody OrderDto orderDto) {
		log.info("Запрос на расчёт стоимости товаров в заказе {}", orderDto);
		return paymentService.getProductsCost(orderDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/failed")
	public void failedPayment(@RequestBody String paymentId) {
		log.info("Запррос метода для эмуляции отказа в оплате платежного шлюза {}", paymentId);
		paymentService.failedPayment(paymentId);
	}
}
