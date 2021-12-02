package com.minhtuan.commercemanager.controller.customer;

import javax.servlet.http.HttpServletRequest;

import com.minhtuan.commercemanager.config.PaypalPaymentIntent;
import com.minhtuan.commercemanager.config.PaypalPaymentMethod;
import com.minhtuan.commercemanager.services.PaypalService;
import com.minhtuan.commercemanager.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PaymentController {
    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/pay")
    public String pay(@RequestParam("orderId") String orderId, @RequestParam("price") double price){
        String cancelUrl = "http://localhost:1038/paypal/cancle";
        String successUrl = "http://localhost:1038/paypal/success?orderId=" + orderId;
        try {
            price = (double) price/23000;
            Payment payment = paypalService.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    System.out.println(links.getHref());
                    return links.getHref() + "";
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay(){
        return "cancel";
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return "success";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
}
