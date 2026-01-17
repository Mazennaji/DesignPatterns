public class Main {
    public static void main(String[] args) {
        ProductCollection collection = new ProductCollection();

        collection.add(new Product("Laptop", 1200));
        collection.add(new Product("Phone", 800));
        collection.add(new Product("Headphones", 150));

        Iterator iterator = collection.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

