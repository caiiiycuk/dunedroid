package com.epicport.glue.billing;

public class UnitSku {

	public final int name;
	public final int unitId;
	public final int count;
	public final String sku;

	public UnitSku(int name, int unitId, int count, String sku) {
		this.name = name;
		this.unitId = unitId;
		this.count = count;
		this.sku = sku;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("name ").append(name).append(" ")
			.append("id ").append(unitId).append(" ")
			.append("count ").append(count).append(" ")
			.append("sku '").append(sku).append("'")
			.toString();
	}
	
}
