package dataFactory;

import pojo.ComponentPojo;
import pojo.ProductPojo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductDataFactory {

    public static ProductPojo createProductWithValue(double value){
        ProductPojo newProduct = new ProductPojo();
        ArrayList<String> colors = new ArrayList<>();
        colors.add("preto");
        colors.add("rosa");

        ComponentPojo phoneCase = new ComponentPojo();
        phoneCase.setComponenteNome("capinha");
        phoneCase.setComponenteQuantidade(1);
        ArrayList<ComponentPojo> components = new ArrayList<>();
        components.add(phoneCase);


        newProduct.setProdutoNome("IPHONE 15 PRO MAX");
        newProduct.setProdutoValor(value);
        newProduct.setProdutoCores(colors);
        newProduct.setProdutoUrlMock("");
        newProduct.setComponentes(components);

        return newProduct;

    }
}
