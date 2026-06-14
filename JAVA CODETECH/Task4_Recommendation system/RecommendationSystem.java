import java.util.*;

// Main Class
public class RecommendationSystem {

    // =====================================================
    // PRODUCT DATABASE
    // =====================================================

    // Store products and categories
    static Map<String, List<String>> productDatabase = new HashMap<>();

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {

        // Add sample products
        loadSampleData();

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println(" AI-BASED RECOMMENDATION SYSTEM ");
        System.out.println("======================================");

        // Display available categories
        System.out.println("\nAvailable Categories:");
        displayCategories();

        // Ask user preference
        System.out.print("\nEnter your preferred category: ");
        String userChoice = sc.nextLine();

        // Generate recommendations
        recommendProducts(userChoice);

        // Close scanner
        sc.close();
    }

    // =====================================================
    // LOAD SAMPLE PRODUCT DATA
    // =====================================================
    public static void loadSampleData() {

        // Electronics category
        productDatabase.put("Electronics",
                Arrays.asList(
                        "Laptop",
                        "Smartphone",
                        "Bluetooth Speaker",
                        "Smart Watch",
                        "Headphones"
                ));

        // Books category
        productDatabase.put("Books",
                Arrays.asList(
                        "Java Programming",
                        "Data Structures",
                        "AI Basics",
                        "Machine Learning Guide",
                        "Operating Systems"
                ));

        // Fashion category
        productDatabase.put("Fashion",
                Arrays.asList(
                        "T-Shirt",
                        "Jeans",
                        "Sneakers",
                        "Jacket",
                        "Sports Shoes"
                ));

        // Food category
        productDatabase.put("Food",
                Arrays.asList(
                        "Pizza",
                        "Burger",
                        "Pasta",
                        "Sandwich",
                        "Ice Cream"
                ));
    }

    // =====================================================
    // DISPLAY AVAILABLE CATEGORIES
    // =====================================================
    public static void displayCategories() {

        // Loop through categories
        for (String category : productDatabase.keySet()) {

            System.out.println("- " + category);
        }
    }

    // =====================================================
    // RECOMMEND PRODUCTS
    // =====================================================
    public static void recommendProducts(String category) {

        System.out.println("\n======================================");

        // Check if category exists
        if (productDatabase.containsKey(category)) {

            System.out.println("Recommended Products:");

            // Get products from selected category
            List<String> recommendations =
                    productDatabase.get(category);

            // Display recommendations
            for (String product : recommendations) {

                System.out.println("-> " + product);
            }

        } else {

            // Invalid category message
            System.out.println("Category not found!");
            System.out.println("Please choose a valid category.");
        }

        System.out.println("======================================");
    }
}