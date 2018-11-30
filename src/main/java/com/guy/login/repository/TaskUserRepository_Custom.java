package com.guy.login.repository;

import com.guy.login.domain.TaskUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskUserRepository_Custom {

    @PersistenceContext
    EntityManager entityManager;

    private Logger LOGGER = LoggerFactory.getLogger(TaskUserRepository_Custom.class);

    public List<TaskUser> findTaskByTitle(String title){
        List<TaskUser> taskList = new ArrayList<>();
        try {
//            Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Task.class);

            // Create CriteriaBuilder
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            // Create CriteriaQuery
            CriteriaQuery<TaskUser> criteriaQuery = builder.createQuery(TaskUser.class);
            Root<TaskUser> taskRoot = criteriaQuery.from(TaskUser.class);
            List<Predicate> predicateList = new ArrayList<>();

            if(!StringUtils.isEmpty(title)){
                predicateList.add(builder.like(builder.lower(taskRoot.get("title")),"%" + title + "%"));
//                criteriaQuery.where(builder.like(builder.lower(taskRoot.get("title")),"%" + title + "%"));
//                criteria.add(Restrictions.like("title",title, MatchMode.ANYWHERE));
            }

//            criteriaQuery.select(taskRoot);
            criteriaQuery.select(taskRoot).where(predicateList.toArray(new Predicate[]{}));

//            taskList = criteria.list();
            taskList = entityManager.createQuery(criteriaQuery).getResultList();
        }catch (Exception e){
            LOGGER.error("{}",e.getMessage());
        }

        return taskList;
    }
}
