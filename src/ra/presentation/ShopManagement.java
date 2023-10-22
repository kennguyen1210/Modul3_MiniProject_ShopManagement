package ra.presentation;

import ra.business.service.ICatalogService;
import ra.business.service.IProductService;
import ra.business.serviceInplement.CatalogService;
import ra.business.serviceInplement.ProductService;
import ra.business.util.InputMethods;
import ra.business.util.Validate;

public class ShopManagement {
    public static Login login = new Login();
    public static IProductService productService = new ProductService();
    public static ICatalogService catalogService = new CatalogService();
    public static CatalogManagement catalogManagement = new CatalogManagement((CatalogService) catalogService);
    public static ProductManagement productManagement = new ProductManagement((CatalogService) catalogService, (ProductService) productService);
    public static void main(String[] args) {
        while (true){
            System.out.println("==============SHOP=============");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("Nhap lua chon cua ban : ");
            byte t = InputMethods.getByte();
            switch (t){
                case 1:
                    login.login();
                    adminManagement();
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println(Validate.OPTION_ERROR);
            }
        }
    }
    public static void adminManagement(){
        while (true){
            System.out.println("==============ADMIN MANAGEMENT=============");
            System.out.println("1. Catalog Management");
            System.out.println("2. Product Management");
            System.out.println("3. Logout");
            System.out.println("Nhap lua chon cua ban : ");
            byte t = InputMethods.getByte();
            switch (t){
                case 1:
                    catalogManagement.catalogManagement();
                    break;
                case 2:
                    productManagement.productManagement();
                    break;
                case 3:
                    return;
                default:
                    System.err.println(Validate.OPTION_ERROR);
            }
        }
    }
}
