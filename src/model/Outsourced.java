package model;

public class Outsourced extends Part {
    private String companyName;


    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Used to get company name
     * @return company name
     */
    public String getCompanyName() {return companyName;}

    /**
     * Used to set company name
     * @param companyName
     */
    public void setCompanyName(String companyName){this.companyName = companyName;}
}
