package org.fasttrackit.service;

import org.fasttrackit.domain.PhoneAddress;
import org.fasttrackit.persistence.AddressRepository;
import org.fasttrackit.transfer.AddressListResponse;
import org.fasttrackit.transfer.AddressWithId;
import org.fasttrackit.transfer.SaveAddressRequest;

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
        List<PhoneAddress> phoneAddress = addressRepository.getPhoneAddress();
        return new AddressListResponse(phoneAddress);
    }

    public AddressWithId updateAddress(AddressWithId request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("*Update Address: " + request);
        return addressRepository.updateAddress(request);
    }

//    public PhoneAddress FindAddressService(SaveAddressRequest request) throws SQLException, IOException, ClassNotFoundException {
//        System.out.println("*Find Address: " + request);
//        return new AddressRepository().findAddress(request);
//    }

}
