package com.hairsalon.respository;

import com.hairsalon.Enum.Role;
import com.hairsalon.entity.Appointment;
import com.hairsalon.entity.AppointmentStatus;
import com.hairsalon.entity.Customer;
import com.hairsalon.entity.Salon;
import com.hairsalon.entity.ServiceHair;
import com.hairsalon.entity.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = {AppointmentRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.hairsalon.entity"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
@ExtendWith(MockitoExtension.class)
public class AppointmentRepositoryTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Test
    void testFindAppointmentByStatusId() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.dao.InvalidDataAccessResourceUsageException: could not prepare statement; SQL [insert into appointment (appointment_date, apm_status_id, appointment_time, created_at, customer_id, salon_id, service_id, updated_at, user_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)]
        //       at jdk.proxy4/jdk.proxy4.$Proxy152.save(Unknown Source)
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
        //       at jdk.proxy4/jdk.proxy4.$Proxy152.save(Unknown Source)
        //   org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "APPOINTMENT" not found (this database is empty); SQL statement:
        //   insert into appointment (appointment_date, apm_status_id, appointment_time, created_at, customer_id, salon_id, service_id, updated_at, user_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?) [42104-214]
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
        //       at jdk.proxy4/jdk.proxy4.$Proxy152.save(Unknown Source)
        //   See https://diff.blue/R013 to resolve this issue.

        LocalDate appointmentDate = LocalDate.of(1970, 1, 1);

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDate);

        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setAppointments(new ArrayList<>());
        appointmentStatus.setId(1);
        appointmentStatus.setStatus("Status");
        appointment.setAppointmentStatus(appointmentStatus);
        appointment.setAppointmentTime(LocalTime.MIDNIGHT);
        appointment.setCreateAt(mock(Timestamp.class));

        Customer customer = new Customer();
        customer.setAppointments(new ArrayList<>());
        customer.setCustomerAddresses(new ArrayList<>());
        customer.setCustomerName("Customer Name");
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setOrders(new ArrayList<>());
        customer.setPaymentMethods(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        customer.setReviews(new ArrayList<>());
        appointment.setCustomer(customer);

        Salon salon = new Salon();
        salon.setAddress("42 Main St");
        salon.setAppointments(new ArrayList<>());
        salon.setId(1);
        salon.setPhoneNumber("6625550144");
        salon.setSalonName("Salon Name");
        appointment.setSalon(salon);

        ServiceHair serviceHair = new ServiceHair();
        serviceHair.setAppointments(new ArrayList<>());
        serviceHair.setDescription("The characteristics of someone or something");
        serviceHair.setId(1);
        serviceHair.setPrice(10.0d);
        serviceHair.setReviews(new ArrayList<>());
        serviceHair.setServiceName("Service Name");
        serviceHair.setUrl("https://example.org/example");
        appointment.setServiceHair(serviceHair);
        appointment.setUpdateAt(mock(Timestamp.class));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());
        user.setUserName("janedoe");
        appointment.setUser(user);
        appointmentRepository.save(appointment);
        LocalDate appointmentDate2 = LocalDate.of(1970, 1, 1);

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentDate(appointmentDate2);

        AppointmentStatus appointmentStatus2 = new AppointmentStatus();
        appointmentStatus2.setAppointments(new ArrayList<>());
        appointmentStatus2.setId(2);
        appointmentStatus2.setStatus("42");
        appointment2.setAppointmentStatus(appointmentStatus2);
        appointment2.setAppointmentTime(LocalTime.MIDNIGHT);
        appointment2.setCreateAt(mock(Timestamp.class));

        Customer customer2 = new Customer();
        customer2.setAppointments(new ArrayList<>());
        customer2.setCustomerAddresses(new ArrayList<>());
        customer2.setCustomerName("42");
        customer2.setEmail("john.smith@example.org");
        customer2.setId(2);
        customer2.setOrders(new ArrayList<>());
        customer2.setPaymentMethods(new ArrayList<>());
        customer2.setPhoneNumber("8605550118");
        customer2.setReviews(new ArrayList<>());
        appointment2.setCustomer(customer2);

        Salon salon2 = new Salon();
        salon2.setAddress("17 High St");
        salon2.setAppointments(new ArrayList<>());
        salon2.setId(2);
        salon2.setPhoneNumber("8605550118");
        salon2.setSalonName("42");
        appointment2.setSalon(salon2);

        ServiceHair serviceHair2 = new ServiceHair();
        serviceHair2.setAppointments(new ArrayList<>());
        serviceHair2.setDescription("Description");
        serviceHair2.setId(2);
        serviceHair2.setPrice(0.5d);
        serviceHair2.setReviews(new ArrayList<>());
        serviceHair2.setServiceName("42");
        serviceHair2.setUrl("Url");
        appointment2.setServiceHair(serviceHair2);
        appointment2.setUpdateAt(mock(Timestamp.class));

        User user2 = new User();
        user2.setEmail("john.smith@example.org");
        user2.setId(2);
        user2.setPassword("Password");
        user2.setRole(Role.ADMIN);
        user2.setTokens(new ArrayList<>());
        user2.setUserName("User Name");
        appointment2.setUser(user2);
        appointmentRepository.save(appointment2);
        appointmentRepository.findAppointmentByStatusId(1);
    }


    @Test
    void testFindAppointmentByCustomerId() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.dao.InvalidDataAccessResourceUsageException: could not prepare statement; SQL [insert into appointment (appointment_date, apm_status_id, appointment_time, created_at, customer_id, salon_id, service_id, updated_at, user_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)]
        //       at jdk.proxy4/jdk.proxy4.$Proxy152.save(Unknown Source)
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
        //       at jdk.proxy4/jdk.proxy4.$Proxy152.save(Unknown Source)
        //   org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "APPOINTMENT" not found (this database is empty); SQL statement:
        //   insert into appointment (appointment_date, apm_status_id, appointment_time, created_at, customer_id, salon_id, service_id, updated_at, user_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?) [42104-214]
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
        //       at jdk.proxy4/jdk.proxy4.$Proxy152.save(Unknown Source)
        //   See https://diff.blue/R013 to resolve this issue.

        LocalDate appointmentDate = LocalDate.of(1970, 1, 1);

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDate);

        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setAppointments(new ArrayList<>());
        appointmentStatus.setId(1);
        appointmentStatus.setStatus("Status");
        appointment.setAppointmentStatus(appointmentStatus);
        appointment.setAppointmentTime(LocalTime.MIDNIGHT);
        appointment.setCreateAt(mock(Timestamp.class));

        Customer customer = new Customer();
        customer.setAppointments(new ArrayList<>());
        customer.setCustomerAddresses(new ArrayList<>());
        customer.setCustomerName("Customer Name");
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setOrders(new ArrayList<>());
        customer.setPaymentMethods(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        customer.setReviews(new ArrayList<>());
        appointment.setCustomer(customer);

        Salon salon = new Salon();
        salon.setAddress("42 Main St");
        salon.setAppointments(new ArrayList<>());
        salon.setId(1);
        salon.setPhoneNumber("6625550144");
        salon.setSalonName("Salon Name");
        appointment.setSalon(salon);

        ServiceHair serviceHair = new ServiceHair();
        serviceHair.setAppointments(new ArrayList<>());
        serviceHair.setDescription("The characteristics of someone or something");
        serviceHair.setId(1);
        serviceHair.setPrice(10.0d);
        serviceHair.setReviews(new ArrayList<>());
        serviceHair.setServiceName("Service Name");
        serviceHair.setUrl("https://example.org/example");
        appointment.setServiceHair(serviceHair);
        appointment.setUpdateAt(mock(Timestamp.class));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());
        user.setUserName("janedoe");
        appointment.setUser(user);
        appointmentRepository.save(appointment);
        LocalDate appointmentDate2 = LocalDate.of(1970, 1, 1);

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentDate(appointmentDate2);

        AppointmentStatus appointmentStatus2 = new AppointmentStatus();
        appointmentStatus2.setAppointments(new ArrayList<>());
        appointmentStatus2.setId(2);
        appointmentStatus2.setStatus("42");
        appointment2.setAppointmentStatus(appointmentStatus2);
        appointment2.setAppointmentTime(LocalTime.MIDNIGHT);
        appointment2.setCreateAt(mock(Timestamp.class));

        Customer customer2 = new Customer();
        customer2.setAppointments(new ArrayList<>());
        customer2.setCustomerAddresses(new ArrayList<>());
        customer2.setCustomerName("42");
        customer2.setEmail("john.smith@example.org");
        customer2.setId(2);
        customer2.setOrders(new ArrayList<>());
        customer2.setPaymentMethods(new ArrayList<>());
        customer2.setPhoneNumber("8605550118");
        customer2.setReviews(new ArrayList<>());
        appointment2.setCustomer(customer2);

        Salon salon2 = new Salon();
        salon2.setAddress("17 High St");
        salon2.setAppointments(new ArrayList<>());
        salon2.setId(2);
        salon2.setPhoneNumber("8605550118");
        salon2.setSalonName("42");
        appointment2.setSalon(salon2);

        ServiceHair serviceHair2 = new ServiceHair();
        serviceHair2.setAppointments(new ArrayList<>());
        serviceHair2.setDescription("Description");
        serviceHair2.setId(2);
        serviceHair2.setPrice(0.5d);
        serviceHair2.setReviews(new ArrayList<>());
        serviceHair2.setServiceName("42");
        serviceHair2.setUrl("Url");
        appointment2.setServiceHair(serviceHair2);
        appointment2.setUpdateAt(mock(Timestamp.class));

        User user2 = new User();
        user2.setEmail("john.smith@example.org");
        user2.setId(2);
        user2.setPassword("Password");
        user2.setRole(Role.ADMIN);
        user2.setTokens(new ArrayList<>());
        user2.setUserName("User Name");
        appointment2.setUser(user2);
        appointmentRepository.save(appointment2);
        appointmentRepository.findAppointmentByCustomerId(1);
    }
}

