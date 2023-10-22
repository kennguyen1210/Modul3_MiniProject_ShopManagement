package ra.presentation;

import ra.business.model.Catalog;
import ra.business.model.Product;
import ra.business.service.ICatalogService;
import ra.business.service.IProductService;
import ra.business.serviceInplement.CatalogService;
import ra.business.serviceInplement.ProductService;
import ra.business.util.InputMethods;
import ra.business.util.Validate;

import java.util.List;

public class ProductManagement {
    private ProductService productService;
    private CatalogService catalogService;
    public ProductManagement(CatalogService c, ProductService p){
        productService = p;
        catalogService = c;
    }
    public void productManagement(){
        while (true){
            System.out.println("==============PRODUCT MANAGEMENT=============");
            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Them moi mot hoac nhieu san pham");
            System.out.println("3. Chinh sua thong tin danh muc");
            System.out.println("4. An/Hien danh muc theo ma danh muc");
            System.out.println("5. Tim danh muc theo ten");
            System.out.println("6. Back to admin menu");
            System.out.println("Nhap lua chon cua ban : ");
            byte t = InputMethods.getByte();
            switch (t){
                case 1:
                    productService.displayAll();
                    break;
                case 2:
                    createNewProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    editStatus();
                    break;
                case 5:
                    findByName();
                    break;
                case 6:
                    return;
                default:
                    System.err.println(Validate.OPTION_ERROR);
            }
        }
    }

    public void createNewProduct(){
        List<Catalog> catalogsList = catalogService.findAll();
        if(catalogsList.isEmpty()){
            System.err.println("Chua co danh muc nao! Hay them danh muc truoc!");
            return;
        }
        System.out.println("Nhap so san pham muon them moi :");
        int t = InputMethods.getInteger();
        for (int i = 0; i < t; i++) {
            System.out.println("Lan nhap thu " + (i+1));
            Product newProduct = new Product();
            newProduct.setProductId(productService.getNewId());
            while (true){
                System.out.println("Nhap ten san pham :");
                String name = InputMethods.getString();
                if(!Validate.checkProductNameMin(name)){
                    System.err.println(Validate.PRODUCT_NAME_MIN_ERROR);
                    continue;
                }
                if(productService.checkExist(name)){
                    System.err.println(Validate.EXIST_ERROR_PRODUCTNAME);
                    continue;
                }
                newProduct.setProductName(name);
                break;
            }
            catalogService.findAll().stream().map(Catalog::getCatalogId).forEach(e->System.out.println("catalogId :" + e));
            while (true) {
                System.out.println("Nhap phan loai san pham (ma danh muc) :");
                Long categoryId = InputMethods.getLong();
                if(catalogService.findById(categoryId)!= null){
                    newProduct.setCategoryId(categoryId);
                    break;
                }
                System.err.println("Ma danh muc khong ton tai");
            }
            System.out.println("Nhap mo ta san pham :");
            String moTa = InputMethods.getString();
            newProduct.setDescription(moTa);
            while (true) {
                System.out.println("Nhap don gia :");
                double unitPrice = InputMethods.getDouble();
                if(Validate.checkUnitPrice(unitPrice)){
                    newProduct.setUnitPrice(unitPrice);
                    break;
                }
                System.err.println(Validate.UNIT_PRICE_ERROR);
            }
            while (true){
                System.out.println("Nhap so luong trong kho :");
                int stock = InputMethods.getInteger();
                if(Validate.checkStock(stock)){
                    newProduct.setStock(stock);
                    break;
                }
                System.err.println(Validate.NUMBER_POSITIVE);
            }
            productService.save(newProduct);
        }
        System.out.println("======> Them moi thanh cong!");
    }
    public void findByName(){
        System.out.println("Nhap ten san pham can tim kiem :");
        String name = InputMethods.getString();
        List<Product> list = productService.findByName(name);
        if(list.isEmpty()){
            System.err.println(Validate.SEARCH_ERROR);
        } else {
            list.forEach(System.out::println);
        }
    }
    public void updateProduct(){
        while (true) {
            System.out.println("Nhap ma san pham muon chinh sua");
            Long id = InputMethods.getLong();
            Product update = productService.findById(id);
            if (update != null) {
                System.out.println(update);
                while (true) {
                    System.out.println("Nhap ten moi cua san pham :");
                    String name = InputMethods.getString();
                    if (name.equals(update.getProductName())) {
                        break;
                    }
                    if(!productService.checkExist(name)){
                        update.setProductName(name);
                        break;
                    }
                    System.err.println(Validate.EXIST_ERROR_PRODUCTNAME);
                }
                System.out.println("Nhap phan loai san pham");
                Long categoryId = InputMethods.getLong();
                update.setCategoryId(categoryId);
                System.out.println("Nhap mo ta san pham :");
                String moTa = InputMethods.getString();
                update.setDescription(moTa);
                while (true) {
                    System.out.println("Nhap don gia :");
                    double unitPrice = InputMethods.getDouble();
                    if(Validate.checkUnitPrice(unitPrice)){
                        update.setUnitPrice(unitPrice);
                        break;
                    }
                    System.err.println(Validate.UNIT_PRICE_ERROR);
                }
                while (true){
                    System.out.println("Nhap so luong trong kho :");
                    int stock = InputMethods.getInteger();
                    if(Validate.checkStock(stock)){
                        update.setStock(stock);
                        break;
                    }
                    System.err.println(Validate.NUMBER_POSITIVE);
                }
                productService.save(update);
                System.out.println("==========>Chinh sua thanh cong!");
                break;
            } else {
                System.err.println("Ma san pham khong ton tai!");
            }
        }
    }
    public void editStatus(){
        while (true) {
            System.out.println("Nhap ma san pham :");
            Long id = InputMethods.getLong();
            Product result = productService.findById(id);
            if (result != null) {
                result.setStatus();
                productService.save(result);
                System.out.println("=========> Chinh sua thanh cong!");
                break;
            } else {
                System.err.println(Validate.SEARCH_ERROR);
            }
        }
    }
}
