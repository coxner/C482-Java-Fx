package model;

public class InHouse extends Part{
    private int machineId;


    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Used to get machine Id
     * @return machine id
     */
    public int getMachineId(){return machineId;}

    /**
     * Used to set machine id
     * @param machineId
     */
    public void setMachineId(int machineId){this.machineId = machineId;}
}
