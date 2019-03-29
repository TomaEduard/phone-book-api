package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.AddressService;
import org.fasttrackit.transfer.AddressListResponse;
import org.fasttrackit.transfer.FirstNameLastNameAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/find-address")
public class FindAddressServlet extends HttpServlet {

    private AddressService addressService = new AddressService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        FirstNameLastNameAddress firstNameLastNameAddress = objectMapper.readValue(req.getReader(), FirstNameLastNameAddress.class);
        try {
            addressService.findAddressService(firstNameLastNameAddress);

        } catch (Exception e) {
            resp.sendError(500, "There was an error processing your requet.");
            e.getMessage();
        }
    }
}

//    //    Read
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        setAccessControlHeaders(resp);
//
//        try {
//            AddressListResponse addressListResponse = addressService.getAddressList();
//
////            ObjectMapper objectMapper = new ObjectMapper();
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            String responseJson = objectMapper.writeValueAsString(addressListResponse);
//
//            resp.setContentType("application/json");
//            resp.getWriter().print(responseJson);
//            resp.getWriter().flush();
//
//        } catch (Exception e) {
//            resp.sendError(500, "There was an error processing your requet.");
//            e.getMessage();
//        }
//    }