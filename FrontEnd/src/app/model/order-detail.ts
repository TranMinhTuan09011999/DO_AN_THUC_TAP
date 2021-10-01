import { Order } from "./order";
import { ProductDetail } from "./product-detail";

export class OrderDetail {
    orderDetailId!: number;
    price!: number;
    quantity!: number;
    productDetails!: ProductDetail;
    orders!: Order;
    discount!: number;
}
