package com.furkankarapinar.garanti_kontrol.controller;

import com.furkankarapinar.garanti_kontrol.entity.Warranty;
import com.furkankarapinar.garanti_kontrol.service.WarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/warranty")
public class WarrantyController {

    @Autowired
    private WarrantyService warrantyService; // Automatically injects the WarrantyService instance

    /**
     * Retrieves a list of all warranties.
     * @return A list of all warranties
     */
    @GetMapping
    public List<Warranty> getAllWarranties() {
        return warrantyService.getAllWarranties(); // Calls service to fetch all warranties
    }

    /**
     * Retrieves a specific warranty by its ID.
     * @param id The ID of the warranty
     * @return The warranty with the specified ID or a 404 Not Found if it doesn't exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Warranty> getWarrantyById(@PathVariable Long id) {
        Optional<Warranty> warranty = warrantyService.getWarrantyById(id); // Fetches the warranty by ID
        return warranty.map(ResponseEntity::ok) // Returns the warranty if present
                .orElseGet(() -> ResponseEntity.notFound().build()); // Returns 404 Not Found if warranty is absent
    }

    /**
     * Creates a new warranty.
     * @param warranty The warranty to create
     * @return The created warranty
     */
    @PostMapping
    public Warranty createWarranty(@RequestBody Warranty warranty) {
        return warrantyService.saveWarranty(warranty); // Saves and returns the new warranty
    }

    /**
     * Updates an existing warranty.
     * @param id The ID of the warranty to update
     * @param warrantyDetails The details to update
     * @return The updated warranty or a 404 Not Found if the warranty doesn't exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<Warranty> updateWarranty(@PathVariable Long id, @RequestBody Warranty warrantyDetails) {
        Optional<Warranty> warranty = warrantyService.getWarrantyById(id); // Fetches the warranty by ID
        if (warranty.isPresent()) {
            Warranty updatedWarranty = warranty.get();
            // Updates the warranty details
            updatedWarranty.setPurchaseDate(warrantyDetails.getPurchaseDate());
            updatedWarranty.setWarrantyStatus(warrantyDetails.getWarrantyStatus());
            updatedWarranty.setDevice(warrantyDetails.getDevice());
            return ResponseEntity.ok(warrantyService.saveWarranty(updatedWarranty)); // Saves and returns the updated warranty
        } else {
            return ResponseEntity.notFound().build(); // Returns 404 Not Found if warranty is absent
        }
    }

    /**
     * Deletes a warranty by its ID.
     * @param id The ID of the warranty to delete
     * @return 200 OK if deletion is successful, or 404 Not Found if the warranty doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarranty(@PathVariable Long id) {
        if (warrantyService.getWarrantyById(id).isPresent()) {
            warrantyService.deleteWarranty(id); // Deletes the warranty
            return ResponseEntity.ok().build(); // Returns 200 OK on successful deletion
        } else {
            return ResponseEntity.notFound().build(); // Returns 404 Not Found if warranty is absent
        }
    }
}
