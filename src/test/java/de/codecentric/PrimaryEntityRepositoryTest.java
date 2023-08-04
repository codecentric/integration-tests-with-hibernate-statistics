package de.codecentric;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = "spring.jpa.properties.hibernate.generate_statistics=true")
class PrimaryEntityRepositoryTest {

    @Autowired
    public PrimaryEntityRepositoryTest(PrimaryEntityRepository primaryEntityRepository, SessionFactory sessionFactory) {
        this.primaryEntityRepository = primaryEntityRepository;
        this.sessionFactory = sessionFactory;
    }

    private final PrimaryEntityRepository primaryEntityRepository;

    private final SessionFactory sessionFactory;

    private Statistics stats;

    @BeforeEach
    void setUp() {
        stats = sessionFactory.getStatistics();
        stats.clear();
    }

    @Test
    void derivedQueryRunsIntoOnePlusNProblem() {
        final List<PrimaryEntity> primaryEntities = primaryEntityRepository.findByCategory("CAT1");

        assertThat(primaryEntities).hasSize(3);
        assertThat(stats.getQueryExecutionCount()).isOne();
        assertThat(stats.getEntityFetchCount()).isEqualTo(primaryEntities.size());
        assertThat(stats.getPrepareStatementCount()).isEqualTo(stats.getQueryExecutionCount() + stats.getEntityFetchCount());
    }

    @Test
    void jpqlWithJoinFetchExecutesSingleQuery() {
        final List<PrimaryEntity> primaryEntities = primaryEntityRepository.findByCategoryJoinFetch("CAT1");

        assertThat(primaryEntities).hasSize(3);
        assertThat(stats.getQueryExecutionCount()).isOne();
        assertThat(stats.getEntityFetchCount()).isZero();
        assertThat(stats.getPrepareStatementCount()).isEqualTo(stats.getQueryExecutionCount() + stats.getEntityFetchCount());
    }

}