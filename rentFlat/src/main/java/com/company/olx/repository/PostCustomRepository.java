package com.company.olx.repository;

import com.company.olx.dto.post.PostFilterDTO;
import com.company.olx.entity.PostEntity;
import com.company.olx.enums.PostStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public PageImpl filter(int page, int size, PostFilterDTO filterDTO) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder builder = new StringBuilder("SELECT a FROM PostEntity a ");
        StringBuilder builderCount = new StringBuilder("SELECT count(a) FROM PostEntity a ");

        if (filterDTO.getStatus() != null) {
            builder.append("Where status ='" + filterDTO.getStatus().name() + "'");
            builderCount.append("Where status ='" + filterDTO.getStatus().name() + "'");
        } else {
            builder.append("Where status ='PUBLISHED'");
            builderCount.append("Where status ='PUBLISHED'");
        }

        if (filterDTO.getRooms() != null) {
            builder.append(" and a.rooms =:rooms");
            builderCount.append(" and a.rooms =:rooms");
            params.put("rooms", filterDTO.getRooms());
        }
        if (filterDTO.getFloor() != null) {
            builder.append(" and a.floor =:floor");
            builderCount.append(" and a.floor =:floor");
            params.put("floor", filterDTO.getFloor());
        }

        if (filterDTO.getPrice() != null) {
            builder.append(" and a.price =:price");
            builderCount.append(" and a.price =:price");
            params.put("price", filterDTO.getPrice());
        }
        if(filterDTO.getArea() != null){
            builder.append(" and a.area=:area");
            builderCount.append(" and a.area=:area");
            params.put("area",filterDTO.getArea());
        }
        if(filterDTO.getProfile_id() != null){
            builder.append(" and a.profile.id=:id");
            builderCount.append(" and a.profile.id=:id");
            params.put("id",filterDTO.getProfile_id());
        }
        if(filterDTO.getAddress_id() != null){
            builder.append(" and a.address.id=:id");
            builderCount.append(" and a.address.id=:id");
            params.put("id",filterDTO.getAddress_id());
        }

        if (filterDTO.getFromDate() != null) {
            builder.append(" and a.createdDate >=:fromDate");
            builderCount.append(" and a.createdDate >=:fromDate");
            params.put("fromDate", LocalDateTime.of(filterDTO.getFromDate(), LocalTime.MIN));
        }

        if (filterDTO.getToDate() != null) {
            builder.append(" and a.createdDate <=:toDate");
            builderCount.append(" and a.createdDate <=:toDate");
            params.put("toDate", LocalDateTime.of(filterDTO.getToDate(), LocalTime.MAX));
        }



        Query query = entityManager.createQuery(builder.toString());
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);

        for (Map.Entry<String, Object> entrySet : params.entrySet()) {
            query.setParameter(entrySet.getKey(), entrySet.getValue());
        }
        List<PostEntity> postEntities = query.getResultList();


        query = entityManager.createQuery(builderCount.toString());
        for (Map.Entry<String, Object> entrySet : params.entrySet()) {
            query.setParameter(entrySet.getKey(), entrySet.getValue());
        }
        Long totalCount = (Long) query.getSingleResult();

        return new PageImpl(postEntities, PageRequest.of(page, size), totalCount);
    }

    public PageImpl<PostEntity> filterCriteriaBuilder(int page,int size,PostFilterDTO filterDTO){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PostEntity> criteriaQuery =
                criteriaBuilder.createQuery(PostEntity.class);
        Root<PostEntity> root = criteriaQuery.from(PostEntity.class);

        criteriaQuery.select(root);
        ArrayList<Predicate> predicateList = new ArrayList<>();

        if(filterDTO.getStatus() != null){
            predicateList.add(criteriaBuilder.equal(root.get("status"),filterDTO.getStatus()));
        }else {
            predicateList.add(criteriaBuilder.equal(root.get("status"), PostStatus.PUBLISHED));
        }

        if(filterDTO.getProfile_id() != null){
            predicateList.add(criteriaBuilder.equal(root.get("profile.id"),filterDTO.getProfile_id()));
        }

        if(filterDTO.getAddress_id() != null){
            predicateList.add(criteriaBuilder.equal(root.get("address.id"),filterDTO.getAddress_id()));
        }

        if(filterDTO.getRooms() != null){
            predicateList.add(criteriaBuilder.equal(root.get("rooms"),filterDTO.getRooms()));
        }
        if(filterDTO.getFloor() != null){
            predicateList.add(criteriaBuilder.equal(root.get("floor"),filterDTO.getFloor()));
        }
        if(filterDTO.getPrice() != null){
            predicateList.add(criteriaBuilder.equal(root.get("price"),filterDTO.getPrice()));
        }
        if(filterDTO.getArea() != null){
            predicateList.add(criteriaBuilder.equal(root.get("area"),filterDTO.getArea()));
        }

        if(filterDTO.getFromDate() != null){
            predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"),
                    filterDTO.getFromDate()));
        }

        if(filterDTO.getToDate() != null){
            predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("cretedDate"),
                    filterDTO.getToDate()));
        }

        Predicate []predicateArray = new Predicate[predicateList.size()];
        predicateList.toArray();

        criteriaQuery.where(predicateArray);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));

        List<PostEntity> postEntities = entityManager.createQuery(criteriaQuery).getResultList();

        return new PageImpl(postEntities, PageRequest.of(page, size), postEntities.size());
    }
}
