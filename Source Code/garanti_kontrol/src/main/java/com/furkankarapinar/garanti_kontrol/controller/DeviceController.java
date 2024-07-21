package com.furkankarapinar.garanti_kontrol.controller;

import com.furkankarapinar.garanti_kontrol.dto.DeviceDTO;
import com.furkankarapinar.garanti_kontrol.entity.Device;
import com.furkankarapinar.garanti_kontrol.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.furkankarapinar.garanti_kontrol.dto.DeviceWarrantyDTO;
import com.furkankarapinar.garanti_kontrol.entity.Warranty;
import com.furkankarapinar.garanti_kontrol.service.WarrantyService;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService; // Injects the DeviceService class automatically

    @Autowired
    private WarrantyService warrantyService; // Injects the WarrantyService class automatically

    /**
     * Searches for the device by serial number and returns relevant warranty information.
     * If there is no warranty information, it returns the warranty information as empty.
     * @param serialNumber Serial number of the device
     * @return DTO with device and warranty information
     */
    @GetMapping("/search")
    public ResponseEntity<DeviceWarrantyDTO> getDeviceBySerialNumber(@RequestParam String serialNumber) {
        // Bring the device according to the serial number
        Optional<Device> deviceOptional = deviceService.getDeviceBySerialNumber(serialNumber);
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            // Update warranty information
            warrantyService.updateWarrantyStatusIfExpired(device.getId());
            // Bring warranty information
            Optional<Warranty> warrantyOptional = warrantyService.getWarrantyByDeviceId(device.getId());

            if (warrantyOptional.isPresent()) {
                Warranty warranty = warrantyOptional.get();
                // Create DTO and add device and warranty information
                DeviceWarrantyDTO dto = new DeviceWarrantyDTO(
                        device.getId(),
                        device.getSerialNumber(),
                        device.getBrand(),
                        device.getModel(),
                        warranty.getPurchaseDate(),
                        warranty.getWarrantyStatus()
                );
                return ResponseEntity.ok(dto);
            } else {
                // If warranty information is not found, return warranty information as blank
                DeviceWarrantyDTO dto = new DeviceWarrantyDTO(
                        device.getId(),
                        device.getSerialNumber(),
                        device.getBrand(),
                        device.getModel(),
                        null,
                        null
                );
                return ResponseEntity.ok(dto);
            }
        } else {
            // Returns empty if device not found
            return ResponseEntity.ok().body(null);
        }
    }

    /** * Lists all devices * @return List of devices */
    @GetMapping("/all")
    public List<DeviceDTO> getAllDevices() {
        return deviceService.getAllDevices();
    }

    /** * Retrieves device by ID * @param id ID of the device * @return DTO with device information */
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> getDeviceById(@PathVariable Long id) {
        Optional<DeviceDTO> deviceOptional = deviceService.getDeviceById(id);
        if (deviceOptional.isPresent()) {
            return ResponseEntity.ok(deviceOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Adds a new device and warranty to the database.
     * @param deviceWarrantyDTO DTO containing device and warranty information
     * @return DTO containing device information after the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<DeviceDTO> addDevice(@RequestBody DeviceWarrantyDTO deviceWarrantyDTO) {
        // Create new device and warranty
        Device device = new Device();
        device.setBrand(deviceWarrantyDTO.getBrand());
        device.setModel(deviceWarrantyDTO.getModel());
        device.setSerialNumber(deviceWarrantyDTO.getSerialNumber());

        Warranty warranty = new Warranty();
        warranty.setPurchaseDate(deviceWarrantyDTO.getPurchaseDate());
        warranty.setWarrantyStatus(deviceWarrantyDTO.getWarrantyStatus());

        // Add device and warranty
        DeviceDTO savedDevice = deviceService.addDeviceWithWarranty(device, warranty);
        return ResponseEntity.ok(savedDevice);
    }

    /**
     * Updates an existing device and warranty
     * @param id ID of the device
     * @param deviceWarrantyDTO DTO with updated device and warranty information
     * @return DTO with updated device information
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable Long id, @RequestBody DeviceWarrantyDTO deviceWarrantyDTO) {
        // Create updated device and warranty
        Device updatedDevice = new Device();
        updatedDevice.setBrand(deviceWarrantyDTO.getBrand());
        updatedDevice.setModel(deviceWarrantyDTO.getModel());
        updatedDevice.setSerialNumber(deviceWarrantyDTO.getSerialNumber());

        Warranty updatedWarranty = new Warranty();
        updatedWarranty.setPurchaseDate(deviceWarrantyDTO.getPurchaseDate());
        updatedWarranty.setWarrantyStatus(deviceWarrantyDTO.getWarrantyStatus());

        // Update device and warranty
        DeviceDTO savedDevice = deviceService.updateDevice(id, updatedDevice, updatedWarranty);
        return ResponseEntity.ok(savedDevice);
    }

    /**
     * Deletes the device by ID
     * @param id ID of the device
     * @return Returns an empty response after deletion
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
