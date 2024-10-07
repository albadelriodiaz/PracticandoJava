package cursoSpringBoot.domain;

public class Product {

    private Integer id;
    private String name;
    private Double price;
    private Integer stock;

    //constructor vacío para que me lo pille Jackson para leer JSON
    public Product(){
        //Constructor vacío
    }

    public Product(Integer id, String name, Double price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
