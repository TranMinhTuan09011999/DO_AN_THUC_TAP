import { SizeService } from "../service/size.service";

export class WarehouseReceiptDetail {
    productId!: String;
    sizeId!: number;
    colorId!: number;
    size!: string;
    color!: string;
    quantity!: number;
    price!: number;
}
