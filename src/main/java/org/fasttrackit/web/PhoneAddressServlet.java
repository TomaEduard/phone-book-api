package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.AddressService;
import org.fasttrackit.transfer.AddressListResponse;
import org.fasttrackit.transfer.AddressWithId;
import org.fasttrackit.transfer.IdAddress;
import org.fasttrackit.transfer.SaveAddressRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/phone-address")
public class PhoneAddressServlet extends HttpServlet {

    private AddressService addressService = new AddressService();

    //    Create
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        ObjectMapper objectMapper = new ObjectMapper();
        SaveAddressRequest saveAddressRequest = objectMapper.readValue(req.getReader(), SaveAddressRequest.class);
        try {
            addressService.createAddressService(saveAddressRequest);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

    //    Read
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        try {
            AddressListResponse addressListResponse = addressService.getAddressList();

//            Cand transformi un obiect in string se numeste serializare(serializing/marshalling)
//            ObjectMapper objectMapper = new ObjectMapper();
            ObjectMapper objectMapper = new ObjectMapper();

            String responseJson = objectMapper.writeValueAsString(addressListResponse);

//            content type or mime type
            resp.setContentType("application/json");
            resp.getWriter().print(responseJson);
            resp.getWriter().flush();

        } catch (Exception e) {
            resp.sendError(500, "There was an error processing your requet.");
            e.getMessage();
        }
    }

    //    Update
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AddressWithId addressWithId = objectMapper.readValue(req.getReader(), AddressWithId.class);
            addressService.updateAddress(addressWithId);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

    //    Delete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        try {

            String id = req.getParameter("id");
            addressService.deleteAddress(Long.parseLong(id));

        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

//    //    Delete
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        setAccessControlHeaders(resp);
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            IdAddress idAddress = objectMapper.readValue(req.getReader(), IdAddress.class);
//            addressService.deleteAddress2(idAddress);
//        } catch (Exception e) {
//            resp.sendError(500, "Internal error: " + e.getMessage());
//        }
//    }

    //for Preflight request
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
