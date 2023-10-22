package ra.business.util;

public class Validate {
    public static final String NUMBER_POSITIVE = "Hay nhap 1 so nguyen duong( >= 0)";
    public static final String UNIT_PRICE_ERROR = "Hay nhap 1 so duong (> 0.0)";
    public static final String PRODUCT_NAME_MIN_ERROR = "Hay nhap it nhat 6 ky tu";
    public static final String EMPTY_ERROR = "Khong duoc de trong truong nay";
    public static final String SEARCH_ERROR = "Khong tim thay ket qua phu hop!";
    public static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String REGEX_EMAIL_ERROR = "Email chua dung dinh dang";
    public static final String ADMIN_EMAIL = "admin@gmail.com";
    public static final String ADMIN_PASSWORD = "123456";
    public static final String ADMIN_PASSWORD_ERROR = "Mat khau chua chinh xac";
    public static final String ADMIN_EMAIL_ERROR = "Email chua chinh xac";
    public static final String OPTION_ERROR = "Lua chon chua chinh xac! Hay lua chon lai";
    public static final String EXIST_ERROR_PRODUCTNAME = "Ten san pham da ton tai! vui long nhap ten san pham khac";
    public static final String EXIST_ERROR_CATALOGNAME = "Ten danh muc da ton tai! vui long nhap ten danh muc khac";
    public static boolean checkProductNameMin(String productName){
        return productName.trim().length() >= 6;
    }
    public static boolean checkEmpty(String str){
        return str.trim().isEmpty();
    }
    public static boolean checkEmail(String email){
        if(!email.matches(REGEX_EMAIL)){
            System.err.println(REGEX_EMAIL_ERROR);
            return false;
        }
        if(!email.equals(ADMIN_EMAIL)){
            System.err.println(ADMIN_EMAIL_ERROR);
            return false;
        }
        return true;
    }
    public static boolean checkPassword(String pw){
        if(!pw.equals(ADMIN_PASSWORD)){
            return false;
        }
        return true;
    }
    public static boolean checkUnitPrice(double price){
        if(price > 0){
            return true;
        }
        return false;
    }
    public static boolean checkStock(int stock){
        if(stock >= 0){
            return true;
        }
        return false;
    }
}
