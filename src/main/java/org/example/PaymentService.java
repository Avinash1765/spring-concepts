package org.example;

import java.util.List;

public class PaymentService {

  private List<PaymentMethod> acceptedPaymentMethods = List.of(PaymentMethod.UPI, PaymentMethod.DEBIT_CARD);

  public boolean makePayment(PaymentMethod paymentMethod, float amount) {
    if (!acceptedPaymentMethods.contains(paymentMethod)) {
      throw new RuntimeException("This payment method not accepted");
    }
    System.out.println("making payment by " + paymentMethod + " for amount " + amount);
    return true;
  }

}
