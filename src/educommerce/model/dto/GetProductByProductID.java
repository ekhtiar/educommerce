package educommerce.model.dto;

public class GetProductByProductID {
	
	private int stock;
	private int AvailabilityInHours;
	
	public GetProductByProductID(int stock, int AvailabilityInHours){
		this.stock = stock;
		this.AvailabilityInHours = AvailabilityInHours;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getAvailabilityInHours() {
		return AvailabilityInHours;
	}
	public void setAvailabilityInHours(int availabilityInHours) {
		AvailabilityInHours = availabilityInHours;
	}

}
