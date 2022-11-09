package de.neuefische.mucjava222orderdbweb.controller;

import de.neuefische.mucjava222orderdbweb.model.Product;
import de.neuefische.mucjava222orderdbweb.model.Order;
import de.neuefische.mucjava222orderdbweb.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop")
public class ShopController {

    private ShopService shopService;

    // Dependency Injection = Wofür genau?
    /*
    * Gibt Verantwortung an Spring ab
    * + Weniger Code schreiben
    *
    * Wir verwenden keine leeren Konstruktoren
    * -> Spring verknüpft unsere Abhängigkeiten automatisch
    *
    * -> Testen wird einfacher
    * */
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    // Das ist ein Endpunkt
    @GetMapping("products")
    public List<Product> getAllProducts() {
        return shopService.listProducts();
    }

    @PostMapping("products")
    public Product addProduct(@RequestBody Product product) {
        return shopService.addProduct(product);
    }

    // Das ist ein Endpunkt
    @GetMapping("orders")
    public List<Order> getAllOrders() {
        return shopService.listOrders();
    }
}
