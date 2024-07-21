package com.furkankarapinar.garanti_kontrol.service;

import com.furkankarapinar.garanti_kontrol.dto.DeviceDTO;
import com.furkankarapinar.garanti_kontrol.entity.Device;
import com.furkankarapinar.garanti_kontrol.entity.Warranty;
import com.furkankarapinar.garanti_kontrol.repository.DeviceRepository;
import com.furkankarapinar.garanti_kontrol.repository.WarrantyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private WarrantyRepository warrantyRepository;

    /**
     * Retrieves a device by its serial number.
     * @param serialNumber The serial number of the device
     * @return An Optional containing the device if found, otherwise an empty Optional
     */
    public Optional<Device> getDeviceBySerialNumber(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber);
    }

    /**
     * Saves a device to the database.
     * @param device The device to be saved
     * @return The saved device
     */
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    /**
     * Retrieves all devices and maps them to DeviceDTO.
     * @return A list of DeviceDTOs representing all devices
     */
    public List<DeviceDTO> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream().map(device -> {
            Warranty warranty = warrantyRepository.findByDevice(device);
            DeviceDTO deviceDTO = new DeviceDTO();
            deviceDTO.setId(device.getId());
            deviceDTO.setBrand(device.getBrand());
            deviceDTO.setModel(device.getModel());
            deviceDTO.setSerialNumber(device.getSerialNumber());
            if (warranty != null) {
                deviceDTO.setPurchaseDate(warranty.getPurchaseDate());
                deviceDTO.setWarrantyStatus(warranty.getWarrantyStatus());
            }
            return deviceDTO;
        }).collect(Collectors.toList());
    }

    /**
     * Retrieves a device by its ID and maps it to DeviceDTO.
     * @param id The ID of the device
     * @return An Optional containing the DeviceDTO if found, otherwise an empty Optional
     */
    public Optional<DeviceDTO> getDeviceById(Long id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            Warranty warranty = warrantyRepository.findByDevice(device);
            DeviceDTO deviceDTO = new DeviceDTO();
            deviceDTO.setId(device.getId());
            deviceDTO.setBrand(device.getBrand());
            deviceDTO.setModel(device.getModel());
            deviceDTO.setSerialNumber(device.getSerialNumber());
            if (warranty != null) {
                deviceDTO.setPurchaseDate(warranty.getPurchaseDate());
                deviceDTO.setWarrantyStatus(warranty.getWarrantyStatus());
            }
            return Optional.of(deviceDTO);
        }
        return Optional.empty();
    }

    /**
     * Adds a device and its associated warranty to the database.
     * @param device The device to be added
     * @param warranty The warranty associated with the device
     * @return A DeviceDTO containing details of the added device and warranty
     */
    @Transactional
    public DeviceDTO addDeviceWithWarranty(Device device, Warranty warranty) {
        Device savedDevice = deviceRepository.save(device);
        warranty.setDevice(savedDevice);
        warrantyRepository.save(warranty);
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(savedDevice.getId());
        deviceDTO.setBrand(savedDevice.getBrand());
        deviceDTO.setModel(savedDevice.getModel());
        deviceDTO.setSerialNumber(savedDevice.getSerialNumber());
        deviceDTO.setPurchaseDate(warranty.getPurchaseDate());
        deviceDTO.setWarrantyStatus(warranty.getWarrantyStatus());
        return deviceDTO;
    }

    /**
     * Updates an existing device and its associated warranty.
     * @param id The ID of the device to be updated
     * @param updatedDevice The updated device details
     * @param updatedWarranty The updated warranty details
     * @return A DeviceDTO containing details of the updated device and warranty
     */
    @Transactional
    public DeviceDTO updateDevice(Long id, Device updatedDevice, Warranty updatedWarranty) {
        Optional<Device> existingDeviceOptional = deviceRepository.findById(id);
        if (existingDeviceOptional.isPresent()) {
            Device existingDevice = existingDeviceOptional.get();
            existingDevice.setBrand(updatedDevice.getBrand());
            existingDevice.setModel(updatedDevice.getModel());
            existingDevice.setSerialNumber(updatedDevice.getSerialNumber());
            Device savedDevice = deviceRepository.save(existingDevice);

            Warranty existingWarranty = warrantyRepository.findByDevice(savedDevice);
            existingWarranty.setPurchaseDate(updatedWarranty.getPurchaseDate());
            existingWarranty.setWarrantyStatus(updatedWarranty.getWarrantyStatus());
            warrantyRepository.save(existingWarranty);

            DeviceDTO deviceDTO = new DeviceDTO();
            deviceDTO.setId(savedDevice.getId());
            deviceDTO.setBrand(savedDevice.getBrand());
            deviceDTO.setModel(savedDevice.getModel());
            deviceDTO.setSerialNumber(savedDevice.getSerialNumber());
            deviceDTO.setPurchaseDate(existingWarranty.getPurchaseDate());
            deviceDTO.setWarrantyStatus(existingWarranty.getWarrantyStatus());
            return deviceDTO;
        } else {
            throw new RuntimeException("Device not found with id: " + id);
        }
    }

    /**
     * Deletes a device and its associated warranty from the database.
     * @param id The ID of the device to be deleted
     */
    @Transactional
    public void deleteDevice(Long id) {
        Optional<Device> existingDeviceOptional = deviceRepository.findById(id);
        if (existingDeviceOptional.isPresent()) {
            Device existingDevice = existingDeviceOptional.get();
            warrantyRepository.deleteByDevice(existingDevice);
            deviceRepository.delete(existingDevice);
        } else {
            throw new RuntimeException("Device not found with id: " + id);
        }
    }
}
