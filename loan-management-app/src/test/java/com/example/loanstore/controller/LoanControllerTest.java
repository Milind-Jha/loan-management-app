package com.example.loanstore.controller;

import com.example.loanstore.model.Loan;
import com.example.loanstore.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class LoanControllerTest {
    private MockMvc mockMvc;
    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
    }

    @Test
    void addLoan() throws Exception {
        // Given
        Loan loan = new Loan("L1", "C1", "LEN1", 10000, 10000, LocalDate.of(2023, 6, 5), 1, LocalDate.of(2023, 7, 5), 0.01);

        // Mock the service method to return the loan when save is called
        when(loanService.addLoan(any())).thenReturn(loan);

        // When and Then
        mockMvc.perform(post("/loan/addLoan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"loanId\": \"L1\",\n" +
                                "  \"customerId\": \"C1\",\n" +
                                "  \"lenderId\": \"LEN1\",\n" +
                                "  \"amount\": 10000,\n" +
                                "  \"remainingAmount\": 10000,\n" +
                                "  \"paymentDate\": \"2023-06-05\",\n" +
                                "  \"interestPerDay\": 1,\n" +
                                "  \"dueDate\": \"2023-07-05\",\n" +
                                "  \"penaltyPerDay\": 0.01\n" +
                                "}"))
                .andExpect(status().isOk());

        // Verify that the addLoan method in the service is called with the loan argument
        verify(loanService, times(1)).addLoan(any());
    }

    @Test
    public void testAggregateByLender() throws Exception {
        // Given
        Object[] lender1Data = new Object[] {"LEN1", 50000, 30000};
        Object[] lender2Data = new Object[] {"LEN2", 20000, 10000};
        List<Object[]> lenderDataList = Arrays.asList(lender1Data, lender2Data);

        // Mock the service method to return the lender data
        when(loanService.aggregateByLender()).thenReturn(lenderDataList);

        // When and Then
        mockMvc.perform(get("/loan/aggregateByLender"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0][0]").value("LEN1"))
                .andExpect(jsonPath("$[0][1]").value(50000))
                .andExpect(jsonPath("$[0][2]").value(30000))
                .andExpect(jsonPath("$[1][0]").value("LEN2"))
                .andExpect(jsonPath("$[1][1]").value(20000))
                .andExpect(jsonPath("$[1][2]").value(10000));
    }

    @Test
    public void testAggregateByInterest() throws Exception {
        // Given
        Object[] interest1Data = new Object[] {0.01, 10000, 5000};
        Object[] interest2Data = new Object[] {0.02, 20000, 10000};
        List<Object[]> interestDataList = Arrays.asList(interest1Data, interest2Data);

        // Mock the service method to return the interest data
        when(loanService.aggregateByInterest()).thenReturn(interestDataList);

        // When and Then
        mockMvc.perform(get("/loan/aggregateByInterest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0][0]").value(0.01))
                .andExpect(jsonPath("$[0][1]").value(10000))
                .andExpect(jsonPath("$[0][2]").value(5000))
                .andExpect(jsonPath("$[1][0]").value(0.02))
                .andExpect(jsonPath("$[1][1]").value(20000))
                .andExpect(jsonPath("$[1][2]").value(10000));
    }

    @Test
    public void testAggregateByCustomerId() throws Exception {
        // Given
        Object[] customer1Data = new Object[] {"C1", 50000, 30000};
        Object[] customer2Data = new Object[] {"C2", 20000, 10000};
        List<Object[]> customerDataList = Arrays.asList(customer1Data, customer2Data);

        // Mock the service method to return the customer data
        when(loanService.aggregateByCustomerId()).thenReturn(customerDataList);

        // When and Then
        mockMvc.perform(get("/loan/aggregateByCustomerId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0][0]").value("C1"))
                .andExpect(jsonPath("$[0][1]").value(50000))
                .andExpect(jsonPath("$[0][2]").value(30000))
                .andExpect(jsonPath("$[1][0]").value("C2"))
                .andExpect(jsonPath("$[1][1]").value(20000))
                .andExpect(jsonPath("$[1][2]").value(10000));
    }
}