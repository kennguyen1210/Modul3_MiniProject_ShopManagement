package ra.business.serviceInplement;

import ra.business.config.IOFile;
import ra.business.model.Catalog;
import ra.business.model.Product;
import ra.business.service.IProductService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements IProductService {
    private List<Product> products;
    public ProductService(){
        products = IOFile.readFileByBinary(IOFile.PRODUCT_PATH);
    }
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream().filter(e->e.getProductId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(Product product) {
        Product productOld = findById(product.getProductId());
        if(productOld!= null){
            product.setUpdate_at(LocalDate.now());
            products.set(products.indexOf(productOld),product);
        } else {
            product.setCreate_at(LocalDate.now());
            products.add(product);
        }
        IOFile.writeToFileBinary(IOFile.PRODUCT_PATH,products);
    }

    @Override
    public void setStatusById(Long id) {
        findById(id).setStatus();
        IOFile.writeToFileBinary(IOFile.PRODUCT_PATH,products);
    }

    @Override
    public Long getNewId() {
        return products.stream().map(Product::getProductId).max(Long::compareTo).orElse(0L)+1;
    }

    @Override
    public boolean checkExist(String str) {
        for (Product pr: products) {
            if(pr.getProductName().equals(str)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> findByName(String name) {
        return products.stream().filter(e->e.getProductName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public void displayAll() {
        List<Product> list = products.stream().sorted(Comparator.comparing(Product::getCreate_at).reversed()).collect(Collectors.toList());
        if(list.isEmpty()){
            System.err.println("Danh sach hien dang trong!");
        } else {
            list.forEach(System.out::println);
        }
    }
}
