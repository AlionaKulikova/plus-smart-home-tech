package ru.yandex.practicum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewOrder {
    @NotBlank
    private CartDto shoppingCart;

    @NotBlank
    private AddressWarehouseDto deliveryAddress;
}