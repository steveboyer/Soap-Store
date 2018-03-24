package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.Address;

public interface AddressRepository {
    Address create(Address address);
    Address read(String addressId);
    void update(String addressId, Address address);
    void delete(String addressId);
}
