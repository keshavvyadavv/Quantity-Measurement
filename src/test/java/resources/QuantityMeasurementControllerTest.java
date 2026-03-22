package resources;

import com.apps.controller.QuantityMeasurementController;
import com.apps.dto.QuantityDTO;
import com.apps.dto.QuantityInputDTO;
import com.apps.dto.QuantityMeasurementDTO;
import com.apps.service.IQuantityMeasurementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IQuantityMeasurementService service;

    private QuantityInputDTO validLengthInput;
    private QuantityMeasurementDTO sampleResult;

    @BeforeEach
    void setUp() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LengthUnit");
        validLengthInput = new QuantityInputDTO(q1, q2);

        sampleResult = new QuantityMeasurementDTO();
        sampleResult.setThisValue(1.0);
        sampleResult.setThisUnit("FEET");
        sampleResult.setThisMeasurementType("LengthUnit");
        sampleResult.setThatValue(12.0);
        sampleResult.setThatUnit("INCHES");
        sampleResult.setThatMeasurementType("LengthUnit");
        sampleResult.setOperation("compare");
        sampleResult.setResultString("true");
        sampleResult.setError(false);
    }

    // ─── Compare ─────────────────────────────────────────────────────────────

    @Test
    @WithMockUser
    @DisplayName("POST /compare - returns 200 with comparison result")
    void testCompare_success() throws Exception {
        Mockito.when(service.compare(Mockito.any(), Mockito.any()))
               .thenReturn(sampleResult);

        mockMvc.perform(post("/api/v1/quantities/compare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validLengthInput)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.resultString").value("true"))
               .andExpect(jsonPath("$.error").value(false));
    }

    // ─── Add ─────────────────────────────────────────────────────────────────

    @Test
    @WithMockUser
    @DisplayName("POST /add - returns 200 with addition result")
    void testAdd_success() throws Exception {
        QuantityMeasurementDTO addResult = new QuantityMeasurementDTO();
        addResult.setOperation("add");
        addResult.setResultValue(2.0);
        addResult.setResultUnit("FEET");
        addResult.setResultMeasurementType("LengthUnit");
        addResult.setError(false);

        Mockito.when(service.add(Mockito.any(), Mockito.any()))
               .thenReturn(addResult);

        mockMvc.perform(post("/api/v1/quantities/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validLengthInput)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.resultValue").value(2.0))
               .andExpect(jsonPath("$.resultUnit").value("FEET"));
    }

    // ─── Convert ─────────────────────────────────────────────────────────────

    @Test
    @WithMockUser
    @DisplayName("POST /convert - returns 200 with converted value")
    void testConvert_success() throws Exception {
        QuantityMeasurementDTO convertResult = new QuantityMeasurementDTO();
        convertResult.setOperation("convert");
        convertResult.setResultValue(12.0);
        convertResult.setError(false);

        Mockito.when(service.convert(Mockito.any(), Mockito.any()))
               .thenReturn(convertResult);

        mockMvc.perform(post("/api/v1/quantities/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validLengthInput)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.resultValue").value(12.0));
    }

    // ─── Subtract ────────────────────────────────────────────────────────────

    @Test
    @WithMockUser
    @DisplayName("POST /subtract - returns 200 with subtraction result")
    void testSubtract_success() throws Exception {
        QuantityMeasurementDTO subtractResult = new QuantityMeasurementDTO();
        subtractResult.setOperation("subtract");
        subtractResult.setResultValue(0.0);
        subtractResult.setResultUnit("FEET");
        subtractResult.setError(false);

        Mockito.when(service.subtract(Mockito.any(), Mockito.any()))
               .thenReturn(subtractResult);

        mockMvc.perform(post("/api/v1/quantities/subtract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validLengthInput)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.operation").value("subtract"));
    }

    // ─── Invalid input → 400 ─────────────────────────────────────────────────

    @Test
    @WithMockUser
    @DisplayName("POST /compare - invalid unit returns 400")
    void testCompare_invalidUnit_returns400() throws Exception {
        QuantityDTO bad1 = new QuantityDTO(1.0, "FOOT",  "LengthUnit");   // typo
        QuantityDTO bad2 = new QuantityDTO(12.0, "INCHE", "LengthUnit");  // typo
        QuantityInputDTO badInput = new QuantityInputDTO(bad1, bad2);

        mockMvc.perform(post("/api/v1/quantities/compare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(badInput)))
               .andExpect(status().isBadRequest());
    }

    // ─── History endpoints ────────────────────────────────────────────────────

    @Test
    @WithMockUser
    @DisplayName("GET /history/operation/COMPARE - returns 200 with list")
    void testGetOperationHistory() throws Exception {
        Mockito.when(service.getHistoryByOperation("COMPARE"))
               .thenReturn(List.of(sampleResult));

        mockMvc.perform(get("/api/v1/quantities/history/operation/COMPARE"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].operation").value("compare"));
    }

    @Test
    @WithMockUser
    @DisplayName("GET /history/type/LengthUnit - returns 200 with list")
    void testGetHistoryByType() throws Exception {
        Mockito.when(service.getHistoryByMeasurementType("LengthUnit"))
               .thenReturn(List.of(sampleResult));

        mockMvc.perform(get("/api/v1/quantities/history/type/LengthUnit"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].thisMeasurementType").value("LengthUnit"));
    }

    @Test
    @WithMockUser
    @DisplayName("GET /history/errored - returns 200 with empty list")
    void testGetErrorHistory() throws Exception {
        Mockito.when(service.getErrorHistory()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/quantities/history/errored"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }

    // ─── Count endpoint ───────────────────────────────────────────────────────

    @Test
    @WithMockUser
    @DisplayName("GET /count/COMPARE - returns 200 with count")
    void testGetOperationCount() throws Exception {
        Mockito.when(service.getOperationCount("COMPARE")).thenReturn(3L);

        mockMvc.perform(get("/api/v1/quantities/count/COMPARE"))
               .andExpect(status().isOk())
               .andExpect(content().string("3"));
    }
}