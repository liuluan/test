package action;

import java.util.ArrayList;
import java.util.List;

import dao.Polygon;
import dao.PolygonDAO;

public class PolygonAction {

	private Polygon polygon;
	private PolygonDAO polygonDAO;
	public Polygon getPolygon() {
		return polygon;
	}
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
	public PolygonDAO getPolygonDAO() {
		return polygonDAO;
	}
	public void setPolygonDAO(PolygonDAO polygonDAO) {
		this.polygonDAO = polygonDAO;
	}
	/**
	 * ����ҵĶ���α����Ϣ
	 * @param can ���������������ߣ�����γ�ȣ�������
	 * @return
	 */
	public String addPol(String can){
		String res="error";
		
		try {
			String [] str=can.split(":");
			polygon.setPointscount(Integer.parseInt(str[0]));
			polygon.setArrpoints(str[1]);
			polygon.setType(str[2]);
			polygonDAO.save(polygon);
			res="pol";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * ���ݶ����������ʾ
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<List<String []>> showPol(String type){
		System.out.println(type);
		List<Polygon> list=null;//�������Ͳ�����Ķ����list
		List<List<String []>> listItems=new ArrayList<List<String []>>();
		try {
			list=polygonDAO.findByType(type);
			System.out.println(list.size());
			if(list!=null){
                for(Polygon pol:list){
                	String[] strArr=pol.getArrpoints().split(",");
                	List<String[]> listItem=new ArrayList<String[]>();
                	for(int i=0;i<strArr.length;i=i+2){
                		String[] item={strArr[i],strArr[i+1]};
                		listItem.add(item);
                	}
                	listItems.add(listItem);
                }
                
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listItems;
	}
	/**
	 * �����ʷ�Ķ���α����Ϣ
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean clearHis(){
		boolean res=false;
		try {
		    List<Polygon> list=polygonDAO.findAll();
		    for (int i = 0; i < list.size(); i++) {
		    	polygonDAO.delete(list.get(i));
			}
			res=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
}
