package com.intellistart.marketplace.util;

import com.intellistart.marketplace.dao.ProductDAO;
import com.intellistart.marketplace.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    private final ProductDAO productDAO;

    public ProductValidator(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;}
}
