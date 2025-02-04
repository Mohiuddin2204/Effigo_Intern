package com.springboot.onetoone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.onetoone.model.Address;
import com.springboot.onetoone.model.User;
import com.springboot.onetoone.repository.AddressRepository;
import com.springboot.onetoone.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    
    public void deleteAddress(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();

            // Find and update the associated user
            Optional<User> userOptional = userRepository.findByAddress(address);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userRepository.deleteById(user.getId());  // Remove the reference to the address
                userRepository.save(user);
            }

            // Delete the address
            addressRepository.deleteById(id);
        } else {
            throw new RuntimeException("Address not found with ID: " + id);
        }
    }
}