package com.example.Librarymanagementsystem.ControllersTest;

import com.example.Librarymanagementsystem.Controllers.PatronController;
import com.example.Librarymanagementsystem.Models.Patron;
import com.example.Librarymanagementsystem.Responses.GenaricResponse;
import com.example.Librarymanagementsystem.Responses.ResponseWithList;
import com.example.Librarymanagementsystem.Services.PatronService;
import com.example.Librarymanagementsystem.Services.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private PatronController patronController;
    private static final long ID = 1L;

    private Patron patron;

    @BeforeEach
    public void setUp() {
        patron = new Patron();
        patron.setId(1L);
        patron.setEmail("test@example.com");
    }

    @Test
    public void testAddPatron_validEmail_returnsCreated() {
        // Given
        when(validationService.validateEmail(patron.getEmail())).thenReturn(true);
        when(patronService.addPatron(patron)).thenReturn(true);

        // When
        ResponseEntity<GenaricResponse> response = patronController.addPatron(patron);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("patron added successfully", response.getBody().getMessage());
    }

    @Test
    public void testAddPatron_invalidEmail_returnsBadRequest() {
        // Given
        when(validationService.validateEmail(patron.getEmail())).thenReturn(false);

        // When
        ResponseEntity<GenaricResponse> response = patronController.addPatron(patron);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("email is not valid", response.getBody().getMessage());
    }

    @Test
    public void testAddPatron_existingPatron_returnsConflict() {
        // Given
        when(validationService.validateEmail(patron.getEmail())).thenReturn(true);
        when(patronService.addPatron(patron)).thenReturn(false);

        // When
        ResponseEntity<GenaricResponse> response = patronController.addPatron(patron);

        // Then
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("patron is already added", response.getBody().getMessage());
    }

    @Test
    public void testUpdatePatron_whenPatronExists_returnsOkStatus() {
        // Given
        when(patronService.updatePatron(any(Patron.class))).thenReturn(true);

        // When
        ResponseEntity<GenaricResponse> response = patronController.updatePatron(patron);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("patron updated successfully", response.getBody().getMessage());
    }

    @Test
    public void testUpdatePatron_whenPatronDoesNotExist_returnsNotFoundStatus() {
        // Given
        when(patronService.updatePatron(any(Patron.class))).thenReturn(false);

        // When
        ResponseEntity<GenaricResponse> response = patronController.updatePatron(patron);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("patron is not updated", response.getBody().getMessage());
    }

    @Test
    public void testDeletePatron_whenPatronExists_thenReturnsNoContent() {
        // Arrange
        when(patronService.deletePatron(ID)).thenReturn(true);

        // Act
        ResponseEntity<GenaricResponse> response = patronController.deletePatron(ID);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeletePatron_whenPatronDoesNotExist_thenReturnsNotFound() {
        // Arrange
        when(patronService.deletePatron(ID)).thenReturn(false);

        // Act
        ResponseEntity<GenaricResponse> response = patronController.deletePatron(ID);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testGetAllPatrons_ReturnsAllPatrons() {
        // Given
        List<Patron> patrons = Arrays.asList(
                new Patron(1L, "John Doe", "john.doe@example.com"),
                new Patron(2L, "Jane Doe", "jane.doe@example.com")
        );
        when(patronService.getAllPatrons()).thenReturn(patrons);

        // When
        ResponseEntity<ResponseWithList<Patron>> response = patronController.getAllPatrons();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patrons retrived successfully", response.getBody().getMessage());
        assertEquals(patrons, response.getBody().getData());
    }
}
