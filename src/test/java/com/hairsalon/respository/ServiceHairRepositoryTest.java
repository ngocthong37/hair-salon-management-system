package com.hairsalon.respository;

import com.hairsalon.entity.ServiceHair;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {ServiceHairRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.hairsalon.entity"})
@DataJpaTest
class ServiceHairRepositoryTest {
    @Mock
    private ServiceHairRepository serviceHairRepository;

    /**
     * Method under test: {@link ServiceHairRepository#findByPartialServiceName(String)}
     */
    @Test
    void testFindByPartialServiceName() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.dao.InvalidDataAccessResourceUsageException: could not prepare statement; SQL [insert into service_hair (description, price, service_name, url) values (?, ?, ?, ?)]
        //       at jdk.proxy4/jdk.proxy4.$Proxy146.save(Unknown Source)
        //   org.hibernate.exception.SQLGrammarException: could not prepare statement
        //       at org.hibernate.exception.internal.SQLExceptionTypeDelegate.convert(SQLExceptionTypeDelegate.java:64)
        //       at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:56)
        //       at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:109)
        //       at org.hibernate.engine.jdbc.internal.StatementPreparerImpl$StatementPreparationTemplate.prepareStatement(StatementPreparerImpl.java:186)
        //       at org.hibernate.engine.jdbc.internal.StatementPreparerImpl.prepareStatement(StatementPreparerImpl.java:111)
        //       at org.hibernate.dialect.identity.GetGeneratedKeysDelegate.prepare(GetGeneratedKeysDelegate.java:51)
        //       at org.hibernate.id.insert.AbstractReturningDelegate.performInsert(AbstractReturningDelegate.java:39)
        //       at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3342)
        //       at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3980)
        //       at org.hibernate.action.internal.EntityIdentityInsertAction.execute(EntityIdentityInsertAction.java:80)
        //       at org.hibernate.engine.spi.ActionQueue.execute(ActionQueue.java:653)
        //       at org.hibernate.engine.spi.ActionQueue.addResolvedEntityInsertAction(ActionQueue.java:283)
        //       at org.hibernate.engine.spi.ActionQueue.addInsertAction(ActionQueue.java:264)
        //       at org.hibernate.engine.spi.ActionQueue.addAction(ActionQueue.java:322)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.addInsertAction(AbstractSaveEventListener.java:340)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.performSaveOrReplicate(AbstractSaveEventListener.java:286)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.performSave(AbstractSaveEventListener.java:192)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.saveWithGeneratedId(AbstractSaveEventListener.java:122)
        //       at org.hibernate.event.internal.DefaultPersistEventListener.entityIsTransient(DefaultPersistEventListener.java:184)
        //       at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:129)
        //       at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:53)
        //       at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:107)
        //       at org.hibernate.internal.SessionImpl.firePersist(SessionImpl.java:737)
        //       at org.hibernate.internal.SessionImpl.persist(SessionImpl.java:721)
        //       at jdk.proxy4/jdk.proxy4.$Proxy136.persist(Unknown Source)
        //       at jdk.proxy4/jdk.proxy4.$Proxy146.save(Unknown Source)
        //   org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "SERVICE_HAIR" not found (this database is empty); SQL statement:
        //   insert into service_hair (description, price, service_name, url) values (?, ?, ?, ?) [42104-214]
        //       at org.h2.message.DbException.getJdbcSQLException(DbException.java:502)
        //       at org.h2.message.DbException.getJdbcSQLException(DbException.java:477)
        //       at org.h2.message.DbException.get(DbException.java:223)
        //       at org.h2.message.DbException.get(DbException.java:199)
        //       at org.h2.command.Parser.getTableOrViewNotFoundDbException(Parser.java:8385)
        //       at org.h2.command.Parser.getTableOrViewNotFoundDbException(Parser.java:8369)
        //       at org.h2.command.Parser.readTableOrView(Parser.java:8358)
        //       at org.h2.command.Parser.readTableOrView(Parser.java:8328)
        //       at org.h2.command.Parser.parseInsert(Parser.java:1632)
        //       at org.h2.command.Parser.parsePrepared(Parser.java:814)
        //       at org.h2.command.Parser.parse(Parser.java:689)
        //       at org.h2.command.Parser.parse(Parser.java:661)
        //       at org.h2.command.Parser.prepareCommand(Parser.java:569)
        //       at org.h2.engine.SessionLocal.prepareLocal(SessionLocal.java:631)
        //       at org.h2.engine.SessionLocal.prepareCommand(SessionLocal.java:554)
        //       at org.h2.jdbc.JdbcConnection.prepareCommand(JdbcConnection.java:1116)
        //       at org.h2.jdbc.JdbcPreparedStatement.<init>(JdbcPreparedStatement.java:92)
        //       at org.h2.jdbc.JdbcConnection.prepareStatement(JdbcConnection.java:1044)
        //       at org.hibernate.engine.jdbc.internal.StatementPreparerImpl$2.doPrepare(StatementPreparerImpl.java:109)
        //       at org.hibernate.engine.jdbc.internal.StatementPreparerImpl$StatementPreparationTemplate.prepareStatement(StatementPreparerImpl.java:176)
        //       at org.hibernate.engine.jdbc.internal.StatementPreparerImpl.prepareStatement(StatementPreparerImpl.java:111)
        //       at org.hibernate.dialect.identity.GetGeneratedKeysDelegate.prepare(GetGeneratedKeysDelegate.java:51)
        //       at org.hibernate.id.insert.AbstractReturningDelegate.performInsert(AbstractReturningDelegate.java:39)
        //       at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3342)
        //       at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3980)
        //       at org.hibernate.action.internal.EntityIdentityInsertAction.execute(EntityIdentityInsertAction.java:80)
        //       at org.hibernate.engine.spi.ActionQueue.execute(ActionQueue.java:653)
        //       at org.hibernate.engine.spi.ActionQueue.addResolvedEntityInsertAction(ActionQueue.java:283)
        //       at org.hibernate.engine.spi.ActionQueue.addInsertAction(ActionQueue.java:264)
        //       at org.hibernate.engine.spi.ActionQueue.addAction(ActionQueue.java:322)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.addInsertAction(AbstractSaveEventListener.java:340)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.performSaveOrReplicate(AbstractSaveEventListener.java:286)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.performSave(AbstractSaveEventListener.java:192)
        //       at org.hibernate.event.internal.AbstractSaveEventListener.saveWithGeneratedId(AbstractSaveEventListener.java:122)
        //       at org.hibernate.event.internal.DefaultPersistEventListener.entityIsTransient(DefaultPersistEventListener.java:184)
        //       at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:129)
        //       at org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:53)
        //       at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:107)
        //       at org.hibernate.internal.SessionImpl.firePersist(SessionImpl.java:737)
        //       at org.hibernate.internal.SessionImpl.persist(SessionImpl.java:721)
        //       at jdk.proxy4/jdk.proxy4.$Proxy136.persist(Unknown Source)
        //       at jdk.proxy4/jdk.proxy4.$Proxy146.save(Unknown Source)
        //   See https://diff.blue/R013 to resolve this issue.

        ServiceHair serviceHair = new ServiceHair();
        serviceHair.setAppointments(new ArrayList<>());
        serviceHair.setDescription("The characteristics of someone or something");
        serviceHair.setPrice(10.0d);
        serviceHair.setReviews(new ArrayList<>());
        serviceHair.setServiceName("Service Name");
        serviceHair.setUrl("https://example.org/example");
        serviceHairRepository.save(serviceHair);

        ServiceHair serviceHair2 = new ServiceHair();
        serviceHair2.setAppointments(new ArrayList<>());
        serviceHair2.setDescription("Description");
        serviceHair2.setPrice(0.5d);
        serviceHair2.setReviews(new ArrayList<>());
        serviceHair2.setServiceName("42");
        serviceHair2.setUrl("Url");
        serviceHairRepository.save(serviceHair2);
        serviceHairRepository.findByPartialServiceName("Service Name");
    }
}

