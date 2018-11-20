package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Polygon entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Polygon
 * @author MyEclipse Persistence Tools
 */

public class PolygonDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(PolygonDAO.class);
	// property constants
	public static final String POINTSCOUNT = "pointscount";
	public static final String ARRPOINTS = "arrpoints";
	public static final String TYPE = "type";

	protected void initDao() {
		// do nothing
	}

	public void save(Polygon transientInstance) {
		log.debug("saving Polygon instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Polygon persistentInstance) {
		log.debug("deleting Polygon instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Polygon findById(java.lang.Integer id) {
		log.debug("getting Polygon instance with id: " + id);
		try {
			Polygon instance = (Polygon) getHibernateTemplate().get(
					"dao.Polygon", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Polygon instance) {
		log.debug("finding Polygon instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Polygon instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Polygon as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPointscount(Object pointscount) {
		return findByProperty(POINTSCOUNT, pointscount);
	}

	public List findByArrpoints(Object arrpoints) {
		return findByProperty(ARRPOINTS, arrpoints);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findAll() {
		log.debug("finding all Polygon instances");
		try {
			String queryString = "from Polygon";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Polygon merge(Polygon detachedInstance) {
		log.debug("merging Polygon instance");
		try {
			Polygon result = (Polygon) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Polygon instance) {
		log.debug("attaching dirty Polygon instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Polygon instance) {
		log.debug("attaching clean Polygon instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PolygonDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PolygonDAO) ctx.getBean("PolygonDAO");
	}
}