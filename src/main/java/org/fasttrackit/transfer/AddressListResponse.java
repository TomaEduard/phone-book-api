package org.fasttrackit.transfer;

import org.fasttrackit.domain.PhoneAddress;

import java.util.List;

public class AddressListResponse {

    private List<PhoneAddress> content;

    public AddressListResponse(List<PhoneAddress> content) {
        this.content = content;
    }

    public AddressListResponse() {
        //        Use for serializing/marshalling
    }

    public List<PhoneAddress> getContent() {
        return content;
    }

    public void setContent(List<PhoneAddress> content) {
        this.content = content;
    }
}
