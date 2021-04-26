// criar modelo do objeto em type para receber dados do back-end
 
export type ProductsResponse={  //retornar lista de produtos e total de paginas
    content: Product[];
    totalPages: number;
}

export type Product = {
    id: number;
    name: string;
    description: string;
    price: number;
    imgUrl: string;
    date: string;
    categories: Category[];
}

export type Category = {
    id: number;
    name: string;
}