package action;

import java.util.ArrayList;
import java.util.List;

import dao.Circle;
import dao.CircleDAO;

public class CircleAction {

	private Circle circle;
	private CircleDAO circleDAO;

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public CircleDAO getCircleDAO() {
		return circleDAO;
	}

	public void setCircleDAO(CircleDAO circleDAO) {
		this.circleDAO = circleDAO;
	}

	/**
	 * ����
	 * @param centerAndRu Բ�ĺͰ뾶
	 * @return
	 */
	public boolean addCircle(String centerAndRu) {
		boolean res = false;
		try {
           String[] str=centerAndRu.split(":");
           circle.setCenterpoint(str[0]);
           circle.setRadius(str[1]);
           circle.setType(str[2]);
           circleDAO.save(circle);
           res = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * ��ʾ����Ϊtype�ĵ�ͼ��ǵ�ԲԲ��
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<List> showCircle(String type) {
		List<Circle> list=null;
		List<List> items=new ArrayList<List>();
		try {
			list=circleDAO.findByType(type);
			if(list!=null){
			  for(Circle c:list){
				 List listItem=new ArrayList();//�洢ÿ��Բ����Ϣ
                 String[] s=c.getCenterpoint().split(",");//��ȡ԰�ľ�γ��
                 listItem.add(s);
                 listItem.add(c.getRadius());
                 listItem.add(c.getType());
                 items.add(listItem);
			  }
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return items;
	}

	/**
	 * ���
	 * @return
	 */
	public boolean clearHis() {
		boolean res = false;
		try {
		  List<Circle> list= circleDAO.findAll();
          for (int i = 0; i <list.size(); i++) {
        	  circleDAO.delete(list.get(i));
		  }
          res=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
}
