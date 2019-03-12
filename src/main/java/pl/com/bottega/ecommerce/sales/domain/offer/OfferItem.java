/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItem {

    // product
    private Product product;

//    private String productId;
//
//    private BigDecimal productPrice;
//
//    private String productName;
//
//    private Date productSnapshotDate;
//
//    private String productType;

    private int quantity;

//    private BigDecimal totalCost;
//
//    private String currency;

    private Money money;
    // discount
    private Discount discount;
//    private String discountCause;
//
//    private BigDecimal discount;

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate,
            String productType, int quantity) {
        this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null, null);
    }

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate,
            String productType, int quantity, BigDecimal discount, String discountCause) {
        this.product = new Product(productId, productPrice, productName, productSnapshotDate, productType);
//        this.productId = productId;
//        this.productPrice = productPrice;
//        this.productName = productName;
//        this.productSnapshotDate = productSnapshotDate;
//        this.productType = productType;

        this.quantity = quantity;
        BigDecimal discountValue = new BigDecimal(0);
        this.discount = new Discount(discountCause, discountValue.subtract(discount));
        //        this.discount = discount;
        //        this.discountCause = discountCause;
//
//        if (discount != null) {
//            discountValue = discountValue.subtract(discount);
//        }

        this.money = new Money(productPrice);
//        this.totalCost = productPrice.multiply(new BigDecimal(quantity)).subtract(discountValue);
    }

    public String getProductId() {
        return product.getProductId();
    }

    public BigDecimal getProductPrice() {
        return product.getProductPrice();
    }

    public String getProductName() {
        return product.getProductName();
    }

    public Date getProductSnapshotDate() {
        return product.getProductSnapshotDate();
    }

    public String getProductType() {
        return product.getProductType();
    }

    public BigDecimal getTotalCost() {
        return this.money.value;
    }

    public String getTotalCostCurrency() {
        return this.money.currency;
    }

    public BigDecimal getDiscount() {
        return discount.getDiscount();
    }

    public String getDiscountCause() {
        return discount.getDiscountCause();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
//        result = prime * result + (discount == null ? 0 : discount.hashCode());
//        result = prime * result + (productName == null ? 0 : productName.hashCode());
//        result = prime * result + (productPrice == null ? 0 : productPrice.hashCode());
//        result = prime * result + (productId == null ? 0 : productId.hashCode());
//        result = prime * result + (productType == null ? 0 : productType.hashCode());
        result = prime * result + quantity;
//        result = prime * result + (totalCost == null ? 0 : totalCost.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        if (discount.getDiscount() == null) {
            if (other.discount.getDiscount() != null) {
                return false;
            }
        } else if (!discount.getDiscount().equals(other.discount.getDiscount())) {
            return false;
        }
//        if (productName == null) {
//            if (other.productName != null) {
//                return false;
//            }
//        } else if (!productName.equals(other.productName)) {
//            return false;
//        }
//        if (productPrice == null) {
//            if (other.productPrice != null) {
//                return false;
//            }
//        } else if (!productPrice.equals(other.productPrice)) {
//            return false;
//        }
//        if (productId == null) {
//            if (other.productId != null) {
//                return false;
//            }
//        } else if (!productId.equals(other.productId)) {
//            return false;
//        }
//        if (productType != other.productType) {
//            return false;
//        }
        if (quantity != other.quantity) {
            return false;
        }
//        if (totalCost == null) {
//            if (other.totalCost != null) {
//                return false;
//            }
//        } else if (!totalCost.equals(other.totalCost)) {
//            return false;
//        }
        return true;
    }

    /**
     *
     * @param // item
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (product.getProductName() == null) {
            if (product.getProductName() != null) {
                return false;
            }
        } else if (!product.getProductName().equals(other.product.getProductName())) {
            return false;
        }
        if (product.getProductPrice() == null) {
            if (other.product.getProductPrice() != null) {
                return false;
            }
        } else if (!product.getProductPrice().equals(other.product.getProductPrice())) {
            return false;
        }
        if (product.getProductId() == null) {
            if (other.product.getProductId() != null) {
                return false;
            }
        } else if (!product.getProductId().equals(other.product.getProductId())) {
            return false;
        }
        if (product.getProductType() != other.product.getProductType()) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (getTotalCost().compareTo(other.getTotalCost()) > 0) {
            max = getTotalCost();
            min = other.getTotalCost();
        } else {
            max = other.getTotalCost();
            min = getTotalCost();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
