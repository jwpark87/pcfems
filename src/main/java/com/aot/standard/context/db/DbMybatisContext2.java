package com.aot.standard.context.db;

import com.aot.standard.common.enc.AES;
import com.aot.standard.override.SqlSessionTemplateOverride;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.Serializable;

//@Configuration
//@EnableTransactionManagement
//@MapperScan(basePackages = "com.aot", annotationClass = Mapper.class)
public class DbMybatisContext2 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("#{jdbcProperties['jdbc.second.driverClassName']}")
    private String driverClassName;
    @Value("#{jdbcProperties['jdbc.second.url']}")
    private String url;
    @Value("#{jdbcProperties['jdbc.second.username']}")
    private String username;
    @Value("#{jdbcProperties['jdbc.second.password']}")
    private String password;
    @Value("#{jdbcProperties['jdbc.second.autoCommit']}")
    private Boolean autoCommit;
    @Value("#{jdbcProperties['jdbc.second.connectionTimeoutMs']}")
    private Long connectionTimeoutMs;
    @Value("#{jdbcProperties['jdbc.second.idleTimeoutMs']}")
    private Long idleTimeoutMs;
    @Value("#{jdbcProperties['jdbc.second.maxLifetimeMs']}")
    private Long maxLifetimeMs;
    @Value("#{jdbcProperties['jdbc.second.minIdle']}")
    private Integer minIdle;
    @Value("#{jdbcProperties['jdbc.second.maxPoolSize']}")
    private Integer maxPoolSize;

    @Bean(name = "dataSource2")
    public HikariDataSource getHikariDataSource() throws Exception {
        this.logger.info("dataSource2 :: init");
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(this.driverClassName);
        hikariConfig.setJdbcUrl(this.url);
        hikariConfig.setUsername(AES.decrypt(this.username));
        // hikariConfig.setUsername(this.username);
        hikariConfig.setPassword(AES.decrypt(this.password));
        // hikariConfig.setPassword(this.password);
        hikariConfig.setAutoCommit(this.autoCommit);
        hikariConfig.setConnectionTimeout(this.connectionTimeoutMs);
        hikariConfig.setIdleTimeout(this.idleTimeoutMs);
        hikariConfig.setMaxLifetime(this.maxLifetimeMs);
        hikariConfig.setMinimumIdle(this.minIdle);
        hikariConfig.setMaximumPoolSize(this.maxPoolSize);

        return new HikariDataSource(hikariConfig);
    }

    /**
     * 마이바티스 {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다.
     * <p>
     * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다. 쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다.
     */
    @Bean(name = "sqlSession2", destroyMethod = "clearCache")
    @Autowired
    public SqlSessionTemplateOverride getAotSqlSessionTemplate(final ApplicationContext applicationContext) throws Exception {
        return new SqlSessionTemplateOverride(this.getSqlSessionFactoryBean(applicationContext).getObject());
    }

    @Bean(name = "transactionManager2")
    @Autowired
    public PlatformTransactionManager getPlatformTransactionManager(final ApplicationContext applicationContext) throws Exception {
        return new DataSourceTransactionManager(this.getHikariDataSource());
    }

    private SqlSessionFactoryBean getSqlSessionFactoryBean(final ApplicationContext applicationContext) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(this.getHikariDataSource());
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setTypeAliasesPackage("com.aot");
        sqlSessionFactory.setTypeAliasesSuperType(Serializable.class);
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath*:**/*Mapper.xml"));
        return sqlSessionFactory;
    }
}