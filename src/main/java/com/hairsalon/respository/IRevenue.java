package com.hairsalon.respository;

public interface IRevenue {
    Double getRevenueFromService();
    Double getRevenueFromProduct();
    Double getRevenueFromServiceByYear(Integer year);
    Double getRevenueFromServiceByMonth(Integer year, Integer month);
}
