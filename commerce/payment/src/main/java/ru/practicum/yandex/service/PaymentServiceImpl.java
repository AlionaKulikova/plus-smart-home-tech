package ru.practicum.yandex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.yandex.mapper.PaymentMapper;
import ru.practicum.yandex.model.Payment;
import ru.practicum.yandex.repository.PaymentRepository;
import ru.yandex.practicum.dto.OrderDto;
import ru.yandex.practicum.dto.PaymentDto;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.exeption.NotFoundException;
import ru.yandex.practicum.order.OrderClient;
import ru.yandex.practicum.shoppingstore.ShoppingStoreClient;
import ru.yandex.practicum.types.PaymentStatus;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	private final PaymentRepository paymentRepository;
	private final PaymentMapper paymentMapper;
	private final OrderClient orderClient;
	private final ShoppingStoreClient storeClient;

	@Override
	public PaymentDto addPayment(OrderDto orderDto) {
		checkOrder(orderDto);
		Payment payment = Payment.builder()
				.orderId(orderDto.getOrderId())
				.totalPayment(orderDto.getTotalPrice())
				.deliveryTotal(orderDto.getDeliveryPrice())
				.feeTotal(orderDto.getTotalPrice() * 0.1)
				.status(PaymentStatus.PENDING)
				.build();

		return paymentMapper.paymentToPaymentDto(paymentRepository.save(payment));
	}

	@Transactional(readOnly = true)
	@Override
	public double getTotalCost(OrderDto orderDto) {
		checkOrder(orderDto);

		return orderDto.getProductPrice() + (orderDto.getProductPrice() * 0.1) + orderDto.getDeliveryPrice();
	}

	@Override
	public void refundPayment(String paymentId) {
		Payment payment = getPayment(paymentId);
		orderClient.paymentOrder(payment.getOrderId());
		payment.setStatus(PaymentStatus.SUCCESS);
		paymentRepository.save(payment);
	}

	@Transactional(readOnly = true)
	@Override
	public double getProductsCost(OrderDto orderDto) {
		Map<String, Long> products = orderDto.getProducts();
		if (products == null)
			throw new NotFoundException("Отсутствуют товары в заказе");
		return products.entrySet().stream()
				.mapToDouble(product -> {
					ProductDto productDto = storeClient.getProductInfo(product.getKey());

					return productDto.getPrice() * product.getValue();
				})
				.sum();
	}

	@Override
	public void failedPayment(String paymentId) {
		Payment payment = getPayment(paymentId);
		orderClient.paymentOrderFailed(payment.getOrderId());
		payment.setStatus(PaymentStatus.FAILED);
		paymentRepository.save(payment);

	}

	private void checkOrder(OrderDto orderDto) {
		if (orderDto.getDeliveryPrice() == null)
			throw new NotFoundException("Отсутствует стомость доставки");
		if (orderDto.getTotalPrice() == null)
			throw new NotFoundException("Отсутствует общая сумма заказа");
		if (orderDto.getProductPrice() == null)
			throw new NotFoundException("Отсутствуют суммы по товарам");
	}

	private Payment getPayment(String paymentId) {
		Optional<Payment> product = paymentRepository.findById(paymentId);
		if (product.isEmpty())
			throw new NotFoundException("Платеж с id " + paymentId + " не найден");

		return product.get();
	}
}
