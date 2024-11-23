package com.dteam.momentree.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserBaseEntity is a Querydsl query type for UserBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QUserBaseEntity extends EntityPathBase<UserBaseEntity> {

    private static final long serialVersionUID = -1799129965L;

    public static final QUserBaseEntity userBaseEntity = new QUserBaseEntity("userBaseEntity");

    public final QTimeBaseEntity _super = new QTimeBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> createUser = createNumber("createUser", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> lastModifyingUser = createNumber("lastModifyingUser", Long.class);

    public QUserBaseEntity(String variable) {
        super(UserBaseEntity.class, forVariable(variable));
    }

    public QUserBaseEntity(Path<? extends UserBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserBaseEntity(PathMetadata metadata) {
        super(UserBaseEntity.class, metadata);
    }

}

