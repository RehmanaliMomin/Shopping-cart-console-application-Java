import java.util.*;

public class GetMyParking {

    public static List<Product> products;
    public static int input;
    public static List<User> users;
    public static User currentUser;

    public static void main(String[] args) {

        products = new ArrayList<>();
        users = new ArrayList<>();
        currentUser = null;

        do {

            System.out.println("Hello, Press 0 for admin, 1 for user, -1 to close the application");
            Scanner inn = new Scanner(System.in);
            int a = Integer.parseInt(inn.next());

            //admin operations
            if (a == 0) {
                if (products.size() == 0) {
                    addProductsAdmin();
                } else {
                    printProducts();
                    System.out.println(
                            "Press 1 to add Products, 2 to modify existing products, 3 to delete products, 0 to exit");
                    Scanner in = new Scanner(System.in);
                    String inputAdmin = in.next();
                    if (inputAdmin.equals("1")) {
                        addProductsAdmin();
                    } else if (inputAdmin.equals("2")) {
                        modifyProductsAdmin();
                    } else if (inputAdmin.equals("3")) {
                        deleteProductsAdmin();
                    }
                }
            }

            //user operations
            else if (a == 1) {
                Scanner in = new Scanner(System.in);
                System.out.println("Enter 0 for Signup or and 1 for login.");
                String inputUser = in.next();

                if (inputUser.equals("0")) {
                    System.out.println(
                            "Please signup - Enter your Name, email, address, dob. Press enter after entering each value.");

                    User user = new User();
                    user.name = in.next();
                    user.emailId = in.next();
                    user.address = in.next();
                    user.dob = in.next();
                    user.cart = new Cart();
                    user.cart.products = new ArrayList<>();
                    users.add(user);
                }

                System.out.println("Please sign in. Enter your emailId");
                String emailInput = in.next();

                for (User u : users) {
                    if (u.emailId.equals(emailInput)) {
                        currentUser = u;
                    }
                }
                if (currentUser == null) {
                    System.out.println("Please enter correct email Id");
                    continue;
                }

                printProducts();

                if (currentUser.cart.products.size() > 0)
                    printCart();
                else
                    System.out.println("Your cart is empty");

                System.out.println("You can add products by entering Ids. Enter -1 when you are done adding");

                String pIdInput;
                while (!(pIdInput = in.next()).equals("-1")) {
                    int index = Integer.parseInt(pIdInput);
                    currentUser.cart.products.add(products.get(index));
                }

                printCart();
            }

        } while (input != -1);
    }

    public static void printCart() {
        System.out.println("Your cart is ");
        int cartValue = 0;

        for (int i = 0; i < currentUser.cart.products.size(); i++) {
            Product p = currentUser.cart.products.get(i);
            System.out.println("Name:" + p.name + ",  Description: " + p.description + ",  Price: " + p.price);
            cartValue += p.price;
        }
        System.out.println("Your cart value is " + cartValue);
    }


    public static void modifyProductsAdmin() {

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println(
                    "Id: " + i + ",  Name:" + p.name + ",  Description: " + p.description + ",  Price: " + p.price);
        }
        System.out.println("Please enter the id of the product that you want to modify  - ");

        Scanner in = new Scanner(System.in);
        int idInput = Integer.parseInt(in.next());

        Product p = products.get(idInput);
        System.out.println(
                "Id: " + idInput + ",  Name:" + p.name + ",  Description: " + p.description + ",  Price: " + p.price);

        System.out.println("Type the updated values in the form of enter separated - name, desc, price ");
        p.name = in.next();
        p.description = in.next();
        p.price = Double.parseDouble(in.next());

        products.set(idInput, p);

        printProducts();
    }


    public static void deleteProductsAdmin() {
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println(
                    "Id: " + i + ",  Name:" + p.name + ",  Description: " + p.description + ",  Price: " + p.price);
        }
        System.out.println("Please enter the id of the product that you want to delete.");
        Scanner in = new Scanner(System.in);
        int idInput = Integer.parseInt(in.next());
        products.remove(idInput);
        printProducts();
    }


    public static void addProductsAdmin() {
        System.out.println("Please enter the Products, in the form of name, descr, price. Press 0 when you are done.");
        Scanner in = new Scanner(System.in);
        String s;
        while (!(s = in.next()).equals("0")) {
            String[] prod = s.split(",");
            Product p = new Product();
            p.name = prod[0];
            p.description = prod[1];
            p.price = Double.parseDouble(prod[2]);
            products.add(p);
        }
    }


    public static void printProducts() {
        System.out.println("Following are the existing products  - ");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println(
                    "Id: " + i + ",  Name:" + p.name + ",  Description: " + p.description + ",  Price: " + p.price);
        }
    }
}
