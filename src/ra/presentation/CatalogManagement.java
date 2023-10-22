package ra.presentation;

import ra.business.model.Catalog;
import ra.business.service.ICatalogService;
import ra.business.serviceInplement.CatalogService;
import ra.business.serviceInplement.ProductService;
import ra.business.util.InputMethods;
import ra.business.util.Validate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogManagement {

    private CatalogService catalogService;
    public CatalogManagement(CatalogService x){
        catalogService = x;

    }
    public void catalogManagement(){
        while (true){
            System.out.println("==============CATALOG MANAGEMENT=============");
            System.out.println("1. Hien thi danh sach danh muc");
            System.out.println("2. Tao moi danh muc");
            System.out.println("3. Tim danh muc theo ten");
            System.out.println("4. Chinh sua thong tin danh muc");
            System.out.println("5. An/Hien danh muc theo ma danh muc");
            System.out.println("6. Back to admin menu");
            System.out.println("Nhap lua chon cua ban : ");
            byte t = InputMethods.getByte();
            switch (t){
                case 1:
                    catalogService.displayAll();
                    break;
                case 2:
                    createNewCatalog();
                    break;
                case 3:
                    findByName();
                    break;
                case 4:
                    updateCatalog();
                    break;
                case 5:
                    editStatus();
                    break;
                case 6:
                    return;
                default:
                    System.err.println(Validate.OPTION_ERROR);
            }
        }
    }
    public void createNewCatalog(){
        Catalog newCatalog = new Catalog();
        newCatalog.setCatalogId(catalogService.getNewId());
        while (true){
            System.out.println("Nhap ten danh muc :");
            String name = InputMethods.getString();
            if(catalogService.checkExist(name)){
                System.err.println(Validate.EXIST_ERROR_CATALOGNAME);
            } else {
                newCatalog.setCatalogName(name);
                break;
            }
        }
        System.out.println("Nhap mo ta :");
        String moTa = InputMethods.getString();
        newCatalog.setDescription(moTa);
        catalogService.save(newCatalog);
        System.out.println("=====>Them moi thanh cong!");
    }
    public void findByName(){
        System.out.println("Nhap ten danh muc can tim kiem :");
        String name = InputMethods.getString();
        List<Catalog> list = catalogService.findByName(name);
        if(list.isEmpty()){
            System.err.println(Validate.SEARCH_ERROR);
        } else {
            list.forEach(System.out::println);
        }
    }
    public void updateCatalog(){
        while (true) {
            System.out.println("Nhap ma danh muc muon chinh sua :");
            Long id = InputMethods.getLong();
            Catalog update = catalogService.findById(id);
            if (update != null) {
                System.out.println(update);
                while (true) {
                    System.out.println("Nhap ten danh muc moi :");
                    String name = InputMethods.getString();
                    if (update.getCatalogName().equals(name)) {
                        break;
                    }
                    if (!catalogService.checkExist(name)) {
                        update.setCatalogName(name);
                        break;
                    } else {
                        System.err.println(Validate.EXIST_ERROR_CATALOGNAME);
                    }
                }
                System.out.println("Nhap mo ta :");
                String moTa = InputMethods.getString();
                update.setDescription(moTa);
                catalogService.save(update);
                System.out.println("===========> Chinh sua thanh cong!");
                break;
            } else {
                System.out.println(Validate.SEARCH_ERROR);
            }
        }
    }
    public void editStatus(){
        while (true) {
            System.out.println("Nhap ma danh muc :");
            Long id = InputMethods.getLong();
            Catalog result = catalogService.findById(id);
            if (result != null) {
                result.setStatus();
                catalogService.save(result);
                System.out.println("=========> Chinh sua thanh cong!");
                break;
            } else {
                System.err.println(Validate.SEARCH_ERROR);
            }
        }
    }
}
