package com.guy.login.repository;

import com.guy.login.domain.Task;
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
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository_Custom {

    @PersistenceContext
    EntityManager entityManager;

    private Logger LOGGER = LoggerFactory.getLogger(TaskRepository_Custom.class);

    public List<Task> findTaskByTitle(String title){
        List<Task> taskList = new ArrayList<>();
        try {
//            Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Task.class);

            // Create CriteriaBuilder
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            // Create CriteriaQuery
            CriteriaQuery<Task> criteriaQuery = builder.createQuery(Task.class);
            Root<Task> taskRoot = criteriaQuery.from(Task.class);
            criteriaQuery.select(taskRoot);

            if(!StringUtils.isEmpty(title)){
                criteriaQuery.where(builder.like(builder.lower(taskRoot.get("title")),"%" + title + "%"));
//                criteria.add(Restrictions.like("title",title, MatchMode.ANYWHERE));
            }

//            taskList = criteria.list();
            taskList = entityManager.createQuery(criteriaQuery).getResultList();
        }catch (Exception e){
            LOGGER.error("{}",e.getMessage());
        }

        return taskList;
    }
}
