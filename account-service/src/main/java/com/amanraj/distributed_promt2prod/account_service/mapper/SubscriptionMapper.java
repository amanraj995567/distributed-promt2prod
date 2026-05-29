package com.amanraj.distributed_promt2prod.account_service.mapper;


import com.amanraj.distributed_promt2prod.account_service.dto.subscription.SubscriptionResponse;
import com.amanraj.distributed_promt2prod.account_service.entity.Plan;
import com.amanraj.distributed_promt2prod.account_service.entity.Subscription;
import com.amanraj.distributed_promt2prod.common_lib.dto.PlanDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionResponse toSubscriptionResponse(Subscription subscription);

    PlanDto toPlanResponse(Plan plan);
}
