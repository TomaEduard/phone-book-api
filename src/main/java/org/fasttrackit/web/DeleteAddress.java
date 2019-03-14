package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.AddressService;
import org.fasttrackit.transfer.AddressWithId;
import org.fasttrackit.transfer.IdAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-address")
public class DeleteAddress extends HttpServlet {

    private AddressService addressService = new AddressService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            IdAddress idAddress = objectMapper.readValue(req.getReader(), IdAddress.class);
            addressService.deleteAddress(idAddress);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }
}
