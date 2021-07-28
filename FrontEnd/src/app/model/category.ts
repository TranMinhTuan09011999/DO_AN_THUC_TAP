import { Product } from './product';
import { Room } from './room';

export class Category {
    categoryId!: string;
    categoryName!: string;
    room!: Room;
}
