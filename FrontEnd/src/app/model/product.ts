import { Provider } from "@angular/core";
import { Category } from "./category";

export class Product {
    productId!: string;
    productName!: string;
    status!: number;
    description!: string;
    providerDTO!: Provider;
    categoryDTO!: Category;
}
