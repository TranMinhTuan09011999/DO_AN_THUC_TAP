import { Category } from "./category";
import { Provider } from "./provider";

export class Product {
    productId!: string;
    productName!: string;
    status!: number;
    description!: string;
    providerDTO!: Provider;
    categoryDTO!: Category;
}
