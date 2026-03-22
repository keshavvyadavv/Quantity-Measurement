package resources;

import com.apps.dto.QuantityDTO;
import com.apps.dto.QuantityInputDTO;
import com.apps.dto.QuantityMeasurementDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QuantityMeasurementApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + "/api/v1/quantities";
    }

    private QuantityInputDTO lengthInput(double v1, String u1, double v2, String u2) {
        return new QuantityInputDTO(
            new QuantityDTO(v1, u1, "LengthUnit"),
            new QuantityDTO(v2, u2, "LengthUnit")
        );
    }

    // ─── Application context ──────────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("Spring Boot application context loads successfully")
    void testApplicationContextLoads() {
        assertNotNull(restTemplate, "TestRestTemplate should be available");
    }

    // ─── Compare ─────────────────────────────────────────────────────────────

    @Test
    @Order(2)
    @DisplayName("POST /compare - 1 FEET == 12 INCHES should return true")
    void testCompareQuantities_equal() {
        QuantityInputDTO input = lengthInput(1.0, "FEET", 12.0, "INCHES");

        ResponseEntity<QuantityMeasurementDTO> response =
            restTemplate.postForEntity(baseUrl() + "/compare", input, QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("true", response.getBody().getResultString());
        assertFalse(response.getBody().isError());
    }

    @Test
    @Order(3)
    @DisplayName("POST /compare - 1 FEET != 1 INCHES should return false")
    void testCompareQuantities_notEqual() {
        QuantityInputDTO input = lengthInput(1.0, "FEET", 1.0, "INCHES");

        ResponseEntity<QuantityMeasurementDTO> response =
            restTemplate.postForEntity(baseUrl() + "/compare", input, QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("false", response.getBody().getResultString());
    }

    // ─── Convert ─────────────────────────────────────────────────────────────

    @Test
    @Order(4)
    @DisplayName("POST /convert - 1 FEET to INCHES should return 12.0")
    void testConvertQuantities() {
        QuantityInputDTO input = lengthInput(1.0, "FEET", 0.0, "INCHES");

        ResponseEntity<QuantityMeasurementDTO> response =
            restTemplate.postForEntity(baseUrl() + "/convert", input, QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(12.0, response.getBody().getResultValue(), 0.01);
    }

    // ─── Add ─────────────────────────────────────────────────────────────────

    @Test
    @Order(5)
    @DisplayName("POST /add - 1 FEET + 12 INCHES should return 2 FEET")
    void testAddQuantities() {
        QuantityInputDTO input = lengthInput(1.0, "FEET", 12.0, "INCHES");

        ResponseEntity<QuantityMeasurementDTO> response =
            restTemplate.postForEntity(baseUrl() + "/add", input, QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2.0, response.getBody().getResultValue(), 0.01);
        assertEquals("FEET", response.getBody().getResultUnit());
    }

    // ─── Subtract ────────────────────────────────────────────────────────────

    @Test
    @Order(6)
    @DisplayName("POST /subtract - 2 FEET - 12 INCHES should return 1 FEET")
    void testSubtractQuantities() {
        QuantityInputDTO input = lengthInput(2.0, "FEET", 12.0, "INCHES");

        ResponseEntity<QuantityMeasurementDTO> response =
            restTemplate.postForEntity(baseUrl() + "/subtract", input, QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1.0, response.getBody().getResultValue(), 0.01);
    }

    // ─── Divide ──────────────────────────────────────────────────────────────

    @Test
    @Order(7)
    @DisplayName("POST /divide - 2 FEET / 1 FEET should return 2.0")
    void testDivideQuantities() {
        QuantityInputDTO input = lengthInput(2.0, "FEET", 1.0, "FEET");

        ResponseEntity<QuantityMeasurementDTO> response =
            restTemplate.postForEntity(baseUrl() + "/divide", input, QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2.0, response.getBody().getResultValue(), 0.01);
    }

    // ─── Weight ───────────────────────────────────────────────────────────────

    @Test
    @Order(8)
    @DisplayName("POST /add - 1 KILOGRAM + 1000 GRAM should return 2 KG")
    void testAddWeightQuantities() {
        QuantityInputDTO input = new QuantityInputDTO(
            new QuantityDTO(1.0, "KILOGRAM", "WeightUnit"),
            new QuantityDTO(1000.0, "GRAM", "WeightUnit")
        );

        ResponseEntity<QuantityMeasurementDTO> response =
            restTemplate.postForEntity(baseUrl() + "/add", input, QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2.0, response.getBody().getResultValue(), 0.01);
    }

    // ─── Temperature convert ──────────────────────────────────────────────────

    @Test
    @Order(9)
    @DisplayName("POST /convert - 100 CELSIUS to FAHRENHEIT should return 212")
    void testConvertTemperature() {
        QuantityInputDTO input = new QuantityInputDTO(
            new QuantityDTO(100.0, "CELSIUS",    "TemperatureUnit"),
            new QuantityDTO(0.0,   "FAHRENHEIT", "TemperatureUnit")
        );

        ResponseEntity<QuantityMeasurementDTO> response =
            restTemplate.postForEntity(baseUrl() + "/convert", input, QuantityMeasurementDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(212.0, response.getBody().getResultValue(), 0.01);
    }

    // ─── Error scenarios ──────────────────────────────────────────────────────

    @Test
    @Order(10)
    @DisplayName("POST /add - incompatible types returns 400")
    void testAdd_incompatibleTypes_returns400() {
        QuantityInputDTO input = new QuantityInputDTO(
            new QuantityDTO(1.0, "FEET",     "LengthUnit"),
            new QuantityDTO(1.0, "KILOGRAM", "WeightUnit")
        );

        ResponseEntity<String> response =
            restTemplate.postForEntity(baseUrl() + "/add", input, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(11)
    @DisplayName("POST /compare - invalid unit returns 400")
    void testCompare_invalidUnit_returns400() {
        QuantityInputDTO input = new QuantityInputDTO(
            new QuantityDTO(1.0, "FOOT",  "LengthUnit"),
            new QuantityDTO(1.0, "INCHE", "LengthUnit")
        );

        ResponseEntity<String> response =
            restTemplate.postForEntity(baseUrl() + "/compare", input, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(12)
    @DisplayName("POST /divide - divide by zero returns 500")
    void testDivide_byZero_returns500() {
        QuantityInputDTO input = lengthInput(1.0, "FEET", 0.0, "INCHES");

        ResponseEntity<String> response =
            restTemplate.postForEntity(baseUrl() + "/divide", input, String.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // ─── History & count endpoints ────────────────────────────────────────────

    @Test
    @Order(13)
    @DisplayName("GET /history/operation/compare - returns list")
    void testGetCompareHistory() {
        ResponseEntity<List> response =
            restTemplate.getForEntity(baseUrl() + "/history/operation/compare", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(14)
    @DisplayName("GET /history/type/LengthUnit - returns list")
    void testGetHistoryByType() {
        ResponseEntity<List> response =
            restTemplate.getForEntity(baseUrl() + "/history/type/LengthUnit", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(15)
    @DisplayName("GET /history/errored - returns list")
    void testGetErrorHistory() {
        ResponseEntity<List> response =
            restTemplate.getForEntity(baseUrl() + "/history/errored", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(16)
    @DisplayName("GET /count/compare - returns non-negative count")
    void testGetOperationCount() {
        ResponseEntity<Long> response =
            restTemplate.getForEntity(baseUrl() + "/count/compare", Long.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() >= 0);
    }

    // ─── Actuator ─────────────────────────────────────────────────────────────

    @Test
    @Order(17)
    @DisplayName("GET /actuator/health - returns UP")
    void testActuatorHealth() {
        String url = "http://localhost:" + port + "/actuator/health";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("UP"));
    }

    // ─── Content negotiation ──────────────────────────────────────────────────

    @Test
    @Order(18)
    @DisplayName("POST /compare - response Content-Type is application/json")
    void testContentNegotiation_JSON() {
        QuantityInputDTO input = lengthInput(1.0, "FEET", 12.0, "INCHES");

        ResponseEntity<QuantityMeasurementDTO> response =
            restTemplate.postForEntity(baseUrl() + "/compare", input, QuantityMeasurementDTO.class);

        assertTrue(response.getHeaders().getContentType().toString()
                           .contains("application/json"));
    }
}