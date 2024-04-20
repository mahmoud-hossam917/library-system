package com.example.Librarymanagementsystem.ServicesTest;


import com.example.Librarymanagementsystem.Models.Patron;
import com.example.Librarymanagementsystem.Services.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatronServiceTest {

    @Mock
    private PatronService patronService;



    private Patron patron;

    @BeforeEach
    public void setUp() {
        patron = new Patron( "Test Patron","test@example.com");
    }

    @Test
    public void testAddPatron() {
        when(patronService.addPatron(patron)).thenReturn(true);
        assertTrue(patronService.addPatron(patron));
    }

    @Test
    public void testDeletePatron() {
        when(patronService.deletePatron(1L)).thenReturn(true);
        assertTrue(patronService.deletePatron(1L));
    }

    @Test
    public void testUpdatePatron() {
        when(patronService.updatePatron(patron)).thenReturn(true);
        assertTrue(patronService.updatePatron(patron));
    }

    @Test
    public void testGetPatronByEmail() {
        when(patronService.getPatronByEmail("test@example.com")).thenReturn(Optional.of(patron));
        assertEquals(Optional.of(patron), patronService.getPatronByEmail("test@example.com"));
    }

    @Test
    public void testGetAllPatrons() {
        when(patronService.getAllPatrons()).thenReturn(Collections.singletonList(patron));
        assertEquals(Collections.singletonList(patron), patronService.getAllPatrons());
    }

    @Test
    public void testFindById() {
        when(patronService.findById(1L)).thenReturn(Optional.of(patron));
        assertEquals(Optional.of(patron), patronService.findById(1L));
    }
}