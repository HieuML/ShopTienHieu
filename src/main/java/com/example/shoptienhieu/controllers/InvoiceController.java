package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.constants.Path;
import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.invoiceReq.InvoiceRequest;
import com.example.shoptienhieu.dto.response.BaseRes;
import com.example.shoptienhieu.dto.response.BaseUpdatedRes;
import com.example.shoptienhieu.entities.Invoice;
import com.example.shoptienhieu.service.invoiceService.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "api/invoice")
public class InvoiceController {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(Path.GET_ALL)
    public ResponseEntity<?> getAllInvoice(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                           @RequestParam(value = "sortBy", required = false, defaultValue = "1") Integer sortBy,
                                           @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                           @RequestParam(value = "limit", required = false, defaultValue = "2") Integer limit) {

        logger.info("Request to getAll");
        Page<Invoice> data = invoiceService.getAll(sortBy, sort, page, limit);
        return ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.GET_ALL_INVOICE_SUCCESS, data));
    }

    @GetMapping(Path.GET_INVOICE_BY_USER)
    public ResponseEntity<?> getByUser(@RequestParam(value = "userId",required = false,defaultValue = "1") Integer userId,
                                       @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                       @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                       @RequestParam(value = "limit", required = false, defaultValue = "2") Integer limit) {

        logger.info("Request to getAll by userId");
        List<Invoice> data = invoiceService.getByUserId(userId, sort, page, limit);
        return ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.GET_ALL_INVOICE_SUCCESS, data));
    }

    @GetMapping(Path.GET_INVOICE_BY_DELIVERY)
    public ResponseEntity<?> getByDelivery(@PathVariable(name = "deliveryId") int deliveryId,
                                           @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                           @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                           @RequestParam(value = "limit", required = false, defaultValue = "2") Integer limit) {

        logger.info("Request to getAll by deliveryId");
        List<Invoice> data = invoiceService.getByUserId(deliveryId, sort, page, limit);
        return ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.GET_ALL_INVOICE_SUCCESS, data));
    }

    @GetMapping(Path.GET_INVOICE_BY_SHOP)
    public ResponseEntity<?> getByShop(@PathVariable(name = "shopId") int shopId,
                                       @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                       @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                       @RequestParam(value = "limit", required = false, defaultValue = "2") Integer limit) {

        logger.info("Request to getAll by shopId");
        List<Invoice> data = invoiceService.getByUserId(shopId, sort, page, limit);
        return ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.GET_ALL_INVOICE_SUCCESS, data));
    }

    @GetMapping(Path.GET_INVOICE_BY_PAYMENT)
    public ResponseEntity<?> getByPayment(@PathVariable(name = "paymentId") int paymentId,
                                          @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                          @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                          @RequestParam(value = "limit", required = false, defaultValue = "2") Integer limit) {

        logger.info("Request to getAll by paymentId");
        List<Invoice> data = invoiceService.getByUserId(paymentId, sort, page, limit);
        return ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.GET_ALL_INVOICE_SUCCESS, data));
    }

    @PostMapping(Path.CREATE)
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        logger.info("Request to Create");
        invoiceService.create(invoiceRequest);
        return ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.CREATE_INVOICE_SUCCESS));
    }

    @PutMapping(Path.UPDATE)
    public ResponseEntity<?> updateInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        logger.info("Request to update");
        invoiceService.updateById(invoiceRequest);
        return ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.UPDATE_INVOICE_SUCCESS, new BaseUpdatedRes(invoiceRequest.getInvoiceId(), true)));

    }

    @DeleteMapping(Path.DELETE)
    public ResponseEntity<?> deleteInvoice(@PathVariable String invoiceId) {
        logger.info("Request to delete");
        invoiceService.deleteById(invoiceId);
        return ResponseEntity.ok(new BaseRes<>(HttpStatus.OK.value(), TextStatus.DELETE_INVOICE_SUCCESS));
    }
}