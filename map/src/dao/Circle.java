package dao;



/**
 * Circle entity. @author MyEclipse Persistence Tools
 */

public class Circle  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String centerpoint;
     private String radius;
     private String type;


    // Constructors

    /** default constructor */
    public Circle() {
    }

    
    /** full constructor */
    public Circle(String centerpoint, String radius, String type) {
        this.centerpoint = centerpoint;
        this.radius = radius;
        this.type = type;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCenterpoint() {
        return this.centerpoint;
    }
    
    public void setCenterpoint(String centerpoint) {
        this.centerpoint = centerpoint;
    }

    public String getRadius() {
        return this.radius;
    }
    
    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
   








}