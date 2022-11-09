package de.neuefische.mucjava222orderdbweb.service;

import de.neuefische.mucjava222orderdbweb.model.Order;
import de.neuefische.mucjava222orderdbweb.repository.OrderRepository;
import de.neuefische.mucjava222orderdbweb.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShopServiceTest {

    // Was brauchen wir noch?
    OrderRepository orderRepository = mock(OrderRepository.class);
    ProductRepository productRepository = mock(ProductRepository.class);

    // Was wir testen wollen - MUSS echt sein
    ShopService shopService = new ShopService(orderRepository, productRepository);

    // Methodenname - Was SOLL passieren, wenn der Fall XYZ ist
    @Test
    void getOrder_shouldReturnOrder_whenOrderIsValid() {
        // GIVEN
        String orderId1 = "1";
        Order expectedOrder = new Order();

        when(orderRepository.get(orderId1)).thenReturn(expectedOrder);

        // WHEN
        Order result = shopService.getOrder(orderId1);

        // THEN
        // Ergebnisse vergleichen - result ist gleich Order mit der Id 1?
        assertEquals(expectedOrder, result);

        // Wurde eine bestimmte Methode aufgerufen?
        verify(orderRepository).get(any());
    }
}