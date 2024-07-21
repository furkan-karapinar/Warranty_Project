package com.furkankarapinar.garanti_kontrol.service;

import com.furkankarapinar.garanti_kontrol.entity.Warranty;
import com.furkankarapinar.garanti_kontrol.repository.WarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class WarrantyService {

    @Autowired
    private WarrantyRepository warrantyRepository;

    /**
     * Adds a new warranty to the database.
     * @param warranty The warranty to be added
     * @return The added warranty
     */
    public Warranty addWarranty(Warranty warranty) {
        return warrantyRepository.save(warranty);
    }

    /**
     * Retrieves all warranties from the database.
     * @return A list of all warranties
     */
    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }

    /**
     * Retrieves a warranty by its ID.
     * @param id The ID of the warranty
     * @return An Optional containing the warranty if found, otherwise an empty Optional
     */
    public Optional<Warranty> getWarrantyById(Long id) {
        return warrantyRepository.findById(id);
    }

    /**
     * Saves a warranty to the database.
     * @param warranty The warranty to be saved
     * @return The saved warranty
     */
    public Warranty saveWarranty(Warranty warranty) {
        return warrantyRepository.save(warranty);
    }

    /**
     * Deletes a warranty from the database.
     * @param id The ID of the warranty to be deleted
     */
    public void deleteWarranty(Long id) {
        warrantyRepository.deleteById(id);
    }

    /**
     * Retrieves a warranty by its associated device ID.
     * @param deviceId The ID of the device
     * @return An Optional containing the warranty if found, otherwise an empty Optional
     */
    public Optional<Warranty> getWarrantyByDeviceId(Long deviceId) {
        return warrantyRepository.findByDeviceId(deviceId);
    }

    /**
     * Updates the warranty status to "No" if the warranty has expired.
     * @param deviceId The ID of the device associated with the warranty
     */
    public void updateWarrantyStatusIfExpired(Long deviceId) {
        Optional<Warranty> warrantyOptional = warrantyRepository.findByDeviceId(deviceId);
        if (warrantyOptional.isPresent()) {
            Warranty warranty = warrantyOptional.get();
            LocalDate purchaseDate = warranty.getPurchaseDate();
            LocalDate twoYearsAgo = LocalDate.now().minusYears(2);

            if (purchaseDate.isBefore(twoYearsAgo)) {
                warranty.setWarrantyStatus("No");
                warrantyRepository.save(warranty); // Save the updated warranty information
            }
        }
    }
}
