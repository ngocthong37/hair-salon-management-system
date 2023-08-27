package com.hairsalon.respository;

import com.hairsalon.model.PaymentMethodModel;

public interface IPaymentMethodRepository {
    PaymentMethodModel findById(Integer id);
}
