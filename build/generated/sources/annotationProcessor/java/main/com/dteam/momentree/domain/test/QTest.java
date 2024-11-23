package com.dteam.momentree.domain.test;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTest is a Querydsl query type for Test
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTest extends EntityPathBase<Test> {

    private static final long serialVersionUID = 2135473114L;

    public static final QTest test = new QTest("test");

    public final com.dteam.momentree.domain.QUserBaseEntity _super = new com.dteam.momentree.domain.QUserBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> createUser = _super.createUser;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final NumberPath<Long> lastModifyingUser = _super.lastModifyingUser;

    public final StringPath value = createString("value");

    public QTest(String variable) {
        super(Test.class, forVariable(variable));
    }

    public QTest(Path<? extends Test> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTest(PathMetadata metadata) {
        super(Test.class, metadata);
    }

}

