package ra.business.serviceInplement;

import ra.business.config.IOFile;
import ra.business.model.Catalog;
import ra.business.model.Product;
import ra.business.service.ICatalogService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogService implements ICatalogService {
    @Override
    public boolean checkExist(String str) {
        for (Catalog c: catalogs) {
            if(c.getCatalogName().equals(str)){
                return true;
            }
        }
        return false;
    }

    private List<Catalog> catalogs;
    public  CatalogService(){
        catalogs = IOFile.readFileByBinary(IOFile.CATALOG_PATH);
    }
    @Override
    public void setStatusById(Long id) {
        findById(id).setStatus();
        IOFile.writeToFileBinary(IOFile.CATALOG_PATH,catalogs);
    }

    @Override
    public List<Catalog> findAll() {
        return catalogs;
    }

    @Override
    public Catalog findById(Long id) {
        return catalogs.stream().filter(e->e.getCatalogId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(Catalog catalog) {
        Catalog catalogOld = findById(catalog.getCatalogId());
        if(catalogOld!= null){
            catalog.setUpdate_at(LocalDate.now());
            catalogs.set(catalogs.indexOf(catalogOld),catalog);
        } else {
            catalog.setCreate_at(LocalDate.now());
            catalogs.add(catalog);
        }
        IOFile.writeToFileBinary(IOFile.CATALOG_PATH,catalogs);
    }

    @Override
    public void displayAll() {
        List<Catalog> list = catalogs.stream().sorted(Comparator.comparing(Catalog::getCreate_at).reversed()).collect(Collectors.toList());
        if(list.isEmpty()){
            System.err.println("Danh sach hien dang trong!");
        } else {
            list.forEach(System.out::println);
        }
    }

    @Override
    public List<Catalog> findByName(String name) {
        return catalogs.stream().filter(e->e.getCatalogName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public Long getNewId() {
        return catalogs.stream().map(Catalog::getCatalogId).max(Long::compareTo).orElse(0L)+1;
    }
}
