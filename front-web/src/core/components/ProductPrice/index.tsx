import React from 'react';
import './style.scss'

type Props = {
    price: string;
}

const ProductPrice = ({price} :Props) => (
    
    <div className="product-price-container">
        <span className="product-current">R$</span>
        <h3 className="product-price"> {price} </h3>
    </div>

);

export default ProductPrice;