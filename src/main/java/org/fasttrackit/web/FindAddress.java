package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.AddressService;
import org.fasttrackit.transfer.FirstNameLastNameAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/find-address")
public class FindAddress extends HttpServlet {

    private AddressService addressService = new AddressService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FirstNameLastNameAddress firstNameLastNameAddress = objectMapper.readValue(req.getReader(), FirstNameLastNameAddress.class);
            addressService.findAddressService(firstNameLastNameAddress);
        } catch (Exception e) {
            resp.sendError(500, "There was an error processing your requet.");
            e.getMessage();
        }
    }

}
