package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class OrderManager {

  private Map<String, Float> priceInfo = new HashMap<>();

  private PaymentService paymentService;

  public void setPaymentService(PaymentService paymentService) {
    System.out.println("payment service setter called");
    this.paymentService = paymentService;
  }

//  public void setPaymentService(PaymentService paymentService) {
//    System.out.println("setter method called");
//    this.paymentService = paymentService;
//  }


  public OrderManager(PaymentService paymentService) {
    System.out.println("order manager constructor called");
    this.paymentService = paymentService;
    priceInfo.put("LAPTOP", 49000f);
    priceInfo.put("PHONE", 14999.99f);
  }


  public String createOrder(List<String> orderItems, PaymentMethod paymentMethod) {
    System.out.println("received order request for " + orderItems + " by payment method " + paymentMethod);

    if (orderItems.contains("LAPTOP") && paymentMethod == PaymentMethod.COD) {
      throw new RuntimeException("COD not allowed for buying laptop");
    }

    // [1,2,3,4,5,6]

    Float amount = orderItems.stream().map(item -> priceInfo.get(item)).reduce((a,b) -> Float.sum(a,b)).get();

//    AtomicReference<Float> amount = new AtomicReference<>(0f);
//
//    orderItems.stream().forEach(item -> {
//      System.out.println("item received is " + item);
//      amount.updateAndGet(v -> v + priceInfo.get(item));
//    });

//    for(String orderItem : orderItems) {
//      amount += priceInfo.get(orderItem);
//    }

    paymentService.makePayment(paymentMethod, amount);

    return "order created";
  }

}
