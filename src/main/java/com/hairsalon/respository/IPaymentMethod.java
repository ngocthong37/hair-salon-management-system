package com.hairsalon.respository;

import com.hairsalon.model.PaymentMethodModel;

public interface IPaymentMethod {
    PaymentMethodModel findById(Integer id);
}
