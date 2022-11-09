package de.neuefische.mucjava222orderdbweb.controller;

import de.neuefische.mucjava222orderdbweb.model.Product;
import de.neuefische.mucjava222orderdbweb.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// Wir schreiben einen Integrationstest und stellen sicher dass
// - die HTTP Methoden ausgeführt werden
// - der Service läuft
// - das Repo die Daten hergibt

// @SpringBootTest = Fährt den Spring Kontext hoch (Server, Datenbankverbindung, etc.)
// @AutoConfigureMockMvc = Konfiguriert MockMvc nach unseren Testbedürfnissen
@SpringBootTest
@AutoConfigureMockMvc
class ShopControllerIntegrationTest {

    // Mock = So was wie Nachmachen
    // MockMvc ermöglicht uns Anfragen nachzumachen
    // Damit mockMvc NICHT null ist, müssen wir die Abhängigkeit, d.h. die DEPENDENCY bereitstellen...
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DirtiesContext
    // @DirtiesContext = FacilityManagement = Putzkraft = Räumt nach dem Test auf
    void getAllProducts_ShouldReturnProductsListWithSingleProduct() throws Exception {
        Product product1 = new Product("Rotkäpchen Sekt trocken", "1");
        productRepository.list().add(product1);

        mockMvc.perform(get("/shop/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                         {"name": "Rotkäpchen Sekt trocken",
                          "id": "1"
                        }
                        ]
                         """));
    }

    // Wenn keine Produkte in der Datenbank, dann gib leere Liste
    // mit when Muster:
    @Test
    void getAllProducts_ShouldReturnEmptyList_WhenNoProductsInDB() throws Exception {
        // Wir wollen die Endpunkte des ShopControllers testen
        // Z.B.
        // www.unser-shop.de/shop/products

        // Gewünschtes Ergebnis: []
        // D.h. eine leere Liste

        mockMvc.perform(get("/shop/products"))
                .andExpect(status().isOk())
                // Wir erwarten ein JSON
                // = Ein Textformat um Objekte darzustellen
                // Wir nutzen die drei Anführungsstiche,
                // um einen String über mehrere Zeile darzustellen

                // Es ist KEIN JSON
                // ... wir _benutzen_ es aber für JSON
                .andExpect(content().json(
                        """
                                []
                                """));
    }

    @Test
    void addProduct_Should() throws Exception {
        mockMvc.perform(post("/shop/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "name": "Rotkäpchen Sekt trocken",
                                  "id": "1"
                                }
                                 """)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                "name": "Rotkäpchen Sekt trocken",
                                  "id": "1"
                                }
                                 """
                ))
        ;
    }
}