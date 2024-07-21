package com.furkankarapinar.garanti_kontrol.repository;

import com.furkankarapinar.garanti_kontrol.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    /**
     * Finds a device by its serial number.
     * @param serialNumber The serial number of the device
     * @return An Optional containing the device if found, otherwise an empty Optional
     */
    Optional<Device> findBySerialNumber(String serialNumber);
}