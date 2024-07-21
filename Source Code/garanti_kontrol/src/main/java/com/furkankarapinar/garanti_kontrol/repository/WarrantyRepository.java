package com.furkankarapinar.garanti_kontrol.repository;

import com.furkankarapinar.garanti_kontrol.entity.Device;
import com.furkankarapinar.garanti_kontrol.entity.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    /**
     * Finds a warranty by the device ID.
     * @param deviceId The ID of the device
     * @return An Optional containing the warranty if found, otherwise an empty Optional
     */
    Optional<Warranty> findByDeviceId(Long deviceId);

    /**
     * Finds a warranty by the associated device.
     * @param device The device associated with the warranty
     * @return The warranty associated with the device
     */
    Warranty findByDevice(Device device);

    /**
     * Deletes a warranty by the associated device.
     * @param device The device associated with the warranty
     */
    void deleteByDevice(Device device);
}

