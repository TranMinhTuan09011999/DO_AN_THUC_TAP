import { ColorService } from "../service/color.service";
import { Color } from "./color";
import { Product } from "./product";
import { Size } from "./size";

export class ProductDetail {
    productDetailId!: number;
    quantity!: number;
    price!: number;
    image!:string;
    product!: Product;
    color!: Color;
    size!: Size;
}
