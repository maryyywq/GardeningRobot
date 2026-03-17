public class FuelCellFabric implements PowerSourceFabric {
    @Override
    public IPowerSource create() {
        return new FuelCell();
    }
}
