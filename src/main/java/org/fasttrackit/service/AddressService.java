package org.fasttrackit.service;

import org.fasttrackit.domain.PhoneAddress;
import org.fasttrackit.persistence.AddressRepository;
import org.fasttrackit.transfer.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddressService {

    private AddressRepository addressRepository = new AddressRepository();
    PhoneAddress phoneAddress = new PhoneAddress();

    public void createAddressService(SaveAddressRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("*Creating PhoneAddress:" + request);
        addressRepository.createAddress(request);
    }

    public AddressListResponse getAddressList() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("*Retriving Address List: ");
        List<PhoneAddress> phoneAddress = addressRepository.getAddress();
        return new AddressListResponse(phoneAddress);
    }

    public void updateAddress(AddressWithId request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("*Update Address: " + request);
        addressRepository.updateAddress(request);
    }

    public void deleteAddress(IdAddress request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("*Delete Address: " + request);
        addressRepository.deleteAddress(request);
    }

    public void deleteMultipleAddress(IdAddress request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("*Delete the last #" + request + " addresses");
        addressRepository.deleteMultipleAddress(request);
    }

    public AddressListResponse findAddressService(FirstNameLastNameAddress request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("*Retriving Address List: " + request);
        List<PhoneAddress> phoneAddress = addressRepository.findAddress(request);
        return new AddressListResponse(phoneAddress);
    }

}
