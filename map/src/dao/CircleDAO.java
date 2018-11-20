package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for Circle entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see dao.Circle
  * @author MyEclipse Persistence Tools 
 */

public class CircleDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(CircleDAO.class);
		//property constants
	public static final String CENTERPOINT = "centerpoint";
	public static final String RADIUS = "radius";
	public static final String TYPE = "type";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Circle transientInstance) {
        log.debug("saving Circle instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Circle persistentInstance) {
        log.debug("deleting Circle instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Circle findById( java.lang.Integer id) {
        log.debug("getting Circle instance with id: " + id);
        try {
            Circle instance = (Circle) getHibernateTemplate()
                    .get("dao.Circle", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Circle instance) {
        log.debug("finding Circle instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Circle instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Circle as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCenterpoint(Object centerpoint
	) {
		return findByProperty(CENTERPOINT, centerpoint
		);
	}
	
	public List findByRadius(Object radius
	) {
		return findByProperty(RADIUS, radius
		);
	}
	
	public List findByType(Object type
	) {
		return findByProperty(TYPE, type
		);
	}
	

	public List findAll() {
		log.debug("finding all Circle instances");
		try {
			String queryString = "from Circle";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Circle merge(Circle detachedInstance) {
        log.debug("merging Circle instance");
        try {
            Circle result = (Circle) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Circle instance) {
        log.debug("attaching dirty Circle instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Circle instance) {
        log.debug("attaching clean Circle instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CircleDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CircleDAO) ctx.getBean("CircleDAO");
	}
}