package dao;

/**
 * Polygon entity. @author MyEclipse Persistence Tools
 */

public class Polygon implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pointscount;
	private String arrpoints;
	private String type;

	// Constructors

	/** default constructor */
	public Polygon() {
	}

	/** full constructor */
	public Polygon(Integer pointscount, String arrpoints, String type) {
		this.pointscount = pointscount;
		this.arrpoints = arrpoints;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPointscount() {
		return this.pointscount;
	}

	public void setPointscount(Integer pointscount) {
		this.pointscount = pointscount;
	}

	public String getArrpoints() {
		return this.arrpoints;
	}

	public void setArrpoints(String arrpoints) {
		this.arrpoints = arrpoints;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}